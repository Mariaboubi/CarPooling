package gr.aueb.carpooling.model.view.driver.ExistedRoutes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.view.driver.DriverFrontPage;

public class ExistedRouteActivity extends AppCompatActivity implements ExitedRouteView,ExistedRouteRecyclerViewAdapter.RouteSelectionListener{
    ExistedRouteViewModel viewModel;
    int driverId;
    RecyclerView recyclerView;
    TextView emptyView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existed_route);

        viewModel = new ViewModelProvider(this).get(ExistedRouteViewModel.class);
        viewModel.getPresenter().setView(this);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            driverId = extras.getInt("DriverId");
        }
        viewModel.getPresenter().setRouteList();
        // ui initialization
        recyclerView = findViewById(R.id.ChooseRouteRecyclerView);
        emptyView = findViewById(R.id.NoRoutes);
        viewModel.getPresenter().onChangeLayout();
        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                viewModel.getPresenter().onBack();
            }
        });
    }

    @Override
    public void selectRoute(Route route) {
        Intent intent = new Intent(ExistedRouteActivity.this, DriverFrontPage.class);
        intent.putExtra("RouteId",route.getId());
        intent.putExtra("DriverId",driverId);
        startActivity(intent);
    }

    @Override
    public void goBack() {

    }

    @Override
    public void ShowNoRoutes() {
        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void ShowRoutes() {
        recyclerView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ExistedRouteRecyclerViewAdapter(viewModel.getPresenter().getRouteList(), this));
    }
}