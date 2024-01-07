package gr.aueb.carpooling.model.view.passenger.PassengerStatistics;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;
import gr.aueb.carpooling.model.view.driver.DriverStatistics.DriverStatisticsPresenter;

public class PassengerStatisticsViewModel extends ViewModel {

    PassengerStatisticsPresenter presenter;
    /**
     * Αρχικοποιεί τον presenter και του περνάει ένα νέο αντικείμενο τύπου owner doa και restaurant dao για να χρησιμποιήσει
     */
    public PassengerStatisticsViewModel(){
        presenter = new PassengerStatisticsPresenter(new RouteDAOmemory());
    }
    /**
     * Επιστρέφει τον presenter στις κλάσεις όπου περιέχει τις πληροφορίες
     * @return το instance του presenter που δημιουργήσαμε παραπάνω
     */
    public PassengerStatisticsPresenter getPresenter()
    {
        return presenter;
    }
}
