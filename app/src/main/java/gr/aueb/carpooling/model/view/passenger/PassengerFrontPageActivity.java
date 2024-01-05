package gr.aueb.carpooling.model.view.passenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.memoryDao.MemoryInitialized;
import gr.aueb.carpooling.model.view.log_in.LogInActivity;
import gr.aueb.carpooling.model.view.passenger.ExistedSubroutes.ExistedSubrouteActivity;
import gr.aueb.carpooling.model.view.passenger.top_up.TopUpActivity;
import gr.aueb.carpooling.model.view.subroute.subrouteActivity;

public class PassengerFrontPageActivity extends AppCompatActivity implements PassengerFrontPageView {

    private ImageButton log_out_button;

    private ImageButton wallet;

    private Button CreateSubroute_button;
    private Button ShowSubroutes_button;

    private  PassengerFrontPageViewModel viewModel;

    private String username;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_front_page);


        viewModel= new ViewModelProvider(this).get(PassengerFrontPageViewModel.class);

        viewModel.getPresenter().setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            //The key argument here must match that used in the other activity
        }

        log_out_button = (ImageButton) findViewById(R.id.log_out);
        wallet = (ImageButton) findViewById(R.id.Wallet);
        CreateSubroute_button = (Button) findViewById(R.id.createRouteButton);
        ShowSubroutes_button = (Button) findViewById(R.id.showRoutesButton);

        log_out_button.setOnClickListener(v -> openLogInActivity());

        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openTopUpActivity();}
        });

        CreateSubroute_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openCreateRoutePage(username);}
        });
        CreateSubroute_button.setOnClickListener(v -> openCreateRoutePage(username));

        ShowSubroutes_button.setOnClickListener(v -> openShowRoutesPage());


    }

    void openLogInActivity() {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    void openTopUpActivity(){
        Intent intent = new Intent(this , TopUpActivity.class);
        intent.putExtra("Username",username);
        startActivity(intent);
    }

    public void openCreateRoutePage(String username) {
        Intent intent = new Intent(this, subrouteActivity.class);
        intent.putExtra("Username",username);
        startActivity(intent);
    }

    public void openShowRoutesPage() {
        Intent intent = new Intent(this, ExistedSubrouteActivity.class);
        intent.putExtra("Username",username);
        startActivity(intent);
    }


}