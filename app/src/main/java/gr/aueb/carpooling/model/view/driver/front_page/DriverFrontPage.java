package gr.aueb.carpooling.model.view.driver.front_page;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.text.DecimalFormat;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.view.driver.DriverStatistics.DriverStatisticsActivity;
import gr.aueb.carpooling.model.view.driver.DriverTopUp.DriverTopUp;
import gr.aueb.carpooling.model.view.driver.ExistedRoutes.ExistedRouteActivity;
import gr.aueb.carpooling.model.view.driver.createRoute.CreateRouteActivity;
import gr.aueb.carpooling.model.view.driver.show_request.ShowRequestActivity;
import gr.aueb.carpooling.model.view.log_in.LogInActivity;

public class DriverFrontPage extends AppCompatActivity implements DriverFrontPageView {

    private String username;

    private final DriverDAO driverDAO = new DriverDAOmemory();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_front_page);

        DriverFrontPageViewModel viewModel = new ViewModelProvider(this).get(DriverFrontPageViewModel.class);

        viewModel.getPresenter().setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
        }


        ImageButton log_out_button = (ImageButton) findViewById(R.id.log_out);

        log_out_button.setOnClickListener(v -> openLogInActivity());

        Button create_route_button = (Button) findViewById(R.id.button_createroute);

        create_route_button.setOnClickListener(v -> openCreateRoutePage(username));


        Button show_existed_route_button = (Button) findViewById(R.id.button_showroutes);
        show_existed_route_button.setOnClickListener(v -> openExistedRoutePage(username));

        ImageButton wallet = (ImageButton) findViewById(R.id.Wallet);

        wallet.setOnClickListener(v -> openDriverTopUpActivity());

        Button show_request_button = (Button) findViewById(R.id.button_showrequests);

        show_request_button.setOnClickListener(v -> openShowRequestPage(username));

        Button statistics_button = (Button) findViewById(R.id.statistics);

        statistics_button.setOnClickListener(v -> openStatisticsPage( ));

        TextView rate = ((TextView) findViewById(R.id.RATE));
        rate.setText(new DecimalFormat("0.00").format(driverDAO.findByUsername(username).averageRating()));
    }

    public void openDriverTopUpActivity() {
        Intent intent = new Intent(this, DriverTopUp.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }
    public void openStatisticsPage() {
        Intent intent = new Intent(this, DriverStatisticsActivity.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    public void openCreateRoutePage(String username) {
        Intent intent = new Intent(this, CreateRouteActivity.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    public void openLogInActivity() {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    public void openExistedRoutePage(String username) {
        Intent intent = new Intent(this, ExistedRouteActivity.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    public void openShowRequestPage(String username) {
        Intent intent = new Intent(this, ShowRequestActivity.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }
}


