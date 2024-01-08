package gr.aueb.carpooling.model.view.driver.existed_routes;

import java.util.ArrayList;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.dao.RouteDAO;

public class ExistedRoutePresenter {
    private ExistedRouteView view;
    private final RouteDAO routeDAO;
    private ArrayList<Route> routes;


    public ExistedRoutePresenter(RouteDAO routeDAO) {
        this.routeDAO = routeDAO;
        this.routes = new ArrayList<>();
    }

    /**
     * Sets the view object to use its interface methods
     *
     * @param view An instance of the view
     */
    public void setView(ExistedRouteView view) {
        this.view = view;
    }

    /**
     * Returns the view object created above
     *
     * @return The instance of the object
     */
    public ExistedRouteView getView() {
        return view;
    }

    /**
     * Fills the list with the routes of the specific driver
     */
    public void setRouteList(Driver driver) {
        routes = (ArrayList<Route>) routeDAO.findByDriver(driver);
    }

    /**
     * Checks if the list of routes is empty
     * to display or show a message that there are no routes
     */
    public void onChangeLayout() {
        if (routes.isEmpty()) {
            view.ShowNoRoutes();
        }
        else {
            view.ShowRoutes();
        }
    }

    /**
     * Returns the list of routes
     *
     * @return The list of routes
     */
    public ArrayList<Route> getRouteList() {
        return routes;
    }

}
