package gr.aueb.carpooling.model.view.driver.DriverStatistics;

import android.os.Build;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;

public class DriverStatisticsPresenter {
    private RouteDAO routeDAO;

    private DriverStatisticsView view;

    private ArrayList<Route> routes;



    public DriverStatisticsPresenter(RouteDAO routeDAO){
        this.routeDAO=routeDAO;
        this.routes = new ArrayList<>();
    }


    public void setView(DriverStatisticsView view)
    {
        this.view = view;
    }


    public void setRouteList(Driver driver) {
        routes = (ArrayList<Route>) routeDAO.findByDriverIsCompleted(driver);
    }


    public int calcYearlyRoutes(){
        int number=0;
        LocalDateTime now = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
            for(Route route:routes){
                if(route.getDate().getYear()== now.getYear()){
                    number++;

                }
            }
        }

        return number;
    }

    public int calcMonthRoutes(){
        LocalDateTime now = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
        }
        int number=0;

        for (Route route : routes) {
            org.threeten.bp.LocalDateTime routeDate = route.getDate();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (routeDate.getYear() == now.getYear()  && routeDate.getMonthValue() == now.getMonthValue()) {
                    number ++ ;
                }
            }
        }
        return number;
    }

    public float calcYearlyAverageFullness(){
        float sum=0;
        LocalDateTime now = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
            for(Route route:routes){

                int actual_number_of_passengers =route.getPassengerRoutes().size();
                if(route.getDate().getYear()== now.getYear() ){
                    sum= (float) actual_number_of_passengers /route.getMaxPassengers();
                }
            }

        }

        if(routes.size()!=0) {
            return (float) sum / routes.size();
        }else{
            return 0;
        }
    }

    public float calcMonthAverageFullness(){
        LocalDateTime now = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
        }
        float sum=0;
        Set<Integer> monthsWithRoutes = new HashSet<>();

        for (Route route : routes) {

            org.threeten.bp.LocalDateTime routeDate = route.getDate();
            int actual_number_of_passengers =route.getPassengerRoutes().size();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                if (routeDate.getYear() == now.getYear()&& routeDate.getMonthValue() == now.getMonthValue()) {
                    sum= (float) actual_number_of_passengers /route.getMaxPassengers();
                }
            }

        }
        if(routes.size()!=0) {
            return (float) sum / routes.size();
        }else{
            return 0;
        }
    }

    public Money calcYearlyIncome(){
        Money sum= new Money(0.0, Currency.getInstance("EUR"));
        LocalDateTime now = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
            for(Route route:routes){

                if(route.getDate().getYear()== now.getYear()){
                    sum=sum.plus(route.getTotalCost());

                }
            }
        }

        return sum;
    }

    public Money calcMonthIncome(){
        Money sum= new Money(0.0, Currency.getInstance("EUR"));
        LocalDateTime now = null;
        Set<Integer> monthsWithRoutes = new HashSet<>();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
            for(Route route:routes){
                org.threeten.bp.LocalDateTime routeDate = route.getDate();
                if(route.getDate().getYear()== now.getYear()&& routeDate.getMonthValue() == now.getMonthValue()) {
                    sum=sum.plus(route.getTotalCost());
                }
            }
        }
        return sum;
    }


    public void calculateStats(){
        int calcYearlyRoutes= calcYearlyRoutes();
        int calcMonthlyRoutes = calcMonthRoutes();
        float calcYearlyAverageFullness = calcYearlyAverageFullness();
        float calcMonthlyAverageFullness=calcMonthAverageFullness();
        Money calcMonthlyIncome = calcMonthIncome();
        Money calcYearlylyIncome = calcYearlyIncome();



        view.setcalcYearlyRoutes(String.valueOf(calcYearlyRoutes));


        view.setMonthlyRoutes(String.valueOf(calcMonthlyRoutes));

        view.setYearlyAverageFullness(String.valueOf(calcYearlyAverageFullness));



        view.setMonthlyAverageFullness(String.valueOf(calcMonthlyAverageFullness));

        view.setcalcMonthlyIncome(new DecimalFormat("0.00").format(calcMonthlyIncome.getAmount()));

        view.setcalcYearlyIncome(new DecimalFormat("0.00").format(calcYearlylyIncome.getAmount()));
    }
    public DriverStatisticsView getView(){
        return this.view;
    }

}
