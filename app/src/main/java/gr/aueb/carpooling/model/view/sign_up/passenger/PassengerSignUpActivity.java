package gr.aueb.carpooling.model.view.sign_up.passenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.view.LogIn.LogInActivity;
import gr.aueb.carpooling.model.view.attribute_selection.AttributeSelectionActivity;
import gr.aueb.carpooling.model.view.driver.DriverFrontPage;
import gr.aueb.carpooling.model.view.passenger.PassengerFrontPageActivity;
import gr.aueb.carpooling.model.view.sign_up.driver.DriverSignUpActivity;


public class PassengerSignUpActivity extends AppCompatActivity implements PassengerSignUpView{

    private PassengerSignUpViewModel viewModel;
    private Button btnSignUp;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_sign_up);

        viewModel = new ViewModelProvider(this).get(PassengerSignUpViewModel.class);

        viewModel.getPresenter().setView(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            //The key argument here must match that used in the other activity
        }

        ImageButton back_button = (ImageButton) findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> openAttributeSelectionPage());

        TextView txtSignIn = (TextView) findViewById(R.id.txtSignIn);
        txtSignIn.setOnClickListener(v -> openLogInActivity());

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onCreatePassengerAccount(username);

            };
        });
    }

    private void openAttributeSelectionPage() {
        Intent intent = new Intent(PassengerSignUpActivity.this, AttributeSelectionActivity.class);
        startActivity(intent);
    }

    private void openLogInActivity() {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    public String getCardNumber(){
        return ((EditText)findViewById(R.id.SignUpCardNumber)).getText().toString().trim();
    }

    public String getCardHolderName(){
        return ((EditText)findViewById(R.id.SignUpCardHolderName)).getText().toString().trim();
    }

    public String getCVV(){
        EditText et_credit_card = findViewById(R.id.SignUpCVV);
        return et_credit_card.getText().toString().trim();
    }

    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(PassengerSignUpActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    @Override
    public void showRegistrationSuccessMessage(String title,String message) {
        showErrorMessage(title, message);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPassengerFrontPage();
            }
        });

    }
    private void openPassengerFrontPage() {
        Intent intent = new Intent(PassengerSignUpActivity.this, PassengerFrontPageActivity.class);
        intent.putExtra("Username",username);
        startActivity(intent);
    }
}