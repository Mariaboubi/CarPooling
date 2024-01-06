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
import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.view.driver.DriverFrontPage;


public class RatingPassengers extends AppCompatActivity implements RatingPassengerView,RatingPassengerRecyclerViewAdapter.PassengerRatingSelectionListener {

    private RatingPassengersViewModel viewModel;

    private String username;
    private RecyclerView recyclerView;
    private TextView emptyView;

    private int route_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_rating_passengers);

        viewModel = new ViewModelProvider(this).get(RatingPassengersViewModel.class);
        viewModel.getPresenter().setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            //The key argument here must match that used in the other activity
        }

        viewModel.getPresenter().setPassengerRAtingList();
        recyclerView = findViewById(R.id.RatingPassengerRecyclerView);
        emptyView = findViewById(R.id.NoPassengers);
        viewModel.getPresenter().onChangeLayout();
        //showErrorMessage("in passenger rating page", "in");



    }



    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(RatingPassengers.this)
            .setCancelable(true)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", null).create().show();
}
//    @Override
//    public HashMap<String, String> getRateDetails() {
//        return null;
//    }

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
        recyclerView.setAdapter(new RatingPassengerRecyclerViewAdapter(viewModel.getPresenter().getPassengerRatingList(), this, route_id));
    }


    @Override
    public void selectRate(PassengerRating rating) {
        Intent intent = new Intent(RatingPassengers.this, DriverFrontPage.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }
}