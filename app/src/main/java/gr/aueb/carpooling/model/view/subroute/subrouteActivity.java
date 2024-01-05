package gr.aueb.carpooling.model.view.subroute;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.HashMap;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.view.passenger.PassengerFrontPageActivity;
import gr.aueb.carpooling.model.view.passenger.search_route.SearchRouteActivity;

public class subrouteActivity extends AppCompatActivity implements SubrouteView {

    private ImageButton back_button;
    private Button create_route_button;
    private String username;

    private SubrouteViewModel viewModel;

    @SuppressLint("MissingInflatedId")
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
                openSearchRoute();
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openPassengerFrontPageActivity(username);}
        });




    }

    void openSearchRoute() {
        Intent intent = new Intent(this, SearchRouteActivity.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    @Override
    public HashMap<String, String> getSubRouteDetails() {
        HashMap<String,String> details = new HashMap<>();
        details.put("Street",(((EditText)findViewById(R.id.SubrouteStreetInput)).getText().toString().trim()));
        details.put("Street Number",(((EditText)findViewById(R.id.SubrouteNumberInput1)).getText().toString().trim()));
        details.put("City",(((EditText)findViewById(R.id.SubrouteCityInput)).getText().toString().trim()));
        details.put("ZipCode",(((EditText)findViewById(R.id.SubrouteZipCodeInput1)).getText().toString().trim()));
        ////PICK UP POINT///////
        details.put("PickUp Street",(((EditText)findViewById(R.id.PickUpStreet)).getText().toString().trim()));
        details.put("PickUp Street Number",(((EditText)findViewById(R.id.PickUpNumber)).getText().toString().trim()));
        details.put("PickUp City",(((EditText)findViewById(R.id.PickUpCity)).getText().toString().trim()));
        details.put("PickUp ZipCode",(((EditText)findViewById(R.id.PickUpZipCode)).getText().toString().trim()));
        details.put("Date",(((EditText)findViewById(R.id.Date)).getText().toString().trim()));

        return details;

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
    public void showRouteAddedMessage() {
        Intent intent = new Intent(this, SearchRouteActivity.class);
        intent.putExtra("Username",username);
        startActivity(intent);

    }


    public void openPassengerFrontPageActivity(String username){
        Intent intent = new Intent(this, PassengerFrontPageActivity.class);
        intent.putExtra("Username",username);
        startActivity(intent);
    }
}

