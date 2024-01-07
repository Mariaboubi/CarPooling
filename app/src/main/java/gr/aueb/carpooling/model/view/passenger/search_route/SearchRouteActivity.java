package gr.aueb.carpooling.model.view.passenger.search_route;

import static gr.aueb.carpooling.model.Request_status.PENDING;

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
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.view.passenger.front_page.PassengerFrontPageActivity;

public class SearchRouteActivity extends AppCompatActivity implements SearchRouteView, SearchRouteRecyclerViewAdapter.SearchRouteSelectionListener {

    private SearchRouteViewModel viewModel;

    private RecyclerView recyclerView;

    private SearchRouteView view;
    private String username;
    private int subroute_id;
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
            subroute_id = extras.getInt("Subroute");
        }

        Subroute subroute = viewModel.getPresenter().findSubroute(subroute_id);
        Passenger currentPassenger = viewModel.getPresenter().findPassenger(username);
        viewModel.getPresenter().findSameDestinationCityRoutes(currentPassenger, subroute.getDestination().getCity());
        int l = viewModel.getPresenter().getRouteList().size();
        showErrorMessage("len", String.valueOf(l));
        // ui initialization
        recyclerView = findViewById(R.id.ChooseRouteRecyclerView);
        emptyView = findViewById(R.id.NoRoutes);
        viewModel.getPresenter().onChangeLayout();
        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        Subroute sub = viewModel.getPresenter().findSubroute(subroute_id);
        sub.setStatus(PENDING);
        Passenger pass = viewModel.getPresenter().findPassenger(username);
        route.addPassengerRoute(pass, sub);

        /* Redirect to same page in order to request another route */
        Intent intent = new Intent(this, this.getClass());
        intent.putExtra("Username", username);
        intent.putExtra("Subroute", sub.getId());
        startActivity(intent);
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
        recyclerView.setAdapter(new SearchRouteRecyclerViewAdapter(viewModel.getPresenter().getRouteList(), this));
    }

    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(SearchRouteActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();

    }

}