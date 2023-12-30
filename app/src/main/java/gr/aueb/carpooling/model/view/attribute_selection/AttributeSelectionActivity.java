package gr.aueb.carpooling.model.view.attribute_selection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.view.LogIn.LogInActivity;
import gr.aueb.carpooling.model.view.create_route.CreateRouteActivity;
import gr.aueb.carpooling.model.view.driver.DriverFrontPage;
import gr.aueb.carpooling.model.view.passenger.PassengerFrontPage;

public class AttributeSelectionActivity extends AppCompatActivity {

    private ImageButton back_button;
    private Button passenger_button;
    private Button driver_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_attribute);

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
                openPassengerPage();
            }
        });

        driver_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDriverPage();
            }
        });
    }

    public void openLogInActivity() {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    public void openPassengerPage() {
        Intent intent = new Intent(this, PassengerFrontPage.class);
        startActivity(intent);
    }

    public void openDriverPage() {
        Intent intent = new Intent(this, CreateRouteActivity.class);
        startActivity(intent);
    }
}