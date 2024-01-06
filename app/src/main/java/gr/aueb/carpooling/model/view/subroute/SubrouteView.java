package gr.aueb.carpooling.model.view.subroute;

import java.util.HashMap;

import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.view.View;

public interface SubrouteView extends View {

    HashMap<String,String> getSubRouteDetails();

    void showErrorMessage(String title, String message);



    void showRouteAddedMessage(Subroute subroute);
}


