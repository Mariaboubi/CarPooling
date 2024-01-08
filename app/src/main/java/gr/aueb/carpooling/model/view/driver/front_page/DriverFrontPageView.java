package gr.aueb.carpooling.model.view.driver.front_page;

import gr.aueb.carpooling.model.view.View;


public interface DriverFrontPageView extends View {

    void openDriverTopUpActivity();
    void openStatisticsPage();
    void openCreateRoutePage(String username) ;
}
