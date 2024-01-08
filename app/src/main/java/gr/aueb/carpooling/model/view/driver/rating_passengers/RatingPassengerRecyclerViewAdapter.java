package gr.aueb.carpooling.model.view.driver.rating_passengers;

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
import gr.aueb.carpooling.model.dao.PassengerRatingDao;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.memoryDao.PassengerRatingDAOmemory;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;

public class RatingPassengerRecyclerViewAdapter extends RecyclerView.Adapter<RatingPassengerRecyclerViewAdapter.ViewHolder> {
    private final List<Passenger> passengers;

    private final PassengerRatingDao passengerRatingDao = new PassengerRatingDAOmemory();
    private final RouteDAO routeDAO = new RouteDAOmemory();

    private final RatingPassengerRecyclerViewAdapter.PassengerRatingSelectionListener listener;

    private final int routeId;

    public RatingPassengerRecyclerViewAdapter(ArrayList<Passenger> passengers, PassengerRatingSelectionListener listener, int routeId) {
        this.passengers = passengers;
        this.listener = listener;
        this.routeId = routeId;
    }


    /**
     * Inflates the layout to be used for displaying the items in our list
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return a new view holder object with the custom layout of passenger ratings
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
    @Override
    public void onBindViewHolder(@NonNull RatingPassengerRecyclerViewAdapter.ViewHolder holder, int position) {
        Passenger currentItem = passengers.get(position);

        String str_username = "Passenger: " + currentItem.getUsername();
        holder.ratingUsername.setText(str_username);

        Route route = routeDAO.find(routeId);

        holder.RateButton.setOnClickListener(v -> {
            Passenger rated_passenger = passengers.get(position);
            String politeness = holder.ratingPoliteness.getText().toString().trim();
            String consistency = holder.ratingConsistency.getText().toString().trim();
            String reliability = holder.ratingReliability.getText().toString().trim();

            boolean result = listener.validateRates(rated_passenger, politeness, consistency, reliability, route);
            if (!result) {
                return;
            }
            PassengerRating passengerRating = new PassengerRating(rated_passenger, route, politeness, consistency, reliability);

            passengerRatingDao.save(passengerRating);

            rated_passenger.addRating(passengerRating);
            route.addPassengerRating(passengerRating);

            listener.selectRate(passengerRating);

        });


    }

    @Override
    public int getItemCount() {
        return passengers.size();
    }

    /**
     * Initializes the Text Views and Buttons used in the above method
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
            RateButton = (Button) v.findViewById(R.id.RateButton);
        }

    }


    public interface PassengerRatingSelectionListener {
        void selectRate(PassengerRating rating);

        boolean validateRates(Passenger passenger, String politeness, String consistency, String reliability, Route route);

    }

}
