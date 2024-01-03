package gr.aueb.carpooling.model.view.sign_up;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.memoryDao.UserDAOmemory;

public class SignUpViewModel extends ViewModel {
    private final SignUpPresenter signUpPresenter;

    public SignUpViewModel() {signUpPresenter = new SignUpPresenter(new UserDAOmemory(), new DriverDAOmemory(),new PassengerDAOmemory());}

    public SignUpPresenter getPresenter() {return signUpPresenter;}
}
