package gr.aueb.carpooling.model.view.passenger.subroute;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.view.passenger.front_page.PassengerFrontPageActivity;
import gr.aueb.carpooling.model.view.passenger.search_route.SearchRouteActivity;

public class subrouteActivity extends AppCompatActivity implements SubrouteView {

    private ImageButton back_button;
    private Button create_route_button;
    private String username;

    private SubrouteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_create_subroute);

        back_button = (ImageButton) findViewById(R.id.back_button);
        create_route_button = (Button) findViewById(R.id.btnCreateSubroute);

        viewModel = new ViewModelProvider(this).get(SubrouteViewModel.class);

        viewModel.getPresenter().setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            //The key argument here must match that used in the other activity
        }

        create_route_button.setOnClickListener(new View.OnClickListener(){ // Όταν πατηθεί το κουμπί δημιουργίας του  subroute
            @Override
            public void onClick(View v){
                viewModel.getPresenter().onCreateSubRoute(username);

            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openPassengerFrontPageActivity(username);}
        });




    }



    @Override
    public String  StreetDest(){
        return ((EditText)findViewById(R.id.SubrouteStreetInput)).getText().toString().trim();
    }

    @Override
    public String NumberDest() {
        return ((EditText)findViewById(R.id.SubrouteNumberInput1)).getText().toString().trim();
    }

    @Override
    public String CityDest() {
        return ((EditText)findViewById(R.id.SubrouteCityInput)).getText().toString().trim();
    }

    @Override
    public String ZipCodeDest() {
        return ((EditText)findViewById(R.id.SubrouteZipCodeInput1)).getText().toString().trim();
    }

    @Override
    public String StreetPick() {
        return ((EditText)findViewById(R.id.PickUpStreet)).getText().toString().trim();
    }

    @Override
    public String NumberPick() {
        return ((EditText)findViewById(R.id.PickUpNumber)).getText().toString().trim();
    }

    @Override
    public String CityPick() {
        return ((EditText)findViewById(R.id.PickUpCity)).getText().toString().trim();
    }

    @Override
    public String ZipCodePick() {
        return ((EditText)findViewById(R.id.PickUpZipCode)).getText().toString().trim();
    }

    @Override
    public String Date() {
        return ((EditText)findViewById(R.id.Date)).getText().toString().trim();
    }

    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(subrouteActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }



    @Override
    public void showRouteAddedMessage(Subroute subroute) {
        Intent intent = new Intent(this, SearchRouteActivity.class);
        intent.putExtra("Username",username);
        intent.putExtra("Subroute",subroute.getId());
        startActivity(intent);

    }


    public void openPassengerFrontPageActivity(String username){
        Intent intent = new Intent(this, PassengerFrontPageActivity.class);
        intent.putExtra("Username",username);
        startActivity(intent);
    }
}

