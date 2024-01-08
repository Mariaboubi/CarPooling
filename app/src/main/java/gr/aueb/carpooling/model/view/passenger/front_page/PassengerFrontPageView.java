package gr.aueb.carpooling.model.view.passenger.front_page;


import gr.aueb.carpooling.model.view.View;

public interface PassengerFrontPageView extends View {

    void openLogInActivity() ;

    void openTopUpActivity();

    void openStatisticsPage();

    void openCreateRoutePage(String username);

    void openShowRoutesPage() ;

}
