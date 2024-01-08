package gr.aueb.carpooling.model.view.driver.top_up;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.view.driver.front_page.DriverFrontPage;

public class DriverTopUp extends AppCompatActivity implements DriverTopUpView {


    private TextView balanceText;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_top_up);

        ImageButton back_button = (ImageButton) findViewById(R.id.back_button);
        balanceText = findViewById(R.id.BalanceText);

        DriverTopUpViewModel viewModel = new DriverTopUpViewModel(new DriverDAOmemory());
        viewModel.getPresenter().setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
        }

        viewModel.getPresenter().setDriver();
        viewModel.getPresenter().setLayout();


        back_button.setOnClickListener(v -> openDriverFrontPageActivity(username));

    }

    public void setBalance(String balance) {balanceText.setText(balance);}

    @Override
    public String getDriverUsername() {
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