package gr.aueb.carpooling.model.view.driver.show_request;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;
import gr.aueb.carpooling.model.view.driver.front_page.DriverFrontPage;


public class ShowRequestActivity extends AppCompatActivity implements ShowRequestView, ShowRequestRecyclerViewAdapter.ShowRequestListener {

    private ShowRequestViewModel viewModel;

    private String username;

    private RecyclerView recyclerView;
    private TextView emptyView;

    private final DriverDAO driverDAO = new DriverDAOmemory();

    private final RouteDAO routeDAO = new RouteDAOmemory();

    private ArrayList<Route> routes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_show_request);

        viewModel = new ViewModelProvider(this).get(ShowRequestViewModel.class);
        viewModel.getPresenter().setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
        }


        Driver driver = driverDAO.findByUsername(username);

        routes = (ArrayList<Route>) routeDAO.findByDriver(driver);

        viewModel.getPresenter().setSubrouteList(routes);

        // ui initialization
        recyclerView = findViewById(R.id.ShowRequestRecyclerView);
        emptyView = findViewById(R.id.NoRequests);
        viewModel.getPresenter().onChangeLayout();

        findViewById(R.id.back_button).setOnClickListener(v -> openDriverFrontPage(username));


    }

    @Override
    public void refreshRequests() {
        Intent intent = new Intent(ShowRequestActivity.this, ShowRequestActivity.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    @Override
    public void ShowNoRequests() {
        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void ShowRequests() {
        recyclerView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ShowRequestRecyclerViewAdapter(viewModel.getPresenter().getSubrouteList(), this, routes));
    }

    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(ShowRequestActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();

    }

    @Override
    public void openDriverFrontPage(String username) {
        Intent intent = new Intent(this, DriverFrontPage.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

}