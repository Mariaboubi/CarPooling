package gr.aueb.carpooling.model.view.passenger.DriverRaiting;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.view.driver.RatingPassengers.RatingPassengerRecyclerViewAdapter;
import gr.aueb.carpooling.model.view.driver.RatingPassengers.RatingPassengerView;
import gr.aueb.carpooling.model.view.driver.RatingPassengers.RatingPassengersViewModel;

public class DriverRaitingActivity extends AppCompatActivity implements DriverRaitingView {

    private DriverRaitingViewModel viewModel;

    private String username;

    public  TextView DriverUsername;

    private View view;

    private Button rate_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_rating_driver);

        viewModel = new ViewModelProvider(this).get(DriverRaitingViewModel.class);
        viewModel.getPresenter().setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            //The key argument here must match that used in the other activity
        }

        rate_button = (Button) findViewById(R.id.RateButton);

//        DriverUsername = (TextView) view.findViewById(R.id.DriversUserName);
//
//        DriverUsername.setText(username);

//        rate_button.setOnClickListener(new View.OnClickListener(){ // Όταν πατηθεί το κουμπί δημιουργίας του  subroute
//            @Override
//            public void onClick(View v){
//                viewModel.getPresenter().onCreateRate(username);
//
//            }
//        });


    }


    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }
}