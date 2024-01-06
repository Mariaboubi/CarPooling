package gr.aueb.carpooling.model.view.passenger.DriverRaiting;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.dao.SubrouteDAO;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;
import gr.aueb.carpooling.model.memoryDao.SubrouteDAOmemory;
import gr.aueb.carpooling.model.view.driver.DriverFrontPage;
import gr.aueb.carpooling.model.view.driver.RatingPassengers.RatingPassengerRecyclerViewAdapter;
import gr.aueb.carpooling.model.view.driver.RatingPassengers.RatingPassengerView;
import gr.aueb.carpooling.model.view.driver.RatingPassengers.RatingPassengersViewModel;
import gr.aueb.carpooling.model.view.driver.createRoute.CreateRouteActivity;
import gr.aueb.carpooling.model.view.passenger.PassengerFrontPageActivity;

public class DriverRaitingActivity extends AppCompatActivity implements DriverRaitingView {

    private DriverRaitingViewModel viewModel;

    private String username;

    public  TextView DriverUsername;

    private View view;

    private String dest;

    private String pick;

    private String date;

    private Button rate_button;

    private Subroute subroute;

    private PassengerDAO passengerDAO=new PassengerDAOmemory();

    private RouteDAO routeDAO=new RouteDAOmemory();

    private SubrouteDAO subrouteDAO= new SubrouteDAOmemory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_rating_driver);

        viewModel = new ViewModelProvider(this).get(DriverRaitingViewModel.class);
        viewModel.getPresenter().setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            dest=extras.getString("SubrouteDest");
            pick=extras.getString("SubroutePick");
            date=extras.getString("SubrouteDate");
            //The key argument here must match that used in the other activity
        }
        subroute= subrouteDAO.find(dest,pick,date);
        showErrorMessage("Destination", subroute.getDestination().toString());
        rate_button = (Button) findViewById(R.id.RateButton);
        Route route = routeDAO.findByMap(passengerDAO.findByUsername(username),subroute);
        DriverUsername = ((TextView)findViewById(R.id.DriversUserName));
        DriverUsername.setText(route.getDriver().getUsername());

        rate_button.setOnClickListener(new View.OnClickListener(){ // Όταν πατηθεί το κουμπί δημιουργίας του  subroute
            @Override
            public void onClick(View v){
                viewModel.getPresenter().onCreateRate(username,route);

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
        return ((EditText)findViewById(R.id.Politeness)).getText().toString().trim();
    }

    @Override
    public String Security() {
        return ((EditText)findViewById(R.id.Security)).getText().toString().trim();
    }

    @Override
    public String Cleanliness() {
        return ((EditText)findViewById(R.id.Cleanliness)).getText().toString().trim();
    }

    public void showRateAddedMessage() {
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle("Επιτυχής προσθήκη κριτικής")
                .setMessage("Η Βαθμολογία προστέθηκε με επιτυχία στην λίστα του οδηγού!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).create().show();

        Intent intent = new Intent(this, PassengerFrontPageActivity.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }


}