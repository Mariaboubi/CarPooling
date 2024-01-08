package gr.aueb.carpooling.model.view.driver.existed_routes;

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
    private final ExistedRouteRecyclerViewAdapter.RouteSelectionListener listener;

    /**
     * Initializes the list of available routes
     * Initializes the Listener object to be used when the driver clicks on a route
     *
     * @param routes   available routes
     * @param listener the item selection listener object to be used
     */
    public ExistedRouteRecyclerViewAdapter(ArrayList<Route> routes, RouteSelectionListener listener) {
        this.routes = routes;
        this.listener = listener;
    }

    /**
     * Inflates the layout to be used for displaying the items in our list
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return a new view holder object with the custom layout of routes
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
        System.out.println(position);

        Route currentItem = routes.get(position);

        holder.routeDest.setText((currentItem.getDestinationString()));

        String str_date = "Date : " + currentItem.getDate().toString();
        holder.routeDate.setText(str_date);

        holder.CompletedButton.setOnClickListener(view -> {
            Route clickedRoute = routes.get(position);
            clickedRoute.Completed();

            listener.selectRoute(clickedRoute);

        });
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }

    /**
     * Initializes the Text Views used in the above method
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
