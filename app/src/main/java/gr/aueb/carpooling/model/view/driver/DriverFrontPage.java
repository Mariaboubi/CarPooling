package gr.aueb.carpooling.model.view.driver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.memoryDao.MemoryInitialized;
import gr.aueb.carpooling.model.view.attribute_selection.AttributeSelectionActivity;
import gr.aueb.carpooling.model.view.driver.ExistedRoutes.ExistedRouteActivity;
import gr.aueb.carpooling.model.view.driver.createRoute.CreateRouteActivity;

public class DriverFrontPage extends AppCompatActivity implements DriverFrontPageView {

    private ImageButton back_button ;

    private Button create_route_button;

    private  DriverFrontPageViewModel viewModel;

    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_front_page);

        MemoryInitialized dataHelper = new MemoryInitialized();
        dataHelper.prepareData();

        viewModel= new ViewModelProvider(this).get(DriverFrontPageViewModel.class);

        viewModel.getPresenter().setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            //The key argument here must match that used in the other activity
        }

        back_button = (ImageButton) findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAttributeSelectionActivity();
            }
        });

        create_route_button = (Button) findViewById(R.id.button_createroute);

        create_route_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateRoutePage(username);
            }
        });


        Button show_existed_route_button = (Button) findViewById(R.id.button_showroutes);
        show_existed_route_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExistedRoutePage(username);
            }
        });

    }

    void openCreateRoutePage(String userId) {
        Intent intent = new Intent(this, CreateRouteActivity.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }
    void openAttributeSelectionActivity() {
        Intent intent = new Intent(this, AttributeSelectionActivity.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    void openExistedRoutePage(String username) {
        Intent intent = new Intent(this, ExistedRouteActivity.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }
}