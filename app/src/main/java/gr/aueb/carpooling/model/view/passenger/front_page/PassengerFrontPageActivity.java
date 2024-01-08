package gr.aueb.carpooling.model.view.passenger.front_page;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.text.DecimalFormat;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.view.log_in.LogInActivity;
import gr.aueb.carpooling.model.view.passenger.statistics.PassengerStatisticsActivity;
import gr.aueb.carpooling.model.view.passenger.existed_subroutes.ExistedSubrouteActivity;
import gr.aueb.carpooling.model.view.passenger.subroute.subrouteActivity;
import gr.aueb.carpooling.model.view.passenger.top_up.TopUpActivity;

public class PassengerFrontPageActivity extends AppCompatActivity implements PassengerFrontPageView {

    private final PassengerDAO passengerDAO= new PassengerDAOmemory();

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_front_page);


        PassengerFrontPageViewModel viewModel = new ViewModelProvider(this).get(PassengerFrontPageViewModel.class);

        viewModel.getPresenter().setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
        }

        ImageButton log_out_button = (ImageButton) findViewById(R.id.log_out);
        ImageButton wallet = (ImageButton) findViewById(R.id.Wallet);
        Button createSubroute_button = (Button) findViewById(R.id.createRouteButton);
        Button showSubroutes_button = (Button) findViewById(R.id.showRoutesButton);

        log_out_button.setOnClickListener(v -> openLogInActivity());

        TextView rate = ((TextView) findViewById(R.id.RATE));

        String avg_rating = new DecimalFormat("0.00").format(passengerDAO.findByUsername(username).averageRating());
         rate.setText(avg_rating);

        wallet.setOnClickListener(v -> openTopUpActivity());

        Button statistics_button = (Button) findViewById(R.id.statistics);

        statistics_button.setOnClickListener(v -> openStatisticsPage( ));

        createSubroute_button.setOnClickListener(v -> openCreateRoutePage(username));
        createSubroute_button.setOnClickListener(v -> openCreateRoutePage(username));

        showSubroutes_button.setOnClickListener(v -> openShowRoutesPage());


    }

    public void openLogInActivity() {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    public void openTopUpActivity(){
        Intent intent = new Intent(this , TopUpActivity.class);
        intent.putExtra("Username",username);
        startActivity(intent);
    }

    public void openStatisticsPage(){
        Intent intent = new Intent(this , PassengerStatisticsActivity.class);
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