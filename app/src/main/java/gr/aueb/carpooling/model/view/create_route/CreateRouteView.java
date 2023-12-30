package gr.aueb.carpooling.model.view.create_route;

import java.util.ArrayList;
import java.util.HashMap;

public interface CreateRouteView {  /**
* Δημιουργεί ένα hash map στο οποίο έχουμε σαν κλειδί το εστιατορίο
 * και σαν value έχουμε την τιμή του κλειδιού την οποία παίρνουμε απο την οθόνη που έχει περάσει ο οδηγος
 * τα στοιχεία της διαδρομης που θέλει να προσθέσει
 */
    HashMap<String,String> getRouteDetails();
    /**
     * Εμφανίζει ενα μήνυμα τύπου alert με
     * τίτλο title και μήνυμα message.
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    void showErrorMessage(String title, String message);
    /**
     * Καλείται για να επιστρέψουμε στο προηγούμενο Activity
     */
    void goBack();

    /**
     * Εμφανίζει μήνυμα επιτυχίας όταν ο ιδιοτήτης προσθέσει επιτυχώς το νέο route του
     * και επιστρέφει στο προηγούμενο ακτίβιτι όταν πατηθεί το κουμπί ΟΚ
     */
    void showRouteAddedMessage();
}
