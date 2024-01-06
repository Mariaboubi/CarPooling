package gr.aueb.carpooling.model.view.driver.createRoute;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.HashMap;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.view.driver.DriverFrontPage;

public class CreateRouteActivity extends AppCompatActivity implements CreateRouteView {

    private CreateRouteViewModel viewModel;

    private CreateRouteView view;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_create_new_route);


        viewModel = new ViewModelProvider(this).get(CreateRouteViewModel.class);
        viewModel.getPresenter().setView(this);
//
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            //The key argument here must match that used in the other activity
        }

        Button create_route_button = (Button) findViewById(R.id.btnCreateRoute);
        create_route_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewModel.getPresenter().onCreateRoute(username);
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageButton back_button = (ImageButton) findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDriverFrontPage(username);
            }
        });

    }



    @Override
    public String Streeet() {
        return ((EditText)findViewById(R.id.CreateRouteStreet)).getText().toString().trim();
    }

    @Override
    public String Number() {
        return ((EditText)findViewById(R.id.CreateRouteNumber)).getText().toString().trim();
    }

    @Override
    public String City() {
        return ((EditText)findViewById(R.id.CreateRouteCity)).getText().toString().trim();
    }

    @Override
    public String ZipCode() {
        return ((EditText)findViewById(R.id.CreateRouteZipCode)).getText().toString().trim();
    }

    @Override
    public String EstimatedCost() {
        return ((EditText)findViewById(R.id.CreateRouteEstimatedCost)).getText().toString().trim();
    }

    @Override
    public String MaxPassengers() {
        return ((EditText)findViewById(R.id.CreateRouteMaxPassenger)).getText().toString().trim();
    }

    public String Date() {
        return ((EditText)findViewById(R.id.CreateRouteDate)).getText().toString().trim();
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

    public void openDriverFrontPage(String username) {
        Intent intent = new Intent(CreateRouteActivity.this, DriverFrontPage.class);
        intent.putExtra("Username", username);
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