package gr.aueb.carpooling.model.view.driver.statistics;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.view.driver.front_page.DriverFrontPage;


public class DriverStatisticsActivity extends AppCompatActivity implements DriverStatisticsView {
    private DriverStatisticsViewModel viewModel;

    private DriverStatisticsView view;
    private String username;

    private DriverDAO driverDAO= new DriverDAOmemory();


    private Driver driver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_statistics);

        viewModel = new ViewModelProvider(this).get(DriverStatisticsViewModel.class);

        viewModel.getPresenter().setView(this);


        //
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");

        }
        driver=driverDAO.findByUsername(username);

        viewModel.getPresenter().setRouteList(driver);
        viewModel.getPresenter().calculateStats();

        ImageButton back_button = (ImageButton) findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDriverFrontPage();
            }
        });
    }
    public void openDriverFrontPage() {
        Intent intent = new Intent(DriverStatisticsActivity.this, DriverFrontPage.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    @Override
    public void setcalcYearlyRoutes(String YearlyRoutes) {
        ((TextView)findViewById(R.id.YearlyTotalroutesResult)).setText(YearlyRoutes);
    }

    @Override
    public void setMonthlyRoutes(String MonthlyRoutes) {
        ((TextView)findViewById(R.id.MonthlyTotalroutesResult)).setText(MonthlyRoutes);
    }

    @Override
    public void setYearlyAverageFullness(String YearlyAverageFullness) {
        ((TextView)findViewById(R.id.YearlyAverageFullnessResult)).setText(YearlyAverageFullness);
    }

    @Override
    public void setMonthlyAverageFullness(String MonthlyAverageFullness) {
        ((TextView)findViewById(R.id.MonthlyAverageFullnessResult)).setText(MonthlyAverageFullness);
    }

    @Override
    public void setcalcMonthlyIncome(String MonthlyIncome) {
        ((TextView)findViewById(R.id.MonthlyIncomeResult)).setText(MonthlyIncome);
    }

    @Override
    public void setcalcYearlyIncome(String YearlyIncome) {
        ((TextView)findViewById(R.id.YearlyIncomeResult)).setText(YearlyIncome);
    }


}