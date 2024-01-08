package gr.aueb.carpooling.model.view.driver.RatingPassengers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;
import gr.aueb.carpooling.model.view.driver.front_page.DriverFrontPage;


public class RatingPassengers extends AppCompatActivity implements RatingPassengerView, RatingPassengerRecyclerViewAdapter.PassengerRatingSelectionListener {

    private RatingPassengersViewModel viewModel;

    private String username;
    private RecyclerView recyclerView;
    private TextView emptyView;

    public ImageButton confirmButton;
    private int routeId;

    private RouteDAO routeDAO = new RouteDAOmemory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_rating_passengers);

        viewModel = new ViewModelProvider(this).get(RatingPassengersViewModel.class);
        viewModel.getPresenter().setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Driver username");
            routeId = extras.getInt("RouteId");
        }

        Route route = routeDAO.find(routeId);
        viewModel.getPresenter().setPassengerList(route);

        // ui initialization
        recyclerView = findViewById(R.id.RatingPassengerRecyclerView);
        emptyView = findViewById(R.id.NoPassengers);
        viewModel.getPresenter().onChangeLayout();

        confirmButton = ((ImageButton) findViewById(R.id.confirm_ratings));
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDriverFrontPage();
            }
        });

    }


    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(RatingPassengers.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }


    @Override
    public void ShowNoPassengers() {
        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }


    @Override
    public void ShowPassengers() {
        recyclerView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RatingPassengerRecyclerViewAdapter(viewModel.getPresenter().getPassengerList(), this, routeId));
    }


    @Override
    public void selectRate(PassengerRating rating) {
        Intent intent = new Intent(this, RatingPassengers.class);
        intent.putExtra("RouteId",rating.getRoute().getId());
        intent.putExtra("Driver username", username);
        startActivity(intent);
    }

    @Override
    public boolean validateRates(Passenger passenger, String politeness, String consistency, String reliability, Route route) {
        double pol = 0;
        double cons = 0;
        double rel = 0;
        if(!politeness.isEmpty()){
            pol = Double.valueOf(politeness);
        }
        if(!consistency.isEmpty()){
            cons = Double.valueOf(consistency);
        }
        if(!reliability.isEmpty()){
            rel = Double.valueOf(reliability);
        }
        if(politeness.isEmpty() || consistency.isEmpty() || reliability.isEmpty()) {
            showErrorMessage("Error", "Please fill all the fields");
            return false;
        } else if(pol < 0 || pol > 5) {
            showErrorMessage("Error!", "Politeness must be between 0 and 5.");
            return false;
        } else if(cons < 0 || cons > 5) {
            showErrorMessage("Error!", "Consistency must be between 0 and 5.");
            return false;
        } else if(rel < 0 || rel > 5) {
            showErrorMessage("Error!", "Reliability must be between 0 and 5.");
            return false;
        }
        return true;
    }

    public void goToDriverFrontPage() {

        Intent intent = new Intent(this, DriverFrontPage.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }
}

