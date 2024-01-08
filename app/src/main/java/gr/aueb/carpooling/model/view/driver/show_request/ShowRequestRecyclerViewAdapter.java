package gr.aueb.carpooling.model.view.driver.show_request;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Request_status;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;


public class ShowRequestRecyclerViewAdapter extends RecyclerView.Adapter<ShowRequestRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Subroute> subroutes;

    private final ShowRequestRecyclerViewAdapter.ShowRequestListener listener;
    private final RouteDAO routeDAO = new RouteDAOmemory();
    public ShowRequestRecyclerViewAdapter(ArrayList<Subroute> subroutes, ShowRequestRecyclerViewAdapter.ShowRequestListener listener,ArrayList<Route> routes) {
        this.subroutes = subroutes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ShowRequestRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_driver_show_request_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShowRequestRecyclerViewAdapter.ViewHolder holder, int position) {

        Subroute currentSubroute = subroutes.get(position);
        String str_destination = "Destination address: " + currentSubroute.getDestination().toString();
        holder.routeDest.setText(str_destination);

        String str_date = "Date: " + currentSubroute.getPickupTime().toString();
        holder.routeDate.setText(str_date);

        Passenger passenger = routeDAO.findPassengerBySubroute(currentSubroute);

        String str_name = "Passenger name: " + passenger.getName();
        holder.passengerName.setText(str_name);

        String str_rate = "Passenger rating: " + new DecimalFormat("0.00").format(passenger.averageRating());
        holder.passengerRate.setText(str_rate);

        holder.acceptButton.setOnClickListener(view -> {
            currentSubroute.setStatus(Request_status.APPROVED);

            listener.refreshRequests();
        });

        holder.rejectButton.setOnClickListener(view -> {
            currentSubroute.setStatus(Request_status.REJECTED);

            Route route = routeDAO.findRouteBySubroute(currentSubroute);
            route.removeSubroute(currentSubroute);

            listener.refreshRequests();
        });
    }

    @Override
    public int getItemCount() {
        return subroutes.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public final TextView routeDest;
        public final TextView routeDate;

        public final TextView passengerName;

        public final TextView passengerRate;

        public final Button acceptButton;

        public final Button rejectButton;
        public ViewHolder(View v)
        {
            super(v);
            routeDest = (TextView) v.findViewById(R.id.Destination);
            routeDate = (TextView) v.findViewById(R.id.Date);
            passengerName = (TextView) v.findViewById(R.id.passenger_name);
            passengerRate = (TextView) v.findViewById(R.id.passenger_rate);

            acceptButton = (Button) v.findViewById(R.id.AcceptButton);
            rejectButton = (Button) v.findViewById(R.id.RejectButton);
        }

    }

    public interface ShowRequestListener {
        void refreshRequests();
    }
}
