package gr.aueb.carpooling.model.view.sign_up.passenger;


import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.dao.UserDAO;


public class PassengerSignUpPresenter {
    private final UserDAO userDao;

    private final PassengerDAO passengerDao;

    private PassengerSignUpView view;

    public PassengerSignUpPresenter(UserDAO userDao, PassengerDAO passengerDao) {
        this.userDao = userDao;
        this.passengerDao = passengerDao;
    }

    public void setView(PassengerSignUpView v) {
        this.view = v;
    }

    public PassengerSignUpView getView() {
        return view;
    }

    public void onCreatePassengerAccount(String username) {
        String inputCardNumber = view.getCardNumber();
        String inputCardHolderName = view.getCardHolderName();
        String inputCVV = view.getCVV();

        if(inputCardNumber.isEmpty() || inputCardHolderName.isEmpty() || inputCVV.isEmpty()) {
            view.showErrorMessage("Error!", "Complete all the fields.");
        } else if (inputCVV.length() != 3) {
            view.showErrorMessage("Error!", "CVV must have 3 numbers.");
        }else{
                User user = userDao.findByUsername(username);
                Passenger passenger = new Passenger(user.getUsername(),user.getName(),user.getSurname(),user.getPhone(),user.getEmail(),user.getPassword(),user.getAge(),inputCardNumber,inputCardHolderName,inputCVV);
                passengerDao.save(passenger);

                boolean b =passengerDao.find(username);
                view.showRegistrationSuccessMessage("is registered as passenger",String.valueOf(b));

        }
    }
}
