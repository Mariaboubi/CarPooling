package gr.aueb.carpooling.model.view.driver.DriverTopUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.view.driver.DriverFrontPage;

public class DriverTopUp extends AppCompatActivity implements DriverTopUpView {


    private ImageButton back_button;

    private DriverTopUpViewModel viewModel;
    private TextView balanceText;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_top_up);

        back_button = (ImageButton) findViewById(R.id.back_button);
        balanceText = findViewById(R.id.BalanceText);

        viewModel = new DriverTopUpViewModel(new DriverDAOmemory());
        viewModel.getPresenter().setView(this);

//
//
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            //The key argument here must match that used in the other activity
        }
//
        viewModel.getPresenter().setDriver();
        viewModel.getPresenter().setLayout();


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openDriverFrontPageActivity(username);}
        });

    }

    public void setBalance(String balance) {balanceText.setText(balance);}

    @Override
    public String getDriverUername() {
        return username;
    }

    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(DriverTopUp.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();




    }


    public void openDriverFrontPageActivity(String username){
        Intent intent = new Intent(this, DriverFrontPage.class);
        intent.putExtra("Username",username);
        startActivity(intent);
    }
}