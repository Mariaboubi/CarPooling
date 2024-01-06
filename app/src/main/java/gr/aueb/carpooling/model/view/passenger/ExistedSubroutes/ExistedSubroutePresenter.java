package gr.aueb.carpooling.model.view.passenger.ExistedSubroutes;


import java.util.ArrayList;

import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.dao.SubrouteDAO;

public class ExistedSubroutePresenter {

    ExistedSubrouteView view;

    private SubrouteDAO subrouteDAO;

    private ArrayList<Subroute> subroutes;

    public ExistedSubroutePresenter(SubrouteDAO subrouteDAO){
        this.subrouteDAO = subrouteDAO;
        subroutes = new ArrayList<>();
    }

    /**
     *Σετάρει το αντικείμενο view μας για να χρησιμοποιήσουμε τις μεθόδους του interface του
     * @param view Ένα instance του view
     */
    public void setView(ExistedSubrouteView view) {
        this.view = view;
    }

    /**
     * Γεμίζει την λίστα με της διαδρομεσ του συγκεκριμενου επιβατη
     */
    public void setSubrouteList() {
        subroutes = (ArrayList<Subroute>) subrouteDAO.findAll();
    }
    /**
     *  Ελεγχουμε εαν η λίστα με τις διαδρομες είναι άδεια
     *  για να τα προβάλουμε ή να δείξουμε μήνυμα οτι δεν υπάρχουν διαδρομες
     */
    public void onChangeLayout() {
        if (subroutes.isEmpty()) {
            view.ShowNoSubroutes();
        }
        else {
            view.ShowSubroutes();
        }
    }

    /**
     * Επιστρέφει την λίστα με τις διαδρομές
     * @return η λίστα με τις διαδρομες
     */
    public ArrayList<Subroute> getSubrouteList() {
        return subroutes;
    }


    public void showMessege(String title,String messege) {
        view.showErrorMessage(title, messege);

    }


}
