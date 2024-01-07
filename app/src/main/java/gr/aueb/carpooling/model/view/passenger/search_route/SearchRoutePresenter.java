package gr.aueb.carpooling.model.view.passenger.search_route;

import java.util.ArrayList;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.dao.SubrouteDAO;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;
import gr.aueb.carpooling.model.memoryDao.SubrouteDAOmemory;

public class SearchRoutePresenter {
    private SearchRouteView view;
    private final RouteDAO routeDAO ;

    private final SubrouteDAO subrouteDAO;

    private final PassengerDAO passengerDAO;

    private ArrayList<Route> routes;


    public SearchRoutePresenter(RouteDAOmemory routeDAO, SubrouteDAOmemory subrouteDAO, PassengerDAO passengerDAO) {
        this.routeDAO = routeDAO;
        this.subrouteDAO = subrouteDAO;
        this.passengerDAO = passengerDAO;
        this.routes = new ArrayList<>();
    }

    /**
     * Σετάρει το αντικείμενο view μας για να χρησιμοποιήσουμε τις μεθόδους του interface του
     *
     * @param view Ένα instance του view
     */
    public void setView(SearchRouteView view) {
        this.view = view;
    }

    /**
     * Επιστρέφει το αντικείμενο view Που δημιουργήσαμε παραπάνω
     *
     * @return το Instance του αντικειμένου
     */
    public SearchRouteView getView() {
        return view;
    }

    /**
     * Γεμίζει την λίστα με της διαδρομεσ του συγκεκριμενου οδηγού
     */
    public void findSameDestinationCityRoutes(Passenger currentPassenger, String city) {
        this.routes = (ArrayList<Route>) routeDAO.findAll();
        ArrayList<Route> routes_to_remove = new ArrayList<>();
        for (Route route : routes) {
            if (!route.getDestination().getCity().equalsIgnoreCase(city)) {
                routes_to_remove.add(route);
            }
            if (route.getSubRouteByPassenger(currentPassenger) != null) {
                routes_to_remove.add(route);
            }
        }
        routes.removeAll(routes_to_remove);
    }

    /**
     * Ελεγχουμε εαν η λίστα με τις διαδρομες είναι άδεια
     * για να τα προβάλουμε ή να δείξουμε μήνυμα οτι δεν υπάρχουν διαδρομες
     */
    public void onChangeLayout() {
        if (routes.isEmpty()) {
            view.ShowNoRoutes();
        } else {
            view.ShowRoutes();
        }
    }

    /**
     * Επιστρέφει την λίστα με τις διαδρομές
     *
     * @return η λίστα με τις διαδρομες
     */
    public ArrayList<Route> getRouteList() {
        return this.routes;
    }

    public Subroute findSubroute(int subroute_id) {
        return subrouteDAO.findById(subroute_id);
    }
    public Passenger findPassenger(String username) {
        return passengerDAO.findByUsername(username);
    }

}