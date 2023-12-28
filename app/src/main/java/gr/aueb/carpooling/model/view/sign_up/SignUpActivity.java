package gr.aueb.carpooling.model.view.sign_up;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.contact.EmailAddress;

public class SignUpActivity extends AppCompatActivity implements SignUpView {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        SignUpViewModel viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        viewModel.getPresenter().setView(this);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
        }

        findViewById(R.id.Sign_up_button).setOnClickListener(new View.OnClickListener(){ // το κουμπί για να δημιουργηθεί ο λογαριασμός
            @Override
            public void onClick(View v){
                viewModel.getPresenter().onCreateUserAccount();
            }

        });


         /**
          findViewById(R.id.gobackButton).setOnClickListener(new View.OnClickListener(){// το κουμπί για να επιστρέψει πίσω
            @Override
            public void onClick(View v){
                viewModel.getPresenter().onBack();
            }
        });
         */


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
        EditText et_name = findViewById(R.id.name1_input);
        return et_name.getText().toString();
    }

    public String getSurname() {
        EditText et_surname = findViewById(R.id.surname_input);
        return et_surname.getText().toString();
    }

    public Integer getAge() {
        EditText et_age = findViewById(R.id.age_input);
        return Integer.parseInt(et_age.getText().toString());
    }

    public EmailAddress getEmail() {
        EditText et_email = findViewById(R.id.email_input);
        return new EmailAddress(et_email.getText().toString());
    }

    public String getPhoneNumber() {
        EditText et_phone_number = findViewById(R.id.tel_input);
        return et_phone_number.getText().toString();
    }


    public String getUsername() {
        EditText et_username = findViewById(R.id.username_input);
        return et_username.getText().toString();
    }

    public String getPassword() {
        EditText et_password = findViewById(R.id.password_input);
        return et_password.getText().toString();
    }

    public String getPasswordVerification() {
        EditText et_password_verification = findViewById(R.id.passwordVerification_input);
        return et_password_verification.getText().toString();
    }

    public String getDriverLicense() {
        EditText et_driver_license = findViewById(R.id.driver_license_input);
        return et_driver_license.getText().toString();
    }

    @Override
    public String getCarType() {
        EditText et_car_type = findViewById(R.id.car_type_input);
        return et_car_type.getText().toString();
    }

    @Override
    public String getIban() {
        EditText et_iban_type = findViewById(R.id.idan_input);
        return et_iban_type.getText().toString();
    }

    public String getCreditCard() {
        EditText et_credit_card = findViewById(R.id.Credit_card_input);
        return et_credit_card.getText().toString();
    }

    @Override
    public String getCardHolderName() {
        EditText et_card_name_holder = findViewById(R.id.nameCard_input);
        return et_card_name_holder.getText().toString();
    }

    @Override
    public String getCvv() {
        EditText et_cvv = findViewById(R.id.cvv_input);
        return et_cvv.getText().toString();
    }


    @Override
    public void goBack() {finish();} //Μας πηγενει στο προιγουμενο activity
}