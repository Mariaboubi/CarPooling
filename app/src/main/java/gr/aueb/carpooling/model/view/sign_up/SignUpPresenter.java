package gr.aueb.carpooling.model.view.sign_up;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.dao.UserDAO;

public class SignUpPresenter {

    private final UserDAO userDao;

    private final DriverDAO driverDao;

    private final PassengerDAO passengerDao;
    private SignUpView view;

    public SignUpPresenter(UserDAO userDao, DriverDAO driverDao, PassengerDAO passengerDao) {
        this.userDao = userDao;
        this.driverDao = driverDao;
        this.passengerDao = passengerDao;
    }

    public void setView(SignUpView v) {
        this.view = v;
    }

    public SignUpView getView() {
        return view;
    }

    /**
     * This method is called when the account creation button is pressed by the user
     * after all their details have been entered.
     * We perform checks on each field to determine if it is acceptable, and if not, we display a notification message to the user's screen
     * prompting them to make the necessary changes.
     * If the details are correct, an appropriate message is displayed, and the user is added to the application.
     */
    public void onCreateUserAccount() {

        String inputName = view.getName();
        String inputSurname = view.getSurname();
        EmailAddress inputEmail = new EmailAddress(view.getEmail());
        String inputPhoneNumber = view.getPhoneNumber();
        String inputAge = view.getAge();
        String inputUsername = view.getUsername();
        String inputPassword = view.getPassword();
        String inputPasswordVerification = view.getPasswordVerification();
        String inputCardNumber = view.getCardNumber();
        String inputCardHolderName = view.getCardHolderName();
        String inputCVV = view.getCVV();
        String inputIban = view.getIban();
        String inputDriverLicense = view.getDriverLicense();
        String inputCarType = view.getCarType();


        if (inputName.isEmpty() || inputSurname.isEmpty() || inputUsername.isEmpty() || inputAge.isEmpty() || inputEmail.toString().isEmpty() ||
                inputPassword.isEmpty() || inputPasswordVerification.isEmpty() || inputPhoneNumber.isEmpty()) {
            view.showErrorMessage("Error!", "Complete all the fields");
        } else if (inputUsername.length() < 3) {
            view.showErrorMessage("Error!", "Username must have at least 3 characters.");
        } else if (Integer.parseInt(inputAge) <= 0 || Integer.parseInt(inputAge) > 120) {
            view.showErrorMessage("Error!", "Age is incorrect!");
        } else if (inputPhoneNumber.length() != 10) {
            view.showErrorMessage("Error!", "Phone number must have 10 characters.");
        } else if (!inputEmail.isValid()) {
            view.showErrorMessage("Error!", "The email address is incorrect.");
        } else if (inputPassword.length() < 8) {
            view.showErrorMessage("Error!", "Το password must have at least 8 characters.");
        } else if (!inputPassword.equals(inputPasswordVerification)) {
            view.showErrorMessage("Error!", "The fields password and confirm password must match!");
        } else {

            /* check if user with username exists already */
            if (userDao.findByUsername(inputUsername) != null) {
                view.showErrorMessage("Error!", "This username is taken.");
                return;
            }
            User newUser = new User(inputUsername, inputName, inputSurname, inputPhoneNumber, inputEmail, inputPassword, inputAge);
            userDao.save(newUser);

            if ((inputDriverLicense.isEmpty() && inputCarType.isEmpty() && inputIban.isEmpty()) ||
                    (!inputDriverLicense.isEmpty() && !inputCarType.isEmpty() && !inputIban.isEmpty())) {
                Driver driver = new Driver(newUser.getUsername(), newUser.getName(), newUser.getSurname(), newUser.getPhone(), newUser.getEmail(), newUser.getPassword(), newUser.getAge(), inputIban, inputDriverLicense, inputCarType);
                driverDao.save(driver);

            } else {
                view.showErrorMessage("Error!", "Complete all the driver fields or leave them all empty");
                return;
            }

            if ((inputCardNumber.isEmpty() && inputCardHolderName.isEmpty() && inputCVV.isEmpty()) ||
                    (!inputCardNumber.isEmpty() && !inputCardHolderName.isEmpty() && !inputCVV.isEmpty())) {
                Passenger passenger = new Passenger(newUser.getUsername(), newUser.getName(), newUser.getSurname(), newUser.getPhone(), newUser.getEmail(), newUser.getPassword(), newUser.getAge(), inputCardNumber, inputCardHolderName, inputCVV);
                passengerDao.save(passenger);
            } else {
                view.showErrorMessage("Error!", "Complete all the passenger fields or leave them all empty");
                return;
            }
            view.showRegistrationSuccessMessage();

        }

    }
}


