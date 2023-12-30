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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        SignUpViewModel viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        viewModel.getPresenter().setView(this);

        if (savedInstanceState == null){
            Intent intent = getIntent();
        }

        ImageButton back_button = (ImageButton) findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> openLogInActivity());

        TextView txtSignIn = (TextView) findViewById(R.id.txtSignIn);
        txtSignIn.setOnClickListener(v -> openLogInActivity());

        Button btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onCreateUserAccount();
                openAttributeSelectionActivity();
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
        new AlertDialog.Builder(SignUpActivity.this)
                .setCancelable(true)
                .setTitle("Account created successfully")
//                .setMessage("Ο λαγαριασμος δημιουργήθηκε με επιτυχία")
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                    finish();
                }).create().show();

    }

    public String getName() {
        EditText et_name = findViewById(R.id.SignUpName);
        return et_name.getText().toString();
    }

    public String getSurname() {
        EditText et_surname = findViewById(R.id.SignUpSurname);
        return et_surname.getText().toString();
    }

    public Integer getAge() {
        EditText et_age = findViewById(R.id.SignUpAge);
        return Integer.parseInt(et_age.getText().toString());
    }

    public EmailAddress getEmail() {
        EditText et_email = findViewById(R.id.SignUpEmail);
        return new EmailAddress(et_email.getText().toString());
    }

    public String getPhoneNumber() {
        EditText et_phone_number = findViewById(R.id.SignUpPhone);
        return et_phone_number.getText().toString();
    }

    public String getCardNumber() {
        EditText et_credit_card = findViewById(R.id.SignUpCardNumber);
        return et_credit_card.getText().toString();
    }
    public String getCardHolderName() {
        EditText et_credit_card = findViewById(R.id.SignUpCardHolderName);
        return et_credit_card.getText().toString();
    }
    public String getCVV() {
        EditText et_credit_card = findViewById(R.id.SignUpCVV);
        return et_credit_card.getText().toString();
    }

    public String getUsername() {
        EditText et_username = findViewById(R.id.SignUpUsername);
        return et_username.getText().toString();
    }

    public String getPassword() {
        EditText et_password = findViewById(R.id.password);
        return et_password.getText().toString();
    }

    public String getPasswordVerification() {
        EditText et_password_verification = findViewById(R.id.SignUpConfirmPassword);
        return et_password_verification.getText().toString();
    }

    public String getDriverLicense() {
        EditText et_driver_license = findViewById(R.id.SignUpLicenseNumber);
        return et_driver_license.getText().toString();
    }

    @Override
    public String getCarType() {
        EditText et_car_type = findViewById(R.id.SignUpCarType);
        return et_car_type.getText().toString();
    }

    public String getIban() {
        EditText et_iban = findViewById(R.id.SignUpIban);
        return et_iban.getText().toString();
    }


    @Override
    public void goBack() {finish();}

    public void openLogInActivity() {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    public void openAttributeSelectionActivity() {
        Intent intent = new Intent(this, AttributeSelectionActivity.class);
        startActivity(intent);
    }
}