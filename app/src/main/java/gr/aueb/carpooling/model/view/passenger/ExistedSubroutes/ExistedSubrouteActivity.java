package gr.aueb.carpooling.model.view.passenger.ExistedSubroutes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.Request_status;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.dao.SubrouteDAO;
import gr.aueb.carpooling.model.memoryDao.SubrouteDAOmemory;
import gr.aueb.carpooling.model.view.driver.DriverFrontPage;
import gr.aueb.carpooling.model.view.driver.ExistedRoutes.ExistedRouteActivity;
import gr.aueb.carpooling.model.view.driver.RatingPassengers.RatingPassengers;
import gr.aueb.carpooling.model.view.log_in.LogInActivity;
import gr.aueb.carpooling.model.view.passenger.DriverRaiting.DriverRaitingActivity;
import gr.aueb.carpooling.model.view.passenger.PassengerFrontPageActivity;

public class ExistedSubrouteActivity extends AppCompatActivity implements ExistedSubrouteView,ExistedSubrouteRecyclerViewAdapter.SubrouteSelectionListener {

    private ExistedSubrouteViewModel viewModel;

    private String username;

    private RecyclerView recyclerView;

    private TextView emptyView;

    SubrouteDAO subrouteDAO= new SubrouteDAOmemory();

    @SuppressLint("MissingInflatedId")

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
        viewModel.getPresenter().setSubrouteList();

        recyclerView = findViewById(R.id.ChooseSubrouteRecyclerView);
        emptyView = findViewById(R.id.NoSubroutes);
        viewModel.getPresenter().onChangeLayout();

        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPessengerFrontPage();
            }
        });

    }

    @Override
    public void selectSubroute(Subroute subroute, Request_status status,Boolean b) {
        showErrorMessage("Dest",subroute.getDestination().toString());
        if(b){
            if (status == Request_status.APPROVED){
                Intent intent = new Intent(ExistedSubrouteActivity.this, DriverRaitingActivity.class);
                intent.putExtra("Username", username);
                intent.putExtra("SubrouteDest",subroute.getDestination().toString());
                intent.putExtra("SubroutePick",subroute.getPickupPoint().toString());
                intent.putExtra("SubrouteDate",subroute.getPickupTime().toString());
                startActivity(intent);
            }else if(status == Request_status.REJECTED){
                showErrorMessage("You can press button complete if request status is aproved.Now is: ", String.valueOf(Request_status.REJECTED));
                recyclerView = findViewById(R.id.ChooseSubrouteRecyclerView);
                emptyView = findViewById(R.id.NoSubroutes);
                viewModel.getPresenter().onChangeLayout();
            }else {
                showErrorMessage("Wait for answer.Now is: ", String.valueOf(Request_status.PENDING));
            }
        }else{
            if (status == Request_status.APPROVED){
                showErrorMessage("You can press button delete if request status is approved.Now is: ", String.valueOf(Request_status.APPROVED));
                recyclerView = findViewById(R.id.ChooseSubrouteRecyclerView);
                emptyView = findViewById(R.id.NoSubroutes);
                viewModel.getPresenter().onChangeLayout();
            }else if(status == Request_status.REJECTED){
                subrouteDAO.delete(subroute);
                viewModel.getPresenter().setSubrouteList();

                recyclerView = findViewById(R.id.ChooseSubrouteRecyclerView);
                emptyView = findViewById(R.id.NoSubroutes);
                viewModel.getPresenter().onChangeLayout();
            }else {
                showErrorMessage("Wait for answer.Now is: ", String.valueOf(subroute.getStatus()));
            }
        }

    }

    void openPessengerFrontPage() {
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

//        Intent intent = new Intent(ExistedSubrouteActivity.this, LogInActivity.class);
////        intent.putExtra("Username",username);
//        startActivity(intent);


    }

}