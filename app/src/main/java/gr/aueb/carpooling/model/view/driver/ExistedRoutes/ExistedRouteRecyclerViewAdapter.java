package gr.aueb.carpooling.model.view.driver.ExistedRoutes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.Route;

public class ExistedRouteRecyclerViewAdapter extends RecyclerView.Adapter<ExistedRouteRecyclerViewAdapter.ViewHolder> {
    private final List<Route> routes;

    private Route currentItem;
    private ExistedRouteViewModel viewModel;
    private final ExistedRouteRecyclerViewAdapter.RouteSelectionListener listener;

    /**
     * Αρχικοποιεί την λίστα με τις διαθέσιμες διαδρομές
     * Αρχικοποιεί το αντικείμενο Listener που θα χρησιμοποιηθεί όταν ο οδηγός πατήσει επάνω σε κάποια διαδρομή
     *
     * @param routes   διαθέσιμες διαδρομές
     * @param listener το αντικείμενο item selection listener που θα χρησιμοποιήσουμε
     */
    public ExistedRouteRecyclerViewAdapter(ArrayList<Route> routes, RouteSelectionListener listener) {
        this.routes = routes;
        this.listener = listener;
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
    public ExistedRouteRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_driver_route_list_item, parent, false));
    }


    /*
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ExistedRouteRecyclerViewAdapter.ViewHolder holder, int position) {
        currentItem = routes.get(position);

        holder.routeDest.setText((currentItem.getDestinationString()));

        String str_date = "Date : " + currentItem.getDate().toString();
        holder.routeDate.setText(str_date);
        boolean b = currentItem.isCompleted();

        // holder.routeCompleted.setText((String.valueOf(b)));
//        holder.routeDest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.selectRoute(currentItem);
//            }m
//        });
        holder.CompletedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentItem.Completed();
                boolean b = currentItem.isCompleted();
                System.out.print(currentItem.getDestination().toString());

                // holder.routeCompleted.setText((String.valueOf(b)));
                listener.selectRoute(currentItem);

//                Context context = view.getContext();
//                Intent intent = new Intent(context, RatingPassengers.class);
//                // extras
//                intent.putExtra("Route id",currentItem.getId());
                //context.startActivity(intent);

//                Intent intent = new Intent(this , RatingPassengers.class);
//                intent.putExtra("Route id",currentItem.getId());
//                startActivity(intent);
//                viewModel.getPresenter().showError(b);
            }

        });

    }

    @Override
    public int getItemCount() {
        return routes.size();
    }

    /**
     * Αρχικοποιεί τα Text Views που χρησιμοποιούμε στην παραπάνω μέθοδο
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView routeDest;
        public final TextView routeDate;

        //public final TextView routeCompleted;
        public final Button CompletedButton;

        public ViewHolder(View v) {
            super(v);
            routeDest = (TextView) v.findViewById(R.id.Destination);
            routeDate = (TextView) v.findViewById(R.id.Date);
            //routeCompleted = (Button) v.findViewById(R.id.Completed);
            CompletedButton = (Button) v.findViewById(R.id.CompletedButton);
        }

    }


    public interface RouteSelectionListener {
        void selectRoute(Route route);


    }

}
