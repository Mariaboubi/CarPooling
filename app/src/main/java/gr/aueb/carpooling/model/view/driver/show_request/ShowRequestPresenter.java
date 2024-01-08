package gr.aueb.carpooling.model.view.driver.show_request;

import java.util.ArrayList;
import java.util.HashMap;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Request_status;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.dao.SubrouteDAO;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;

public class ShowRequestPresenter {

    private ShowRequestView view;

    private final SubrouteDAO subrouteDAO;

    private ArrayList<Subroute> subroutes;



    public ShowRequestPresenter(SubrouteDAO subrouteDAO) {
        this.subrouteDAO = subrouteDAO;
        this.subroutes = new ArrayList<Subroute>();
    }

    /**
     *Σετάρει το αντικείμενο view μας για να χρησιμοποιήσουμε τις μεθόδους του interface του
     * @param view Ένα instance του view
     */
    public void setView(ShowRequestView view) {
        this.view = view;
    }
    /**
     * Επιστρέφει το αντικείμενο view Που δημιουργήσαμε παραπάνω
     * @return το Instance του αντικειμένου
     */
    public ShowRequestView getView() {
        return view;
    }

    /**
     *  Ελεγχουμε εαν η λίστα με τις διαδρομες είναι άδεια
     *  για να τα προβάλουμε ή να δείξουμε μήνυμα οτι δεν υπάρχουν διαδρομες
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
            System.out.println("passengerSubrouteMap: " + passengerSubrouteMap);
            // Add all subroutes to the subroutes_exist list
            // print status
            System.out.println("subroute status: " + passengerSubrouteMap.values().stream().map(Subroute::getStatus).toList());
            subroutes.addAll(passengerSubrouteMap.values().stream().filter(subroute -> subroute.getStatus() == Request_status.PENDING).toList());
        }

    }

    public ArrayList<Subroute> getSubrouteList() {
        return subroutes;
    }
//    public ArrayList<Route> getSubrouteList() {
//    return subroutes;
//}
    public void showError(boolean b) {
        view.showErrorMessage("is Completed", String.valueOf(b));

    }

}
