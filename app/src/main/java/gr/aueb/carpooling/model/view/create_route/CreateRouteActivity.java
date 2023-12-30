package gr.aueb.carpooling.model.view.create_route;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

import gr.aueb.carpooling.R;

public class CreateRouteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_route);
        CreateRouteViewModel viewModel = new ViewModelProvider(this).get(CreateRouteViewModel.class);
        viewModel.getPresenter().setView((CreateRouteView) this);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            driverId = extras.getInt("DriverId");
        }
        viewModel.getPresenter().setDriver(driverId);

        findViewById(R.id.btnCreateRoute).setOnClickListener(new View.OnClickListener(){ // Όταν πατηθεί το κουμπί δημιουργίας του εστιατορίου
            @Override
            public void onClick(View v){
                viewModel.getPresenter().onCreateRestaurant();
            }

        });


    }

    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(CreateRouteActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    public void showRouteAddedMessage()
    {
        new AlertDialog.Builder(CreateRouteActivity.this)
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
    private int driverId;

    public HashMap<String,String> getRouteDetails(){
        HashMap<String,String> details = new HashMap<>();
        details.put("Street",(((EditText)findViewById(R.id.CreateRouteStreet)).getText().toString().trim()));
        details.put("Street Number",(((EditText)findViewById(R.id.CreateRouteNumber)).getText().toString().trim()));
        details.put("City",(((EditText)findViewById(R.id.CreateRouteCity)).getText().toString().trim()));
        details.put("ZipCode",(((EditText)findViewById(R.id.CreateRouteZipCode)).getText().toString().trim()));
        details.put("Date",(((EditText)findViewById(R.id.CreateRouteDate)).getText().toString().trim()));
        details.put("Estimated Cost",(((EditText)findViewById(R.id.CreateRouteEstimatedCost)).getText().toString().trim()));
        details.put("Max number of passenger",(((EditText)findViewById(R.id.CreateRouteMaxPassenger)).getText().toString().trim()));
        return details;
    }

    public void goBack(){
        finish();
    }
}