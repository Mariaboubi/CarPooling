package gr.aueb.carpooling.model.view.driver.RatingPassengers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.dao.PassengerRatingDao;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.memoryDao.PassengerRatingDAOmemory;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;

public class RatingPassengerRecyclerViewAdapter extends RecyclerView.Adapter<RatingPassengerRecyclerViewAdapter.ViewHolder> {
    private final List<Passenger> passengers;

    private PassengerDAO passengerDAO;
    private final PassengerRatingDao passengerRatingDao = new PassengerRatingDAOmemory();
    private final RouteDAO routeDAO = new RouteDAOmemory();

    private RatingPassengersViewModel viewModel;

    private RatingPassengerView view;
    private final RatingPassengerRecyclerViewAdapter.PassengerRatingSelectionListener listener;

    private final int routeId;

    public RatingPassengerRecyclerViewAdapter(ArrayList<Passenger> passengers, PassengerRatingSelectionListener listener, int routeId) {
        this.passengers = passengers;
        this.listener = listener;
        this.routeId = routeId;
    }


    /**
     * Περνάει στον adapter το layout που θέλουμε να εμφανιστούν τα αντικείμενα της λίστας μας
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return νέο αντικείμενο view holder με το custom layout των διαδρομών
     */
    @NonNull
    @Override
    public RatingPassengerRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_driver_rating_passenger_list_item, parent, false));
    }


    /*
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
//    @Override
    public void onBindViewHolder(@NonNull RatingPassengerRecyclerViewAdapter.ViewHolder holder, int position) {
        Passenger currentItem = passengers.get(position);

        String str_username = "Passenger: " + currentItem.getUsername();
        holder.ratingUsername.setText(str_username);

        //Route rating_route = currentItem.getRoute();
//        String reliability = holder.ratingReliability;
//        String politeness = holder.ratingPoliteness;
//        String consistency = holder.ratingConsistency;

        Route route = routeDAO.find(routeId);
//            route.Completed();
//            currentItem.setConsistencyRating(reliability);
//            currentItem.setReliabilityRating(politeness);
//            currentItem.setPolitenessRating(consistency);
        //PassengerRating passengerRating = new PassengerRating(passenger,route,politeness,consistency,reliability);
//            passengerRatingDao.save(currentItem);

        PassengerRating passengerRating = new PassengerRating(currentItem,route,politeness,consistency,reliability);
        route.addPassengerRating(passengerRating);
        passengerRatingDao.save(passengerRating);
        currentItem.addRates(passengerRating);

        holder.RateButton.setOnClickListener(new View.OnClickListener(){
//        route.Completed();
//
//        PassengerRating passengerRating = new PassengerRating(currentItem,route,politeness,consistency,reliability);
//        route.addPassengerRating(passengerRating);
//        passengerRatingDao.save(passengerRating);
//        currentItem.addRates(passengerRating);
//            holder.RateButton.setOnClickListener(v -> listener.selectRate(currentItem),
//                    routeDAO.delete(route);
//            );

        holder.RateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Passenger rated_passenger = passengers.get(position);
                String politeness = holder.ratingPoliteness.getText().toString().trim();
                String consistency = holder.ratingConsistency.getText().toString().trim();
                String reliability = holder.ratingReliability.getText().toString().trim();

                PassengerRating passengerRating = new PassengerRating(rated_passenger, route, politeness, consistency, reliability);
                System.out.println("Rating passenger");
                System.out.println(passengerRating.getPolitenessRating());
                System.out.println(passengerRating.getConsistencyRating());
                System.out.println(passengerRating.getReliabilityRating());

                rated_passenger.addRating(passengerRating);
//                passengers.remove(rated_passenger);
                //listener.selectRate(passengerRating);
            }
        });

    }


    @Override
    public int getItemCount() {
        return passengers.size();
    }

    /**
     * Αρχικοποιεί τα Text Views που χρησιμοποιούμε στην παραπάνω μέθοδο
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView ratingUsername;

        public final EditText ratingPoliteness;

        public EditText ratingReliability;

        public final EditText ratingConsistency;
        public final Button RateButton;

        public ViewHolder(View v) {
            super(v);
            ratingUsername = (TextView) v.findViewById(R.id.PassengerUserName);
            ratingPoliteness = ((EditText) v.findViewById(R.id.Politeness));
            ratingReliability = ((EditText) v.findViewById(R.id.Reliability));
            ratingConsistency = ((EditText) v.findViewById(R.id.Consistency));
//            System.out.println(ratingPoliteness + " " + ratingReliability + " " + ratingConsistency);
            RateButton = (Button) v.findViewById(R.id.RateButton);
        }

    }


    public interface PassengerRatingSelectionListener {
        void selectRate(PassengerRating rating);
    }
}