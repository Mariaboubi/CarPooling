package gr.aueb.carpooling.model.view.passenger.search_route;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.view.driver.ExistedRoutes.ExistedRouteActivity;
import gr.aueb.carpooling.model.view.driver.ExistedRoutes.ExistedRouteRecyclerViewAdapter;
import gr.aueb.carpooling.model.view.log_in.LogInActivity;
import gr.aueb.carpooling.model.view.passenger.PassengerFrontPageActivity;
import gr.aueb.carpooling.model.view.subroute.subrouteActivity;

public class SearchRouteActivity extends AppCompatActivity implements SearchRouteView, SearchRouteRecyclerViewAdapter.SearchRouteSelectionListener {

    private SearchRouteViewModel viewModel;

    private RecyclerView recyclerView;

    private SearchRouteView view;
    private String username;

    private TextView emptyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_request_route);

        viewModel = new ViewModelProvider(this).get(SearchRouteViewModel.class);
        viewModel.getPresenter().setView(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            //The key argument here must match that used in the other activity
        }

        viewModel.getPresenter().setRouteList();
        int l = viewModel.getPresenter().getRouteList().size();
        showErrorMessage("len",String.valueOf(l));
        // ui initialization
        recyclerView = findViewById(R.id.ChooseRouteRecyclerView);
        emptyView = findViewById(R.id.NoRoutes);
        viewModel.getPresenter().onChangeLayout();
        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openPassengerFrontPage();
            }
        });
    }



    void openPassengerFrontPage() {
        Intent intent = new Intent(this, PassengerFrontPageActivity.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

//    @Override
    public void selectRoute(Route route) {
//        Intent intent = new Intent(SearchRouteActivity.this, subrouteActivity.class);
//        intent.putExtra("Username",username);
//        startActivity(intent);
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
        recyclerView.setAdapter(new ExistedRouteRecyclerViewAdapter(viewModel.getPresenter().getRouteList(), (ExistedRouteRecyclerViewAdapter.RouteSelectionListener) this));
    }

    public void showErrorMessage (String title, String message)
    {
        new AlertDialog.Builder(SearchRouteActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();

    }

}