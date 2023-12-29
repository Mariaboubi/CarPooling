package gr.aueb.carpooling.model.view.sign_up;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.contact.EmailAddress;

public class SignUpActivity extends AppCompatActivity implements SignUpView {

    private SignUpViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
                .setTitle("Επιτυχής δημιουργία λογαριασμού")
                .setMessage("Ο λαγαριασμος δημιουργήθηκε με επιτυχία")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
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
        EditText et_password = findViewById(R.id.SignUpPassword);
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
}