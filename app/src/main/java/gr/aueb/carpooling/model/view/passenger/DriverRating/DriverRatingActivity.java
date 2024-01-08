package gr.aueb.carpooling.model.view.passenger.DriverRating;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.DriverRating;
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

    private String username;
    private int subroute_id;

    public TextView DriverUsername;

    private Button rate_button;

    private Subroute subroute;

    private PassengerDAO passengerDAO = new PassengerDAOmemory();

    private RouteDAO routeDAO = new RouteDAOmemory();

    private SubrouteDAO subrouteDAO = new SubrouteDAOmemory();

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
        subroute = subrouteDAO.findById(subroute_id);

        rate_button = (Button) findViewById(R.id.RateButton);
        Route route = routeDAO.findRouteByPassAndSub(passengerDAO.findByUsername(username), subroute);
        DriverUsername = ((TextView) findViewById(R.id.DriversUserName));
        DriverUsername.setText(route.getDriver().getUsername());

        rate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onCreateRate(username, route);

            }
        });


    }


    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    @Override
    public String Politiness() {
        return ((EditText) findViewById(R.id.Politeness)).getText().toString().trim();
    }

    @Override
    public String Security() {
        return ((EditText) findViewById(R.id.Security)).getText().toString().trim();
    }

    @Override
    public String Cleanliness() {
        return ((EditText) findViewById(R.id.Cleanliness)).getText().toString().trim();
    }

    public void showRateAddedMessage(DriverRating raiting) {
        showErrorMessage(" Η βαθμολογία καταχωρήθεικε.Σε αθτη την διαδρομη εχει Μ.Ο: ", String.valueOf(raiting.averageRating()));


        Intent intent = new Intent(this, PassengerFrontPageActivity.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }


}