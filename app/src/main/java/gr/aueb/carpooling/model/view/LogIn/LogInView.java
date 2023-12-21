package gr.aueb.carpooling.model.view.LogIn;



public interface LogInView extends View {
    /**
     * Η μέθοδος αυτή λαμβάνει το όνομα που έχει πληκτρολογήσει ο χρήστης στο πεδίο Username
     */
    String ExtractUsername();
    /**
     * Η μέθδος αυτή λαμβάνει τον κωδικό που έχει πληκτρολογήσει ο χρήστης στο πεδίο PassWord
     */
    String ExtractPassword();
    /**
     * Εμφανίζει μήνυμα επιτυχίας όταν ο χρηστης συνδεθεί επιτυχώς τον λογαριασμό του
     * και κατευθύνεται στο Home Page ακτίβιτι όταν πατηθεί το κουμπί ΟΚ
     */
    void showUserFoundMessage(int id);

    /**
     * Η μέθοδος αυτή καλείται όταν πατηθεί το κουμπί εγγραφής για πελάτη
     */
    void signup();

    /**
     * Εμφανίζει ενα μήνυμα τύπου alert με
     * τίτλο title και μήνυμα message.
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    void showErrorMessage(String title, String message);



}
