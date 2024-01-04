package gr.aueb.carpooling.model.view.sign_up.driver;

import androidx.lifecycle.ViewModel;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.memoryDao.UserDAOmemory;


public class DriverSignUpViewModel extends ViewModel {
    private final DriverSignUpPresenter driverSignUpPresenter;

    public DriverSignUpViewModel() {driverSignUpPresenter = new DriverSignUpPresenter(new UserDAOmemory(), new DriverDAOmemory());}

    public DriverSignUpPresenter getPresenter() {return driverSignUpPresenter;}
}
