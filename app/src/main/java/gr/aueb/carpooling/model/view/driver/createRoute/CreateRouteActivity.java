package gr.aueb.carpooling.model.view.driver.createRoute;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.HashMap;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.view.driver.DriverFrontPage;

public class CreateRouteActivity extends AppCompatActivity implements CreateRouteView {

    private CreateRouteViewModel viewModel;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_create_new_route);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageButton back_button = (ImageButton) findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> openDriverFrontPage());
        viewModel = new ViewModelProvider(this).get(CreateRouteViewModel.class);
        viewModel.getPresenter().setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            //The key argument here must match that used in the other activity
        }

    }

    @Override
    public HashMap<String, String> getRouteDetails() {
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

    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(CreateRouteActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    @Override
    public void goBack() {

    }

    public void openDriverFrontPage() {
        Intent intent = new Intent(this, DriverFrontPage.class);
        startActivity(intent);
    }

    @Override
    public void showRouteAddedMessage() {
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
}