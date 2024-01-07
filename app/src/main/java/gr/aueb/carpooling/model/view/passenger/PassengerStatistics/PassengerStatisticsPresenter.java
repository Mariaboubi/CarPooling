package gr.aueb.carpooling.model.view.passenger.PassengerStatistics;

import android.os.Build;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.view.driver.DriverStatistics.DriverStatisticsView;

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
            subroutes = (ArrayList<Subroute>) routeDAO.findSubroutesByPassanger(passenger);
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

    public float calcMonthlyRoutes(){
        LocalDateTime now = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
        }
        int number=0;
        Set<Integer> monthsWithRoutes = new HashSet<>();

        for (Subroute subroute:subroutes) {
            org.threeten.bp.LocalDateTime routeDate = subroute.getPickupTime();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (routeDate.getYear() == now.getYear() ) {
                    number ++ ;
                    int month = routeDate.getMonthValue();
                    monthsWithRoutes.add(month);
                }
            }
        }
        int totalMonths = monthsWithRoutes.size();
        if(totalMonths!=0) {
            return (float) number / totalMonths;
        }else{
            return 0;
        }
    }



    public Money calcYearlyExpenses(){
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

        return sum;
    }

    public Money calcMonthlyExpenses(){
        Money sum= new Money(0.0, Currency.getInstance("EUR"));
        LocalDateTime now = null;
        Set<Integer> monthsWithRoutes = new HashSet<>();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
            for(Subroute subroute:subroutes){
                org.threeten.bp.LocalDateTime routeDate = subroute.getPickupTime();
                if(routeDate.getYear()== now.getYear()){
                    sum=sum.plus(subroute.calculateCost());
                    int month = routeDate.getMonthValue();
                    monthsWithRoutes.add(month);

                }
            }
        }
        int totalMonths = monthsWithRoutes.size();
        if(totalMonths!=0) {
            return sum.divide((double) totalMonths);
        }else{
            return new Money(0.0, Currency.getInstance("EUR"));
        }
    }


    public void calculateStats(){
        int calcYearlyRoutes= calcYearlyRoutes();
        float calcMonthlyRoutes = calcMonthlyRoutes();
        // Money calcMonthlyExpenses= calcMonthlyExpenses();
//        Money calcYearlylyExpenses = calcYearlyExpenses();



        view.setcalcYearlyRoutes(String.valueOf(calcYearlyRoutes));


        view.setMonthlyRoutes(String.valueOf(calcMonthlyRoutes));


        //view.setcalcMonthlyIncome(String.valueOf(calcMonthlyIncome));

//        view.setcalcYearlyIncome(String.valueOf(calcYearlylyIncome));
    }
    public PassengerStatisticsView getView(){
        return this.view;
    }
}
