package gr.aueb.carpooling.model.view.driver.RatingPassengers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;
import gr.aueb.carpooling.model.view.driver.DriverFrontPage;



    public class RatingPassengers extends AppCompatActivity implements RatingPassengerView, RatingPassengerRecyclerViewAdapter.PassengerRatingSelectionListener {

        private RatingPassengersViewModel viewModel;

        private String username;

        private String dest;

        private String date;
        private RecyclerView recyclerView;
        private TextView emptyView;

        private int route_id;

        private PassengerDAO passengerDAO = new PassengerDAOmemory();

        private RouteDAO routeDAO = new RouteDAOmemory();

        private DriverDAO driverDAO = new DriverDAOmemory();

        private Route route;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_driver_rating_passengers);

            viewModel = new ViewModelProvider(this).get(RatingPassengersViewModel.class);
            viewModel.getPresenter().setView(this);

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                username = extras.getString("Driver username");
                route_id = extras.getInt("Route id");
                //Username Driver
                username = extras.getString("Username");
                dest = extras.getString("RouteDest");
                date = extras.getString("RouteDate");

                //The key argument here must match that used in the other activity
            }
            System.out.println("route id is " + route_id);
            //viewModel.getPresenter().setPassengerRatingList();
//        showErrorMessage("RouteDest",dest);
            route = routeDAO.findByDestDateDriver(dest, date, driverDAO.findByUsername(username));

            //showErrorMessage("RouteDest",route.getDestination().toString());
            viewModel.getPresenter().setPassengerList(route);
            recyclerView = findViewById(R.id.RatingPassengerRecyclerView);
            emptyView = findViewById(R.id.NoPassengers);
            viewModel.getPresenter().onChangeLayout();
            //showErrorMessage("in passenger rating page", "in");


        }


        public void showErrorMessage(String title, String message) {
            new AlertDialog.Builder(RatingPassengers.this)
                    .setCancelable(true)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("OK", null).create().show();
        }
//    @Override
//    public HashMap<String, String> getRateDetails() {
//        return null;
//    }


        @Override
        public void ShowNoPassengers() {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }


        @Override
        public void ShowPassengers() {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new RatingPassengerRecyclerViewAdapter(viewModel.getPresenter().getPassengerList(), this, route));
        }


        @Override
        public void selectRate(PassengerRating rating) {
            //passengerDAO.findByUsername(username).addRates(rating);
            showErrorMessage(" Η βαθμολογία καταχωρήθεικε.Σε αθτη την διαδρομη εχει Μ.Ο: ", String.valueOf(rating.averageRating()));
            Intent intent = new Intent(RatingPassengers.this, DriverFrontPage.class);
            intent.putExtra("Username", username);
            startActivity(intent);
        }
    }

