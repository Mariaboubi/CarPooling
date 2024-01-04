package gr.aueb.carpooling.model.view.driver.RatingPassengers;

import static android.content.Intent.getIntent;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.contact.Address;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.contact.ZipCode;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.dao.PassengerRatingDao;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.view.driver.ExistedRoutes.ExistedRouteViewModel;

public class RatingPassengerRecyclerViewAdapter extends RecyclerView.Adapter<RatingPassengerRecyclerViewAdapter.ViewHolder>{
    private final List<PassengerRating> ratings;

    private PassengerDAO passengerDAO;
    private PassengerRatingDao passengerRatingDao;
    private RouteDAO routeDAO;
    private PassengerRating currentItem;
    private RatingPassengersViewModel viewModel;
    private final RatingPassengerRecyclerViewAdapter.PassengerRatingSelectionListener listener;

    private int route_id;

    public  RatingPassengerRecyclerViewAdapter(ArrayList<PassengerRating> ratings, PassengerRatingSelectionListener listener,int route_id){
        this.ratings = ratings;
        this.listener=listener;
        this.route_id = route_id;
    }



    /**
     * Περνάει στον adapter το layout που θέλουμε να εμφανιστούν τα αντικείμενα της λίστας μας
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return νέο αντικείμενο view holder με το custom layout των διαδρομών
     */
    @NonNull
    @Override
    public RatingPassengerRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rating_passenger_list_item, parent, false));
    }


    /*
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
//    @Override
public void onBindViewHolder(@NonNull RatingPassengerRecyclerViewAdapter.ViewHolder holder, int position) {
        currentItem = ratings.get(position);

        holder.ratingName.setText((currentItem.getPassenger().getName()));
        holder.ratingSurname.setText((currentItem.getPassenger().getSurname()));
        onCreateRatingPassenger(currentItem);


}

    public void onCreateRatingPassenger(PassengerRating currentItem) {
//        boolean isEmpty = false;
//        RatingPassengerView view = null;
//        HashMap<String, String> details = view.getRateDetails();
//
//        for (Map.Entry<String, String> set : details.entrySet()) {
//            if (set.getValue().isEmpty() || set.getValue() == null) {
//                isEmpty = true;
//                break;
//            }
//        }
//        float consisteny = Float.parseFloat(details.get("Consistency"));
//        float reliability = Float.parseFloat(details.get("Reliability"));
//        float politeness = Float.parseFloat(details.get("Politeness"));
//        if (isEmpty) {
//            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε όλα τα πεδία!.");
//        } else if (consisteny < 0.0f || consisteny > 5.0f) {
//            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε αριθμό αναμεσα στο 0 και στο 5");
//        } else if (reliability < 0.0f || reliability > 5.0f) {
//            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε αριθμό αναμεσα στο 0 και στο 5");
//        } else if (politeness < 0.0f || politeness > 5.0f) {
//            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε αριθμό αναμεσα στο 0 και στο 5");
//
//        } else {
//            Passenger passenger = passengerDAO.findByName(currentItem.getPassenger().getName());
//            Route route = routeDAO.find(route_id);
//            PassengerRating passengerRating = new PassengerRating(passenger,route,consisteny,reliability,politeness);
//            passengerRatingDao.save(passengerRating);
//        }
    }

    @Override
    public int getItemCount() {
        return ratings.size();
    }

    /**
     * Αρχικοποιεί τα Text Views που χρησιμοποιούμε στην παραπάνω μέθοδο
     */
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public final TextView ratingName;

        public final TextView ratingSurname;
        public final TextView ratingPoliteness;

        public final TextView ratingReliability;

        public final TextView ratingConsistency;
        public final Button RateButton;
        public ViewHolder(View v)
        {
            super(v);
            ratingName = (TextView) v.findViewById(R.id.PassengerName);
            ratingSurname = (TextView) v.findViewById(R.id.PassengerSurname);
            ratingPoliteness = (TextView) v.findViewById(R.id.politenessText);
            ratingReliability = (TextView) v.findViewById(R.id.reliabilityText);
            ratingConsistency = (TextView) v.findViewById(R.id.consistencyText);
            RateButton = (Button) v.findViewById(R.id.RateButton);
        }

    }


    public interface PassengerRatingSelectionListener {
        void selectRate(PassengerRating rating);
    }
}