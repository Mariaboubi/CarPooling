package gr.aueb.carpooling.model.view.subroute;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.HashMap;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.view.passenger.PassengerFrontPageActivity;

public class subrouteActivity extends AppCompatActivity implements SubrouteView {

    private ImageButton back_button;
    private int passengerId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subroute);

        back_button = (ImageButton) findViewById(R.id.back_button2);

        SubrouteViewModel viewModel = new ViewModelProvider(this).get(SubrouteViewModel.class);
        viewModel.getPresenter().setView(this);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            assert extras != null;
            passengerId = extras.getInt("PassengerId");
        }
        viewModel.getPresenter().setPassenger(passengerId);
//
//        findViewById(R.id.btnCreateSubroute).setOnClickListener(new View.OnClickListener(){ // Όταν πατηθεί το κουμπί δημιουργίας του  subroute
//            @Override
//            public void onClick(View v){
//                viewModel.getPresenter().onCreateSubRoute();
//            }
//        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openPassengerFrontPageActivity();}
        });


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

        new AlertDialog.Builder(subrouteActivity.this)
                .setCancelable(true)
                .setTitle("Επιτυχής προσθήκη διαδρομης")
                .setMessage("Η διαδρομή προστέθηκε με επιτυχία στην λίστα του οδηγού!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).create().show();

    }

    public void goBack() {finish();}

    public void openPassengerFrontPageActivity(){
        Intent intent = new Intent(this, PassengerFrontPageActivity.class);
        startActivity(intent);
    }
}
