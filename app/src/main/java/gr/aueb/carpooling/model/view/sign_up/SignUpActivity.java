package gr.aueb.carpooling.model.view.sign_up;

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
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.view.LogIn.LogInActivity;
import gr.aueb.carpooling.model.view.LogIn.LoginViewModel;
import gr.aueb.carpooling.model.view.attribute_selection.AttributeSelectionActivity;

public class SignUpActivity extends AppCompatActivity implements SignUpView {
    private SignUpViewModel viewModel;
    private Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        viewModel.getPresenter().setView(this);

        if (savedInstanceState == null){
            Intent intent = getIntent();
        }

        ImageButton back_button = (ImageButton) findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> openLogInActivity());

        TextView txtSignIn = (TextView) findViewById(R.id.txtSignIn);
        txtSignIn.setOnClickListener(v -> openLogInActivity());

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onCreateUserAccount();
            };
        });
    }

    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(SignUpActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    public void showRegistrationSuccessMessage() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogInActivity();
            }
        });

    }

    public String getName() {
        return ((EditText)findViewById(R.id.SignUpName)).getText().toString().trim();
    }

    public String getSurname() {
        return ((EditText)findViewById(R.id.SignUpSurname)).getText().toString().trim();
    }

    public String getAge() {
        EditText et_age = findViewById(R.id.SignUpAge);
        return et_age.getText().toString().trim();
    }

    public String getEmail() {
        EditText et_email = findViewById(R.id.SignUpEmail);
        return et_email.getText().toString().trim();
    }

    public String getPhoneNumber() {
        EditText et_phone = findViewById(R.id.SignUpPhone);
        return et_phone.getText().toString().trim();
    }

    public String getCardNumber() {
        return ((EditText)findViewById(R.id.SignUpCardNumber)).getText().toString().trim();
    }
    public String getCardHolderName() {
        return ((EditText)findViewById(R.id.SignUpCardHolderName)).getText().toString().trim();
    }
    public String getCVV() {
        EditText et_credit_card = findViewById(R.id.SignUpCVV);
        return et_credit_card.getText().toString().trim();
    }

    public String getUsername() {
        EditText et_username = findViewById(R.id.SignUpUsername);
        return et_username.getText().toString().trim();
    }

    public String getPassword() {
        EditText et_password = findViewById(R.id.password);
        return et_password.getText().toString().trim();
    }

    public String getPasswordVerification() {
        EditText et_password_verification = findViewById(R.id.SignUpConfirmPassword);
        return et_password_verification.getText().toString().trim();
    }

    public String getDriverLicense() {
        EditText et_driver_license = findViewById(R.id.SignUpLicenseNumber);
        return et_driver_license.getText().toString().trim();
    }

    @Override
    public String getCarType() {
        EditText et_car_type = findViewById(R.id.SignUpCarType);
        return et_car_type.getText().toString().trim();
    }

    public String getIban() {
        EditText et_iban = findViewById(R.id.SignUpIban);
        return et_iban.getText().toString().trim();
    }

    private void openLogInActivity() {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    private void openAttributeSelectionActivity() {
        Intent intent = new Intent(SignUpActivity.this, AttributeSelectionActivity.class);
        startActivity(intent);
    }
}