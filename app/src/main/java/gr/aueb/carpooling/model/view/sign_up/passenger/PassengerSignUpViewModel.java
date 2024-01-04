package gr.aueb.carpooling.model.view.sign_up.passenger;

import androidx.lifecycle.ViewModel;


import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.memoryDao.UserDAOmemory;


public class PassengerSignUpViewModel extends ViewModel {

    private final PassengerSignUpPresenter passengerSignUpPresenter;

    public PassengerSignUpViewModel() {passengerSignUpPresenter = new PassengerSignUpPresenter(new UserDAOmemory(), new PassengerDAOmemory());}

    public PassengerSignUpPresenter getPresenter() {return passengerSignUpPresenter;}
}
