package gr.aueb.carpooling.model.view.passenger.statistics;

import gr.aueb.carpooling.model.view.View;

public interface PassengerStatisticsView extends View {

    void showErrorMessage(String title, String message);


    void setcalcYearlyRoutes(String YearlyRoutes);

    void setMonthlyRoutes(String MonthlyRoutes);


    void setMonthlyExpenses(String MonthlyIncome);

    void setYearlyExpenses(String YearlyIncome);

}
