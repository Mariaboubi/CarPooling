package gr.aueb.carpooling.model.view.driver.statistics;

import gr.aueb.carpooling.model.view.View;

public interface DriverStatisticsView extends View {

    void showErrorMessage(String title, String message);


    void setcalcYearlyRoutes(String YearlyRoutes);

    void setMonthlyRoutes(String MonthlyRoutes);

    void setYearlyAverageFullness(String YearlyAverageFullness) ;

    void setMonthlyAverageFullness(String MonthlyAverageFullness);

    void setcalcMonthlyIncome(String MonthlyIncome);

    void setcalcYearlyIncome(String YearlyIncome);

}
