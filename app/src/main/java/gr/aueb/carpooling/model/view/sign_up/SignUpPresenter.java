package gr.aueb.carpooling.model.view.sign_up;

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
        String inputCreditCard = view.getCreditCard();
        String inputUsername = view.getUsername();
        String inputPassword = view.getPassword();
        String inputPasswordVerification = view.getPasswordVerification();
        String inputDriverLicense = view.getDriverLicense();
        String inputCarType = view.getCarType();

        if (inputName.isEmpty() || inputSurname.isEmpty() || inputAge == null || inputEmail == null || inputPhoneNumber.isEmpty() ||
                inputCreditCard.isEmpty() || inputUsername.isEmpty() || inputPassword.isEmpty() || inputPasswordVerification.isEmpty()){
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε όλα τα υποχρεωτικά πεδία!");
        }else if (inputUsername.length() < 3) {
            view.showErrorMessage("Σφάλμα!", "Το όνομα χρήστη πρέπει να έχει τουλάχιστον 3 χαρακτήρες!");
            return;
        }else if(inputAge<0){
            view.showErrorMessage("Σφαλμα!" , "Η ηλικια ειναι λανθασμένη!");
        }else  if (inputPhoneNumber.length() != 10 || !inputPhoneNumber.matches("\\d+")) {
            view.showErrorMessage("Σφάλμα!", "Ο αριθμός τηλεφώνου πρέπει να περιέχει 10 αριθμούς!");
            return;
        }else if (inputEmail.isValid()) {
            view.showErrorMessage("Σφάλμα!", "Εισάγετε ένα σωστό email!");
            return;
        }else if (inputCreditCard.length() > 20) {
            view.showErrorMessage("Σφάλμα!", "Το IBAN πρέπει να έχει λιγότερους από 20 αριθμούς!");
            return;
        }else if (inputPassword.length() < 8) {
            view.showErrorMessage("Σφάλμα!", "Το password πρέπει να έχει τουλάχιστον 8 χαρακτήρες!");
            return;
        } if (!inputPassword.equals(inputPasswordVerification)) {
            view.showErrorMessage("Σφάλμα!", "Τα πεδία password και verification password πρέπει να ταιριάζουν!");
            return;
        }else {
            // Έλεγχος για τη συμπλήρωση των πεδίων inputDriverLicense και inputCarType
            if ((!inputDriverLicense.isEmpty() && inputCarType.isEmpty()) || (inputDriverLicense.isEmpty() && !inputCarType.isEmpty())) {
                // Εμφάνιση μηνύματος σφάλματος αν ένα από τα δύο συμπληρώθηκε και το άλλο όχι
                view.showErrorMessage("Σφάλμα!", "Συμπληρώστε και τα δύο πεδία άδεια!");
            } else {
                // Αν όλα τα υποχρεωτικά πεδία είναι συμπληρωμένα και τα πεδία inputDriverLicense και inputCarType είναι συμπληρωμένα ή και τα δύο άδεια, τότε αποθηκευουμε τον  χρήστη στη βάση δεδομένων
                User newUser = new User(inputUsername, inputName, inputSurname, inputPhoneNumber, inputEmail, inputPassword, inputAge);
                userDao.save(newUser);
                // Εμφάνιση μηνύματος επιτυχούς εγγραφής χρήστη
                view.showRegistrationSuccessMessage();
            }
        }













    }




    }
