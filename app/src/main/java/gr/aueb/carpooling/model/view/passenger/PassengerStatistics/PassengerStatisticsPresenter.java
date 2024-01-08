package gr.aueb.carpooling.model.view.passenger.PassengerStatistics;

import android.os.Build;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.dao.RouteDAO;

public class PassengerStatisticsPresenter {

    private RouteDAO routeDAO;

    private PassengerStatisticsView view;

    private ArrayList<Subroute> subroutes;



    public PassengerStatisticsPresenter(RouteDAO routeDAO){
        this.routeDAO=routeDAO;
        this.subroutes = new ArrayList<>();
    }


    public void setView(PassengerStatisticsView view)
    {
        this.view = view;
    }


    public void setSubroutes(Passenger passenger) {
            subroutes = (ArrayList<Subroute>) routeDAO.findSubroutesByPassengerIsCompleted(passenger);
    }
    public void calculateStats(){
        int calcYearlyRoutes= calcYearlyRoutes();
        float calcMonthlyRoutes = calcMonthRoutes();
        double calcMonthlyExpenses= calcMonthExpenses();
        double calcYearlyExpenses = calcYearlyExpenses();


        view.setcalcYearlyRoutes(String.valueOf(calcYearlyRoutes));


        view.setMonthlyRoutes(String.valueOf(calcMonthlyRoutes));


        String calcMonthlyExpensesToString =  new DecimalFormat("0.00").format(calcMonthlyExpenses);
        view.setMonthlyExpenses(calcMonthlyExpensesToString);

        String calcYearlylyExpensesToString =  new DecimalFormat("0.00").format(calcYearlyExpenses);
        view.setYearlyExpenses(calcYearlylyExpensesToString);
    }

    public int calcYearlyRoutes(){
        int number=0;
        LocalDateTime now = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
            for(Subroute subroute:subroutes){
                if(subroute.getPickupTime().getYear()== now.getYear()){
                    number++;

                }
            }
        }

        return number;
    }

    public float calcMonthRoutes(){
        LocalDateTime now = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
        }
        int number=0;

        for (Subroute subroute:subroutes) {
            org.threeten.bp.LocalDateTime routeDate = subroute.getPickupTime();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (routeDate.getYear() == now.getYear( )&& routeDate.getMonthValue() == now.getMonthValue())  {
                    number ++ ;
                }
            }
        }

            return number;

    }



    public double calcYearlyExpenses(){

        Money sum= new Money(0.0, Currency.getInstance("EUR"));
        LocalDateTime now = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
            for(Subroute subroute:subroutes){

                if(subroute.getPickupTime().getYear()== now.getYear()){
                    sum=sum.plus(subroute.calculateCost());

                }
            }
        }

        return sum.getAmount();
    }

    public double calcMonthExpenses(){
        Money sum= new Money(0.0, Currency.getInstance("EUR"));
        LocalDateTime now = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
            for(Subroute subroute:subroutes){
                org.threeten.bp.LocalDateTime routeDate = subroute.getPickupTime();
                if(routeDate.getYear()== now.getYear() && routeDate.getMonthValue() == now.getMonthValue()){
                    sum=sum.plus(subroute.calculateCost());

                }
            }
        }
        return sum.getAmount();
    }

    public PassengerStatisticsView getView(){
        return this.view;
    }
}
