package gr.aueb.carpooling.model.view.driver.show_request;

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
import gr.aueb.carpooling.model.Subroute;


public class ShowRequestRecyclerViewAdapter extends RecyclerView.Adapter<ShowRequestRecyclerViewAdapter.ViewHolder> {

    private final List<Subroute> subroutes;

    private List<Route> routes;

    private Subroute currentItem;
    private ShowRequestViewModel viewModel;
    private final ShowRequestRecyclerViewAdapter.ShowRequestListener listener;
    public ShowRequestRecyclerViewAdapter(ArrayList<Subroute> subroutes, ShowRequestRecyclerViewAdapter.ShowRequestListener listener,List<Route> routes) {
        this.subroutes = subroutes;
        this.routes = routes;
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
        currentItem = subroutes.get(position);

        holder.routeDest.setText((currentItem.getDestination().toString()));
        String str_date = currentItem.getPickupTime().toString();
        holder.routeDate.setText(str_date);

        //routes.findPassengerBySubroute(currentItem);
        //holder.passengerName
//        holder.passengerName = currentItem.


        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
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
        void selectRequest();


    }
}
