package gr.aueb.carpooling.model.view.attribute_selection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.memoryDao.MemoryInitialized;
import gr.aueb.carpooling.model.view.LogIn.LogInActivity;
import gr.aueb.carpooling.model.view.LogIn.LoginViewModel;
import gr.aueb.carpooling.model.view.driver.DriverFrontPage;
import gr.aueb.carpooling.model.view.passenger.PassengerFrontPageActivity;

public class AttributeSelectionActivity extends AppCompatActivity implements AttributeSelectionView {

    private ImageButton back_button;
    private Button passenger_button;
    private Button driver_button;
    private AttributeSelectionViewModel viewModel;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_attribute);

        MemoryInitialized dataHelper = new MemoryInitialized();
        dataHelper.prepareData();


        viewModel = new ViewModelProvider(this).get(AttributeSelectionViewModel.class);

        viewModel.getPresenter().setView(this);

       Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Id");
            //The key argument here must match that used in the other activity
        }



        back_button = (ImageButton) findViewById(R.id.back_button);
        passenger_button = (Button) findViewById(R.id.button_passenger);
        driver_button = (Button) findViewById(R.id.button_driver);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogInActivity();
            }
        });

        passenger_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isPassenger = viewModel.getPresenter().authenticateAttributePassenger(username);
                showErrorMessage("Passenger", String.valueOf(isPassenger));
                if (isPassenger){
                    openPassengerPage();
                } else {
                    openLogInActivity();
                }
            }
        });

        driver_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            boolean isDriver = viewModel.getPresenter().authenticateAttributeDriver(username);
            showErrorMessage("Driver", String.valueOf(isDriver));
            if (isDriver){
                    openDriverPage(username);
                } else {
                    openLogInActivity();
                }
            }
        });
    }

    public void openLogInActivity() {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    public void openPassengerPage() {
        Intent intent = new Intent(this, PassengerFrontPageActivity.class);
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


}