package gr.aueb.carpooling.model.view.passenger.ExistedSubroutes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Request_status;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.dao.SubrouteDAO;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;
import gr.aueb.carpooling.model.memoryDao.SubrouteDAOmemory;
import gr.aueb.carpooling.model.view.driver.createRoute.CreateRouteActivity;
import gr.aueb.carpooling.model.view.passenger.DriverRating.DriverRatingActivity;
import gr.aueb.carpooling.model.view.passenger.front_page.PassengerFrontPageActivity;
import gr.aueb.carpooling.model.view.passenger.top_up.TopUpActivity;

public class ExistedSubrouteActivity extends AppCompatActivity implements ExistedSubrouteView, ExistedSubrouteRecyclerViewAdapter.SubrouteSelectionListener {

    private ExistedSubrouteViewModel viewModel;

    private String username;

    private RecyclerView recyclerView;

    private TextView emptyView;

    private  Passenger passenger;

    // DAOs
    private final SubrouteDAO subrouteDAO = new SubrouteDAOmemory();
    private final PassengerDAO passengerDAO = new PassengerDAOmemory();

    private final RouteDAO routeDAO = new RouteDAOmemory();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_existed_subroute);

        viewModel = new ViewModelProvider(this).get(ExistedSubrouteViewModel.class);
        viewModel.getPresenter().setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            //The key argument here must match that used in the other activity
        }
        passenger = passengerDAO.findByUsername(username);
        viewModel.getPresenter().setSubrouteList(passenger);

        recyclerView = findViewById(R.id.ChooseSubrouteRecyclerView);
        emptyView = findViewById(R.id.NoSubroutes);
        viewModel.getPresenter().onChangeLayout();

        findViewById(R.id.back_button).setOnClickListener(v -> openPassengerFrontPage());

    }

    @Override

    public void selectSubroute(Subroute subroute, Request_status status,Boolean b) {

        if(b){
            if (status == Request_status.APPROVED){
                boolean success = payment(subroute);
                if(success){
                    subroute.setStatus(Request_status.COMPLETED);
                    Intent intent = new Intent(ExistedSubrouteActivity.this, DriverRatingActivity.class);
                    intent.putExtra("Username", username);
                    intent.putExtra("SubrouteId", subroute.getId());
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(ExistedSubrouteActivity.this,TopUpActivity.class);
                    intent.putExtra("Username", username);
                    startActivity(intent);
                }

            } else if (status == Request_status.REJECTED) {
                showErrorMessage("You can press button complete if request status is approved. Current status ", String.valueOf(Request_status.REJECTED));
            } else if(status == Request_status.PENDING){
                showErrorMessage("Wait for driver's answer. Current status: ", String.valueOf(Request_status.PENDING));
            } else if(status == Request_status.COMPLETED) {
                showErrorMessage("You have already completed this route. Current status: ", String.valueOf(Request_status.COMPLETED));
            }
        } else {
            if (status == Request_status.APPROVED) {
                showErrorMessage("You can't press button delete if request status is approved.Now is: ", String.valueOf(Request_status.APPROVED));
            }else{
                    Route route = routeDAO.findRouteBySubroute(subroute);
                    route.removeSubroute(subroute);
                    subrouteDAO.delete(subroute);
                    Intent intent = new Intent(this, this.getClass());
                    intent.putExtra("Username", username);
                    startActivity(intent);
            }


        }

    }

    void openPassengerFrontPage() {
        Intent intent = new Intent(this, PassengerFrontPageActivity.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    @Override
    public void ShowNoSubroutes() {
        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void ShowSubroutes() {
        recyclerView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ExistedSubrouteRecyclerViewAdapter(viewModel.getPresenter().getSubrouteList(), this));
    }

    @Override
    public void showErrorMessage(String title, String message) {

        new AlertDialog.Builder(ExistedSubrouteActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();


    }

    @Override
    public boolean payment(Subroute subroute) {

//        Money pass_balance = passenger.getBalance();
//        // money to double
//        double pass_money = pass_balance.getAmount();
//        // money to string
//        String pass_moneyString = String.valueOf(pass_money);


        // Cost of the subroute
        Money cost_of_ride = subroute.calculateCost();
        // money to double
        double money = cost_of_ride.getAmount();
        // money to string
        String moneyString = String.valueOf(money);

        boolean success = passenger.transaction(cost_of_ride);
        if(!success){
            showErrorMessage("Payment failed, please put money in the app. The ride costs:",moneyString);
        }else{
            showErrorMessage("Payment successful. The ride costs:",moneyString);
        }
        return success;
    }
}
