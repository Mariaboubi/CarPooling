package gr.aueb.carpooling.model.view.passenger.top_up;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.view.passenger.front_page.PassengerFrontPageActivity;

public class TopUpActivity extends AppCompatActivity implements TopUpView {


    private TopUpViewModel viewModel;
    private TextView balanceText;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_top_up);

        ImageButton back_button = (ImageButton) findViewById(R.id.back_button);
        balanceText = findViewById(R.id.BalanceText);

        viewModel = new TopUpViewModel(new PassengerDAOmemory());
        viewModel.getPresenter().setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
        }

        viewModel.getPresenter().setPassenger();
        viewModel.getPresenter().setLayout();


        back_button.setOnClickListener(v -> openPassengerFrontPageActivity(username));

        findViewById(R.id.topUp5).setOnClickListener(v -> viewModel.getPresenter().onTopUp(5.0));

        findViewById(R.id.topUp10).setOnClickListener(v -> viewModel.getPresenter().onTopUp(10.0));

        findViewById(R.id.topUp20).setOnClickListener(v -> viewModel.getPresenter().onTopUp(20.0));

        findViewById(R.id.topUp50).setOnClickListener(v -> viewModel.getPresenter().onTopUp(50.0));


    }

    public void showErrorMessage (String title, String message)
    {
        new AlertDialog.Builder(TopUpActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();



    }

    @Override
    public void setBalance(String balance) {balanceText.setText(balance);}

    @Override
    public String getPassengerUername() {
        return username;
    }


    public void openPassengerFrontPageActivity(String username){
        Intent intent = new Intent(this, PassengerFrontPageActivity.class);
        intent.putExtra("Username",username);
        startActivity(intent);
    }
}