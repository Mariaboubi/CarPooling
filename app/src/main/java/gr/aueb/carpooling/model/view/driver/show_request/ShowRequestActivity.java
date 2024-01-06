package gr.aueb.carpooling.model.view.driver.show_request;

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
import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.dao.SubrouteDAO;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.memoryDao.SubrouteDAOmemory;
import gr.aueb.carpooling.model.view.driver.DriverFrontPage;


public class ShowRequestActivity extends AppCompatActivity implements ShowRequestView,ShowRequestRecyclerViewAdapter.ShowRequestListener{

    private ShowRequestViewModel viewModel;

    private String username;

    private RecyclerView recyclerView;
    private TextView emptyView;

    private SubrouteDAO subrouteDAO = new SubrouteDAOmemory();

    private  DriverDAO driverDAO = new DriverDAOmemory();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_show_request);

        viewModel = new ViewModelProvider(this).get(ShowRequestViewModel.class);
        viewModel.getPresenter().setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            //The key argument here must match that used in the other activity
        }


        Driver driver = driverDAO.findByUsername(username);

        //viewModel.getPresenter().setRouteList(driver);

        // ui initialization
        recyclerView = findViewById(R.id.ShowRequestRecyclerView);
        emptyView = findViewById(R.id.NoRequests);
        viewModel.getPresenter().onChangeLayout();

        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDriverFrontPage(username);
            }
        });


    }


    public void selectRequest() {

    }

    @Override
    public void ShowNoRequests () {
        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void ShowRequests () {
        recyclerView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ShowRequestRecyclerViewAdapter(viewModel.getPresenter().getSubrouteList(), this));
    }
    public void showErrorMessage (String title, String message)
    {
        new AlertDialog.Builder(ShowRequestActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();

//        Intent intent = new Intent(ExistedRouteActivity.this, LogInActivity.class);
////        intent.putExtra("Username",username);
//        startActivity(intent);

    }

    @Override
    public void openDriverFrontPage(String username) {
        Intent intent = new Intent(this , DriverFrontPage.class);
        intent.putExtra("Username",username);
        startActivity(intent);
    }
}