package gr.aueb.carpooling.model.view.driver.show_request;

import java.util.ArrayList;
import java.util.HashMap;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Request_status;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.dao.SubrouteDAO;

public class ShowRequestPresenter {

    private ShowRequestView view;

    private final ArrayList<Subroute> subroutes;



    public ShowRequestPresenter(SubrouteDAO subrouteDAO) {
        this.subroutes = new ArrayList<>();
    }

    /**
     * Sets our view object to use the methods of its interface
     * @param view An instance of the view
     */
    public void setView(ShowRequestView view) {
        this.view = view;
    }

    /**
     * Returns the view object we created earlier
     * @return The instance of the object
     */
    public ShowRequestView getView() {
        return view;
    }

    /**
     * Checks if the list of subroutes is empty
     * to display or show a message that there are no subroutes
     */
    public void onChangeLayout() {
        System.out.println("Subroute size: " + subroutes.size());
        if (subroutes.isEmpty()) {
            view.ShowNoRequests();
        }
        else {
            view.ShowRequests();
        }
    }

    public void setSubrouteList(ArrayList<Route> routes) {

        for(Route route: routes){
            HashMap<Passenger, Subroute> passengerSubrouteMap = route.getPassengerRoutes();

            subroutes.addAll(passengerSubrouteMap.values().stream().filter(subroute -> subroute.getStatus() == Request_status.PENDING).toList());
        }

    }

    public ArrayList<Subroute> getSubrouteList() {
        return subroutes;
    }


}
