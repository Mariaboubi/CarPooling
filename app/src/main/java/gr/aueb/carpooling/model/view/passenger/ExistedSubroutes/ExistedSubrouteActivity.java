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
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.view.log_in.LogInActivity;
import gr.aueb.carpooling.model.view.passenger.PassengerFrontPageActivity;

public class ExistedSubrouteActivity extends AppCompatActivity implements ExistedSubrouteView,ExistedSubrouteRecyclerViewAdapter.SubrouteSelectionListener {

    private ExistedSubrouteViewModel viewModel;

    private String username;

    private RecyclerView recyclerView;

    private TextView emptyView;
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
        //viewModel.getPresenter().setSubrouteList();

        recyclerView = findViewById(R.id.ChooseSubrouteRecyclerView);
        emptyView = findViewById(R.id.NoSubroutes);
        viewModel.getPresenter().onChangeLayout();

        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                viewModel.getPresenter().onBack();
            }
        });

    }

    @Override
    public void selectSubroute(Subroute subroute) {
        Intent intent = new Intent(ExistedSubrouteActivity.this, PassengerFrontPageActivity.class);
//        intent.putExtra("RouteId",route.getId());
        intent.putExtra("Username",username);
        startActivity(intent);

    }

    @Override
    public void goBack() {

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

        Intent intent = new Intent(ExistedSubrouteActivity.this, LogInActivity.class);
//        intent.putExtra("Username",username);
        startActivity(intent);


    }
}