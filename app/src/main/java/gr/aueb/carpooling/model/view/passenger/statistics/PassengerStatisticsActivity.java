package gr.aueb.carpooling.model.view.passenger.statistics;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.view.passenger.front_page.PassengerFrontPageActivity;


public class PassengerStatisticsActivity extends AppCompatActivity implements PassengerStatisticsView {
    private String username;
    private final PassengerDAO passengerDAO= new PassengerDAOmemory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_statics);

        PassengerStatisticsViewModel viewModel = new ViewModelProvider(this).get(PassengerStatisticsViewModel.class);

        viewModel.getPresenter().setView(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");

        }
        Passenger passenger = passengerDAO.findByUsername(username);

        viewModel.getPresenter().setSubroutes(passenger);
        viewModel.getPresenter().calculateStats();

        ImageButton back_button = (ImageButton) findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> openPassengerFrontPage());
    }

    private void openPassengerFrontPage() {
        Intent intent = new Intent(this, PassengerFrontPageActivity.class);
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
    public void setMonthlyExpenses(String MonthlyExpenses) {
        ((TextView)findViewById(R.id.MonthlyExpensesResult)).setText(MonthlyExpenses);
    }

    @Override
    public void setYearlyExpenses(String YearlyExpenses) {
        ((TextView)findViewById(R.id.YearlyExpensesResult)).setText(YearlyExpenses);
    }


}
