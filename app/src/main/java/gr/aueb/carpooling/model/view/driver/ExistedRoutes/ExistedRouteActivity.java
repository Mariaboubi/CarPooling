package gr.aueb.carpooling.model.view.driver.ExistedRoutes;

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
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.view.LogIn.LogInActivity;
import gr.aueb.carpooling.model.view.driver.DriverFrontPage;

public class ExistedRouteActivity extends AppCompatActivity implements ExitedRouteView,ExistedRouteRecyclerViewAdapter.RouteSelectionListener{
    private ExistedRouteViewModel viewModel;
    private String username;
    private RecyclerView recyclerView;
    private TextView emptyView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existed_route);

        viewModel = new ViewModelProvider(this).get(ExistedRouteViewModel.class);
        viewModel.getPresenter().setView(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            //The key argument here must match that used in the other activity
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
//        intent.putExtra("RouteId",route.getId());
        intent.putExtra("Username",username);
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
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(ExistedRouteActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();

        Intent intent = new Intent(ExistedRouteActivity.this, LogInActivity.class);
//        intent.putExtra("Username",username);
        startActivity(intent);

    }
}