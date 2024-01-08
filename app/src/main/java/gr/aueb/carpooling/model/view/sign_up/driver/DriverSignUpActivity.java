package gr.aueb.carpooling.model.view.sign_up.driver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.view.log_in.LogInActivity;
import gr.aueb.carpooling.model.view.driver.front_page.DriverFrontPage;

public class DriverSignUpActivity extends AppCompatActivity implements DriverSignUpView{

    private DriverSignUpViewModel viewModel;
    private Button btnSignUp;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_driver);

        viewModel = new ViewModelProvider(this).get(DriverSignUpViewModel.class);

        viewModel.getPresenter().setView(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
        }



        TextView txtSignIn = (TextView) findViewById(R.id.txtSignIn);
        txtSignIn.setOnClickListener(v -> openLogInActivity());

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(v -> viewModel.getPresenter().onCreateDriverAccount(username));
    }


    private void openLogInActivity() {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    @Override
    public String getDriverLicense() {
        EditText et_driver_license = findViewById(R.id.SignUpLicenseNumber);
        return et_driver_license.getText().toString().trim();
    }

    @Override
    public String getCarType() {
        EditText et_car_type = findViewById(R.id.SignUpCarType);
        return et_car_type.getText().toString().trim();
    }

    @Override
    public String getIban() {
        EditText et_iban = findViewById(R.id.SignUpIban);
        return et_iban.getText().toString().trim();
    }

    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(DriverSignUpActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    @Override
    public void showRegistrationSuccessMessage(String title,String message) {

        showErrorMessage(title,message);
        btnSignUp.setOnClickListener(v -> openDriverFrontPage());
    }

    private void openDriverFrontPage() {
        Intent intent = new Intent(DriverSignUpActivity.this, DriverFrontPage.class);
        intent.putExtra("Username",username);
        startActivity(intent);
    }
}