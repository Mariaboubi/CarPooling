package gr.aueb.carpooling.model.view.sign_up;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.dao.UserDAO;
import gr.aueb.carpooling.model.contact.EmailAddress;

public class SignUpPresenter {

    private final UserDAO userDao;
    private SignUpView view;

    public SignUpPresenter(UserDAO userDao) {
        this.userDao = userDao;
    }

    public void setView(SignUpView v) {
        this.view = v;
    }

    public SignUpView getView() {
        return view;
    }

    /**
     * Η μέθοδος αυτή καλείται όταν πατηθεί το κουμπί δημιουργίας του account απο τον ιδιοκτήτη
     * αφου πρώτα έχουν περαστεί όλα τα στοιχεία του
     * Κάνουμε ελέγχους σε κάθε πεδίο για το άν θεωρείται αποδεκτό , και εάν δεν είναι εμφανίζεται μήνυμα ειδοποίησης την οθόνη του ιδιοκτήτη
     * που τον ειδοποιεί για να κάνει τις απαραίτητες αλλαγές
     * Εάν τα στοιχεία είναι σωστά , εμφανίζεται κατάλληλο μήνυμα και προστίθεται ο ιδιοκτήτης στην εφαρμογή
     */
    public void onCreateUserAccount() {
        // Θα αποθηκευσουμε τα δεδομένα που εισήχθησαν στα πεδία

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

        if (inputName.isEmpty() && inputSurname.isEmpty() && inputUsername.isEmpty() && inputAge.isEmpty() && !inputEmail.isValid() &&
                inputPassword.isEmpty() && inputPasswordVerification.isEmpty() && inputPhoneNumber.isEmpty()) {
            view.showErrorMessage("Error!", "Complete all the fields");
        } else if (inputUsername.length() < 3) {
            view.showErrorMessage("Error!", "Username must have at least 3 characters.");
        } else if (Integer.parseInt(inputAge) < 0) {
            view.showErrorMessage("Error!", "Age is incorrect!");
        } else if (inputPhoneNumber.length() != 10) {
            view.showErrorMessage("Error!", "Phone number must have 10 characters.");
        } else if (!inputEmail.isValid()) {
            view.showErrorMessage("Error!", "The email address is incorrect.");
        } else if (inputPassword.length() < 8) {
            view.showErrorMessage("Error!", "Το password must have at least 8 characters.");
        } else if (!inputPassword.equals(inputPasswordVerification)) {
            view.showErrorMessage("Error!", "The fields password και confirm password must match!");
        } else {
            if (!((inputDriverLicense.isEmpty() && inputCarType.isEmpty() && inputIban.isEmpty()) ||
                    (!inputDriverLicense.isEmpty() && !inputCarType.isEmpty() && !inputIban.isEmpty()))) {
                view.showErrorMessage("Error!", "All the fields of the driver must be completed.");
            } else {
                User newUser = new User(inputUsername, inputName, inputSurname, inputPhoneNumber, inputEmail, inputPassword, inputAge);
                userDao.save(newUser);
                Driver driver = new Driver(inputUsername, inputName, inputSurname, inputPhoneNumber, inputEmail, inputPassword, inputAge, inputIban, inputDriverLicense, inputCarType);
                // Successful registration
                view.showRegistrationSuccessMessage();
            }
            if (!((inputCardNumber.isEmpty() && inputCardHolderName.isEmpty() && inputCVV.isEmpty()) ||
                    (!inputCardNumber.isEmpty() && !inputCardHolderName.isEmpty() && !inputCVV.isEmpty()))) {
                view.showErrorMessage("Error!", "All the fields of the passenger must be completed.");
            } else {
                if(inputCVV.length() < 3) {
                    view.showErrorMessage("Error!", "CVV must have 3 numbers.");
                }
                User newUser = new User(inputUsername, inputName, inputSurname, inputPhoneNumber, inputEmail, inputPassword, inputAge);
                userDao.save(newUser);
                Passenger passenger = new Passenger(inputUsername, inputName, inputSurname, inputPhoneNumber, inputEmail, inputPassword, inputAge, inputCardNumber, inputCardHolderName, inputCVV);
                // Successful registration
                view.showRegistrationSuccessMessage();
            }

            User newUser = new User(inputUsername, inputName, inputSurname, inputPhoneNumber, inputEmail, inputPassword, inputAge);
            userDao.save(newUser);
            view.showRegistrationSuccessMessage();

        }

    }
}


