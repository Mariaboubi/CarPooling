package gr.aueb.carpooling.model.view.passenger.subroute;

import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.view.View;

public interface SubrouteView extends View {

    String StreetDest();

    String NumberDest();

    String CityDest();

    String ZipCodeDest();

    String StreetPick();

    String NumberPick();

    String CityPick();

    String ZipCodePick();

    String Date();

    void showErrorMessage(String title, String message);

    void showRouteAddedMessage(Subroute subroute);
}


