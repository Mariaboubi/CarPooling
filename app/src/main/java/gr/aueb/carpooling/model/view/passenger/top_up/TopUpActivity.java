package gr.aueb.carpooling.model.view.passenger.top_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.view.passenger.PassengerFrontPageActivity;

public class TopUpActivity extends AppCompatActivity implements TopUpView {

    /**
     * Σε αυτή την σελίδα ο χρήστης μπορεί να δει και να ανανεώσει το χρηματικό του υπόλοιπο
     */
    private ImageButton back_button;
    private int passengerId = -1;
    private TopUpViewModel viewModel;
    private TextView balanceText;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_top_up);

        back_button = (ImageButton) findViewById(R.id.back_button);
        viewModel = new TopUpViewModel(new PassengerDAOmemory());
        viewModel.getPresenter().setView(this);
        balanceText = findViewById(R.id.BalanceText);


        if (savedInstanceState == null)
        {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();

            if (extras != null) {
                passengerId = extras.getInt("PassengerId");
            }

        }
        viewModel.getPresenter().setPassenger();
        viewModel.getPresenter().setLayout();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            //The key argument here must match that used in the other activity
        }

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openPassengerFrontPageActivity(username);}
        });

        findViewById(R.id.topUp5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onTopUp(5.0);
            }
        });

        findViewById(R.id.topUp10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onTopUp(10.0);
            }
        });

        findViewById(R.id.topUp20).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onTopUp(20.0);
            }
        });

        findViewById(R.id.topUp50).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onTopUp(50.0);
            }
        });


    }

    @Override
    public void setBalance(String balance) {balanceText.setText(balance);}

    @Override
    public int getPassengerId() {return passengerId;}

    public void openPassengerFrontPageActivity(String username){
        Intent intent = new Intent(this, PassengerFrontPageActivity.class);
        intent.putExtra("Username",username);
        startActivity(intent);
    }
}