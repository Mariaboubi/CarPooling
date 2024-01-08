package gr.aueb.carpooling.model.view.passenger.driver_rating;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.dao.SubrouteDAO;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;
import gr.aueb.carpooling.model.memoryDao.SubrouteDAOmemory;
import gr.aueb.carpooling.model.view.passenger.front_page.*;

public class DriverRatingActivity extends AppCompatActivity implements DriverRatingView {

    private DriverRatingViewModel viewModel;

    private String username; //passenger username
    private int subroute_id;   //subroute id

    public TextView DriverUsername;

    // DAOs
    private final PassengerDAO passengerDAO = new PassengerDAOmemory();

    private final RouteDAO routeDAO = new RouteDAOmemory();

    private final SubrouteDAO subrouteDAO = new SubrouteDAOmemory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_rating_driver);

        viewModel = new ViewModelProvider(this).get(DriverRatingViewModel.class);
        viewModel.getPresenter().setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            subroute_id = extras.getInt("SubrouteId");
        }
        Subroute subroute = subrouteDAO.findById(subroute_id);
        Passenger passenger = passengerDAO.findByUsername(username);
        Route route = routeDAO.findRouteByPassAndSub(passenger, subroute);
        String  driver_username = route.getDriver().getUsername();

        // Buttons and TxtViews
        Button rate_button = (Button) findViewById(R.id.RateButton);

        DriverUsername = ((TextView) findViewById(R.id.DriversUserName));
        DriverUsername.setText(driver_username);

        rate_button.setOnClickListener(v -> viewModel.getPresenter().onCreateRate(passenger, route));

        ImageButton confirm_ratings = (ImageButton) findViewById(R.id.confirm_ratings);

        confirm_ratings.setOnClickListener(v -> {
            boolean check = viewModel.getPresenter().checkButtonCanBePressed();
            if(check) {
                openPassengerFrontPage(username);
            }
        });


    }

    private void openPassengerFrontPage(String username) {
        Intent intent = new Intent(this, PassengerFrontPageActivity.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }


    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    @Override
    public String politeness() {
        return ((EditText) findViewById(R.id.Politeness)).getText().toString().trim();
    }

    @Override
    public String security() {
        return ((EditText) findViewById(R.id.Security)).getText().toString().trim();
    }

    @Override
    public String cleanliness() {
        return ((EditText) findViewById(R.id.Cleanliness)).getText().toString().trim();
    }

    public void RateAdded() {
        Intent intent = new Intent(this, PassengerFrontPageActivity.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }


}