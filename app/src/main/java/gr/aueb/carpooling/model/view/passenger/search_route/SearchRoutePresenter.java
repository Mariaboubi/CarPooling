package gr.aueb.carpooling.model.view.passenger.search_route;

import java.util.ArrayList;

import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;

public class SearchRoutePresenter {
    SearchRouteView view;
    private RouteDAO routeDAO = new RouteDAOmemory();

    private ArrayList<Route> routes;


    public SearchRoutePresenter(RouteDAOmemory routeDAO) {
        this.routeDAO = routeDAO;
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
    public void setRouteList() {


        this.routes = (ArrayList<Route>) routeDAO.findAll();
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
        return routes;
    }


}