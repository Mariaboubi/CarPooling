package gr.aueb.carpooling.model.view.driver.statistics;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;

public class DriverStatisticsViewModel extends ViewModel {

    DriverStatisticsPresenter presenter;
    /**
     * Αρχικοποιεί τον presenter και του περνάει ένα νέο αντικείμενο τύπου owner doa και restaurant dao για να χρησιμποιήσει
     */
    public DriverStatisticsViewModel(){
        presenter = new DriverStatisticsPresenter(new RouteDAOmemory());
    }
    /**
     * Επιστρέφει τον presenter στις κλάσεις όπου περιέχει τις πληροφορίες
     * @return το instance του presenter που δημιουργήσαμε παραπάνω
     */
    public DriverStatisticsPresenter getPresenter()
    {
        return presenter;
    }
}
