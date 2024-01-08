package gr.aueb.carpooling.model.view.attribute_selection;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.view.driver.front_page.DriverFrontPage;
import gr.aueb.carpooling.model.view.log_in.LogInActivity;
import gr.aueb.carpooling.model.view.passenger.front_page.PassengerFrontPageActivity;
import gr.aueb.carpooling.model.view.sign_up.driver.DriverSignUpActivity;
import gr.aueb.carpooling.model.view.sign_up.passenger.PassengerSignUpActivity;

public class AttributeSelectionActivity extends AppCompatActivity implements AttributeSelectionView {

    private AttributeSelectionViewModel viewModel;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attribute_selection);

        viewModel = new ViewModelProvider(this).get(AttributeSelectionViewModel.class);

        viewModel.getPresenter().setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Id");
        }

        ImageButton log_out_button = (ImageButton) findViewById(R.id.log_out);
        Button passenger_button = (Button) findViewById(R.id.button_passenger);
        Button driver_button = (Button) findViewById(R.id.button_driver);

        log_out_button.setOnClickListener(v -> openLogInActivity());

        passenger_button.setOnClickListener(v -> {
            boolean isPassenger = viewModel.getPresenter().authenticateAttributePassenger(username);
            if (isPassenger){
                openPassengerPage(username);
            } else {
                openFillPassengerInfo();
            }
        });

        driver_button.setOnClickListener(v -> {
            boolean isDriver = viewModel.getPresenter().authenticateAttributeDriver(username);
            if (isDriver){
                openDriverPage(username);
            } else {
                openFillDriverInfo();
            }
        });
    }

    public void openLogInActivity() {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    public void openPassengerPage(String username) {
        Intent intent = new Intent(this, PassengerFrontPageActivity.class);
        intent.putExtra("Username", username) ;
        startActivity(intent);
    }

    public void openDriverPage(String username) {
        Intent intent = new Intent(this, DriverFrontPage.class);
        intent.putExtra("Username", username) ;
        startActivity(intent);
    }
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(AttributeSelectionActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    public void openFillDriverInfo() {
        Intent intent = new Intent(this, DriverSignUpActivity.class);
        intent.putExtra("Username", username) ;
        startActivity(intent);

    }

    public void openFillPassengerInfo() {
        Intent intent = new Intent(this, PassengerSignUpActivity.class);
        intent.putExtra("Username", username) ;
        startActivity(intent);
    }
}
