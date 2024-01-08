package gr.aueb.carpooling.model.view.driver.create_route;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.view.driver.front_page.DriverFrontPage;

public class CreateRouteActivity extends AppCompatActivity implements CreateRouteView {

    private CreateRouteViewModel viewModel;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_create_new_route);


        viewModel = new ViewModelProvider(this).get(CreateRouteViewModel.class);
        viewModel.getPresenter().setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
        }

        Button create_route_button = (Button) findViewById(R.id.btnCreateRoute);
        create_route_button.setOnClickListener(v -> viewModel.getPresenter().onCreateRoute(username));

        ImageButton back_button = (ImageButton) findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> openDriverFrontPage(username));

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


    public void openDriverFrontPage(String username) {
        Intent intent = new Intent(CreateRouteActivity.this, DriverFrontPage.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    @Override
    public void showRouteAddedMessage() {
        new AlertDialog.Builder(CreateRouteActivity.this)
                .setCancelable(true)
                .setTitle("Successfully added route")
                .setMessage("Route successfully added to driver list!")
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                    finish();
                }).create().show();
    }
}