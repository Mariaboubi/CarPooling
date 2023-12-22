package gr.aueb.carpooling.model.view.LogIn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.memoryDao.MemoryInitialized;

public class LogInActivity extends AppCompatActivity implements LogInView{

    private LoginViewModel viewModel;

    private static boolean initialized = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        MemoryInitialized dataHelper = new MemoryInitialized();
        dataHelper.prepareData();

        LoginViewModel viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        viewModel.getPresenter().setView(this);
        if (savedInstanceState == null){
            Intent intent = getIntent();

        }

//        findViewById(R.id.SignUpCustomerButton).setOnClickListener(new android.view.View.OnClickListener() { //το κουμπί όταν θέλει να εγγραφτεί νέος πελάτης στην εφαμοργή
//            public void onClick(View v) {
//                viewModel.getPresenter().onSignup();
//            }
//        });
    }

    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(LogInActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    public void showUserFoundMessage(int id)
    {
        new AlertDialog.Builder(LogInActivity.this)
                .setCancelable(true)
                .setTitle("Συγχαρητήρια")
                .setMessage("Τα στοιχεία που παραχωρήσατε είναι σωστα")
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        redirectToChooseCharacterPage(id);
                    }
                }).create().show();
    }

    public String ExtractUsername()
    {
        return ((EditText)findViewById(R.id.usernameText)).getText().toString().trim();

    }

    public String ExtractPassword()
    {
        return ((EditText)findViewById(R.id.password_text)).getText().toString().trim();
    }

    public void signup(){
//        Intent intent = new Intent(LogInActivity.this, SignUpCustomerActivity.class);
//        startActivity(intent);
    }

    public void redirectToChooseCharacterPage(int customerId){
//        Intent intent = new Intent(LogInActivity.this, ChooseRestaurantActivity.class);
//        intent.putExtra("CustomerId",customerId);
//        startActivity(intent);
    }


}