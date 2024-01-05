package gr.aueb.carpooling.model.view.driver.RatingPassengers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.view.LogIn.LogInActivity;
import gr.aueb.carpooling.model.view.driver.DriverFrontPage;
import gr.aueb.carpooling.model.view.driver.ExistedRoutes.ExistedRouteActivity;
import gr.aueb.carpooling.model.view.driver.ExistedRoutes.ExistedRouteRecyclerViewAdapter;
import gr.aueb.carpooling.model.view.driver.ExistedRoutes.ExistedRouteViewModel;
import gr.aueb.carpooling.model.view.driver.ExistedRoutes.ExitedRouteView;
import gr.aueb.carpooling.model.view.sign_up.SignUpActivity;

public class RatingPassengers extends AppCompatActivity implements RatingPassengerView,RatingPassengerRecyclerViewAdapter.PassengerRatingSelectionListener {

    private RatingPassengersViewModel viewModel;

    private String username;
    private RecyclerView recyclerView;
    private TextView emptyView;
    private int route_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_passengers);

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

    public void showErrorMessage(String title)
    {
        new AlertDialog.Builder(RatingPassengers.this)
                .setCancelable(true)
                .setTitle(title)
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
//        String politiness= ((EditText)findViewById(R.id.Politeness)).getText().toString().trim();
//        String consistency= ((EditText)findViewById(R.id.Consistency)).getText().toString().trim();
//        String reliability= ((EditText)findViewById(R.id.Reliability)).getText().toString().trim();
        recyclerView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RatingPassengerRecyclerViewAdapter(viewModel.getPresenter().getPassengerRatingList(), this, route_id));
    }


    @Override
    public void selectRate(PassengerRating rating) {

    }
}