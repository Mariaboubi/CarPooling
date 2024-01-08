package gr.aueb.carpooling.model.view.driver.existed_routes;

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

import java.text.DecimalFormat;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.view.driver.front_page.DriverFrontPage;
import gr.aueb.carpooling.model.view.driver.rating_passengers.RatingPassengers;

public class ExistedRouteActivity extends AppCompatActivity implements ExistedRouteView,ExistedRouteRecyclerViewAdapter.RouteSelectionListener{
    private ExistedRouteViewModel viewModel;
    private String username;
    private RecyclerView recyclerView;
    private TextView emptyView;

    private final DriverDAO driverDAO= new DriverDAOmemory();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_show_existed_routes);

        viewModel = new ViewModelProvider(this).get(ExistedRouteViewModel.class);
        viewModel.getPresenter().setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
        }

        Driver driver = driverDAO.findByUsername(username);
        viewModel.getPresenter().setRouteList(driver);

        // ui initialization
        recyclerView = findViewById(R.id.ChooseRouteRecyclerView);
        emptyView = findViewById(R.id.NoRoutes);
        viewModel.getPresenter().onChangeLayout();

        findViewById(R.id.back_button).setOnClickListener(v -> openDriverFrontPage());

    }

    public void openDriverFrontPage(){
        Intent intent = new Intent(this , DriverFrontPage.class);
        intent.putExtra("Username",username);
        startActivity(intent);
    }

        @Override
        public void selectRoute (Route route){
            route.calculateTotalCost();
            driverDAO.findByUsername(username).topUp(route.getTotalCost());

            String total_cost =  new DecimalFormat("0.00").format(route.getTotalCost().getAmount());

            showErrorMessage("Route total cost",total_cost);

            Intent intent = new Intent(ExistedRouteActivity.this, RatingPassengers.class);

            intent.putExtra("RouteId",route.getId());
            intent.putExtra("Driver username", username);
            startActivity(intent);
        }


        @Override
        public void ShowNoRoutes () {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }

        @Override
        public void ShowRoutes () {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new ExistedRouteRecyclerViewAdapter(viewModel.getPresenter().getRouteList(), this));
        }
        public void showErrorMessage (String title, String message)
        {
            new AlertDialog.Builder(ExistedRouteActivity.this)
                    .setCancelable(true)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("OK", null).create().show();


        }
    }
