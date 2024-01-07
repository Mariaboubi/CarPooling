package gr.aueb.carpooling.model.view.driver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.memoryDao.MemoryInitialized;
import gr.aueb.carpooling.model.view.driver.DriverTopUp.DriverTopUp;
import gr.aueb.carpooling.model.view.driver.show_request.ShowRequestActivity;
import gr.aueb.carpooling.model.view.log_in.LogInActivity;
import gr.aueb.carpooling.model.view.driver.ExistedRoutes.ExistedRouteActivity;
import gr.aueb.carpooling.model.view.driver.createRoute.CreateRouteActivity;
import gr.aueb.carpooling.model.view.passenger.top_up.TopUpActivity;

public class DriverFrontPage extends AppCompatActivity implements DriverFrontPageView {

    private ImageButton log_out_button;

    private Button create_route_button;

    private Button show_request_button;

    private DriverFrontPageViewModel viewModel;

    private ImageButton wallet;

    private TextView rate;

    private String username;

    private DriverDAO driverDAO = new DriverDAOmemory();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_front_page);

        viewModel = new ViewModelProvider(this).get(DriverFrontPageViewModel.class);

        viewModel.getPresenter().setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            //The key argument here must match that used in the other activity
        }


        log_out_button = (ImageButton) findViewById(R.id.log_out);

        log_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogInActivity();
            }
        });

        create_route_button = (Button) findViewById(R.id.button_createroute);

        create_route_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateRoutePage(username);
            }
        });


        Button show_existed_route_button = (Button) findViewById(R.id.button_showroutes);
        show_existed_route_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExistedRoutePage(username);
            }
        });

        wallet = (ImageButton) findViewById(R.id.Wallet);

        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDriverTopUpActivity();
            }
        });

        show_request_button = (Button) findViewById(R.id.button_showrequests);

        show_request_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShowRequestPage(username);
            }
        });

        rate = ((TextView) findViewById(R.id.RATE));
        rate.setText(new DecimalFormat("0.00").format(driverDAO.findByUsername(username).averageRating()));
    }

    public void openDriverTopUpActivity() {
        Intent intent = new Intent(this, DriverTopUp.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    public void openCreateRoutePage(String userId) {
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
//    void openCreateRoutePage(String userId) {
//        Intent intent = new Intent(this, CreateRouteActivity.class);
//        intent.putExtra("Username", username);
//        startActivity(intent);
//    }
//    void openLogInActivity() {
//        Intent intent = new Intent(this, LogInActivity.class);
//        startActivity(intent);
//    }
//
//    void openExistedRoutePage(String username) {
//        Intent intent = new Intent(this, ExistedRouteActivity.class);
//        intent.putExtra("Username", username);
//        startActivity(intent);
//    }
//
//    public void openCreateRoutePage(String userId) {
//        Intent intent = new Intent(this, CreateRouteActivity.class);
//        intent.putExtra("Username", username);
//        startActivity(intent);
//    }
//    public void openLogInActivity() {
//        Intent intent = new Intent(this, LogInActivity.class);
//        startActivity(intent);
//    }
//
//    public void openExistedRoutePage(String username) {
//        Intent intent = new Intent(this, ExistedRouteActivity.class);
//        intent.putExtra("Username", username);
//        startActivity(intent);
//    }

