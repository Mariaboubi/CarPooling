package gr.aueb.carpooling.model.view.passenger.search_route;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;


public class SearchRouteRecyclerViewAdapter extends RecyclerView.Adapter<SearchRouteRecyclerViewAdapter.ViewHolder>{
    private final List<Route> routes;

    private Route currentItem;


    private SearchRouteViewModel viewModel;

    private final SearchRouteRecyclerViewAdapter.SearchRouteSelectionListener listener;

    public SearchRouteRecyclerViewAdapter(List<Route> routes, SearchRouteSelectionListener listener) {
        this.routes = routes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SearchRouteRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_passenger_request_route_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRouteRecyclerViewAdapter.ViewHolder holder, int position) {

        currentItem = routes.get(position);

        holder.routeDest.setText((currentItem.getDestinationString()));
        String str_date = "Date : " + currentItem.getDate().toString();
        holder.routeDate.setText(str_date);
        String str_driver = "Driver : " + currentItem.getDriver().getName();
        holder.routeDriverName.setText(str_driver);
        String str_rate = "Rate :" + currentItem.getDriver().averageRating();
        holder.routeDriverRate.setText(str_rate);


        holder.RequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Route clickroute= routes.get(position);
                listener.selectRoute(clickroute);
            }
        });
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public final TextView routeDest;
        public final TextView routeDate;
        public final TextView routeDriverName;
        public final TextView routeDriverRate;
        public final Button RequestButton;
        public ViewHolder(View v)
        {
            super(v);
            routeDest = (TextView) v.findViewById(R.id.Destination);
            routeDate = (TextView) v.findViewById(R.id.Date);
            routeDriverName = (TextView) v.findViewById(R.id.driver_name);
            routeDriverRate = (TextView) v.findViewById(R.id.driver_rate);

            RequestButton = (Button) v.findViewById(R.id.RequestButton);
        }

    }
    public interface SearchRouteSelectionListener {

        void selectRoute(Route route);
    }
}
