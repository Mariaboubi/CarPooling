package gr.aueb.carpooling.model.view.sign_up;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.dao.UserDAO;
import gr.aueb.carpooling.model.contact.EmailAddress;

public class SignUpPresenter {

    private UserDAO userDao;
    private SignUpView view;


    public SignUpPresenter(UserDAO userDao){
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
        Integer inputAge = view.getAge();
        EmailAddress inputEmail = view.getEmail();
        String inputPhoneNumber = view.getPhoneNumber();
        String inputCardNumber = view.getCardNumber();
        String inputCardHolderName = view.getCardHolderName();
        String inputCVV = view.getCVV();
        String inputUsername = view.getUsername();
        String inputPassword = view.getPassword();
        String inputPasswordVerification = view.getPasswordVerification();
        String inputIban= view.getIban();
        String inputDriverLicense = view.getDriverLicense();
        String inputCarType = view.getCarType();

        if (inputName.isEmpty() || inputSurname.isEmpty() || inputAge == null || inputEmail == null || inputPhoneNumber.isEmpty() ||
                 inputUsername.isEmpty() || inputPassword.isEmpty() || inputPasswordVerification.isEmpty()){
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε όλα τα υποχρεωτικά πεδία!");
        }else if (inputUsername.length() < 3) {
            view.showErrorMessage("Σφάλμα!", "Το όνομα χρήστη πρέπει να έχει τουλάχιστον 3 χαρακτήρες!");
            return;
        }else if(inputAge<0){
            view.showErrorMessage("Σφαλμα!" , "Η ηλικια ειναι λανθασμένη!");
        }else  if (inputPhoneNumber.length() != 10 ) {
            view.showErrorMessage("Σφάλμα!", "Ο αριθμός τηλεφώνου πρέπει να περιέχει 10 αριθμούς!");
            return;
        }else if (inputEmail.isValid()) {
            view.showErrorMessage("Σφάλμα!", "Εισάγετε ένα σωστό email!");
            return;
        }else if (inputPassword.length() < 8) {
            view.showErrorMessage("Σφάλμα!", "Το password πρέπει να έχει τουλάχιστον 8 χαρακτήρες!");
            return;
        } if (!inputPassword.equals(inputPasswordVerification)) {
            view.showErrorMessage("Σφάλμα!", "Τα πεδία password και conferm password πρέπει να ταιριάζουν!");
            return;
        }else  {
            // Έλεγχος για τη συμπλήρωση των πεδίων inputDriverLicense και inputCarType
            if (!((inputDriverLicense.isEmpty() && inputCarType.isEmpty() && inputIban.isEmpty()) ||
                    (!inputDriverLicense.isEmpty() && !inputCarType.isEmpty() && !inputIban.isEmpty()))) {
                System.out.println("Σφάλμα: Όλα τα πεδία πρέπει να συμπληρωθούν αν θελετε να έχετε την ιδιότητα του οδηγόυ.");
            } else {
                // Αν όλα τα υποχρεωτικά πεδία είναι συμπληρωμένα και τα πεδία inputDriverLicense και inputCarType είναι συμπληρωμένα ή και τα δύο άδεια, τότε αποθηκευουμε τον  χρήστη στη βάση δεδομένων
                User newUser = new User(inputUsername, inputName, inputSurname, inputPhoneNumber, inputEmail, inputPassword, inputAge);
                userDao.save(newUser);
                Driver driver= new Driver(inputUsername, inputName, inputSurname, inputPhoneNumber, inputEmail, inputPassword, inputAge,inputIban,inputDriverLicense,inputCarType);
                // Εμφάνιση μηνύματος επιτυχούς εγγραφής χρήστη
                view.showRegistrationSuccessMessage();
            }

            if (!((inputCardNumber.isEmpty() && inputCardHolderName.isEmpty() && inputCVV.isEmpty()) ||
                    (!inputCardNumber.isEmpty() && !inputCardHolderName.isEmpty() && !inputCVV.isEmpty()))) {
                System.out.println("Σφάλμα: Όλα τα πεδία πρέπει να συμπληρωθούν αν θελετε να έχετε την ιδιότητα του συνεπιβάτη.");
            } else {
                // Αν όλα τα υποχρεωτικά πεδία είναι συμπληρωμένα και τα πεδία inputDriverLicense και inputCarType είναι συμπληρωμένα ή και τα δύο άδεια, τότε αποθηκευουμε τον  χρήστη στη βάση δεδομένων
                User newUser = new User(inputUsername, inputName, inputSurname, inputPhoneNumber, inputEmail, inputPassword, inputAge);
                userDao.save(newUser);
                Passenger passenger = new Passenger(inputUsername, inputName, inputSurname, inputPhoneNumber, inputEmail, inputPassword, inputAge,inputCardNumber,inputCardHolderName,inputCVV);
                // Εμφάνιση μηνύματος επιτυχούς εγγραφής χρήστη
                view.showRegistrationSuccessMessage();
            }
            User newUser = new User(inputUsername, inputName, inputSurname, inputPhoneNumber, inputEmail, inputPassword, inputAge);
            userDao.save(newUser);
            view.showRegistrationSuccessMessage();

        }













    }




    }
