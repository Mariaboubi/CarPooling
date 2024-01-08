package gr.aueb.carpooling.model.view.passenger.ExistedSubroutes;

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
import gr.aueb.carpooling.model.Request_status;
import gr.aueb.carpooling.model.Subroute;

public class ExistedSubrouteRecyclerViewAdapter extends RecyclerView.Adapter<ExistedSubrouteRecyclerViewAdapter.ViewHolder> {

    private final List<Subroute> subroutes;

    private final ExistedSubrouteRecyclerViewAdapter.SubrouteSelectionListener listener;

    /**
     * Αρχικοποιεί την λίστα με τις διαθέσιμες διαδρομές
     * Αρχικοποιεί το αντικείμενο Listener που θα χρησιμοποιηθεί όταν ο επιβατης πατήσει επάνω σε κάποια διαδρομή
     * @param subroutes διαθέσιμες διαδρομές
     * @param listener το αντικείμενο item selection listener που θα χρησιμοποιήσουμε
     */
    public   ExistedSubrouteRecyclerViewAdapter(ArrayList<Subroute> subroutes , SubrouteSelectionListener listener){
        this.subroutes = subroutes;
        this.listener = listener;
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
    public ExistedSubrouteRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExistedSubrouteRecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_passenger_existed_subroute_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExistedSubrouteRecyclerViewAdapter.ViewHolder holder, int position) {
        Subroute currentSubroute = subroutes.get(position);

        currentSubroute.setStatus(Request_status.APPROVED);
        String str_dest = "Destination address: " + currentSubroute.getDestination().toString2();
        holder.subrouteDest.setText(str_dest);

        String pickUpPoint = "Pick up point: " + currentSubroute.getPickupPoint().toString2();
        holder.subroutepickUpPoint.setText(pickUpPoint);

        String status = "Status: " + currentSubroute.getStatus().toString();
        holder.subrouteStatus.setText(status);

        String date = "Date: " + currentSubroute.getPickupTime().toString();
        holder.subrouteDate.setText(date);





        holder.CompletedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Subroute completed_subroute = subroutes.get(position);
                listener.selectSubroute(completed_subroute, completed_subroute.getStatus(), true);

            }

        });

        holder.DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Subroute clicksubroute= subroutes.get(position);
                listener.selectSubroute(clicksubroute, clicksubroute.getStatus(), false);
            }

        });

    }



        @Override
    public int getItemCount() {return subroutes.size();}


    /**
     * Αρχικοποιεί τα Text Views που χρησιμοποιούμε στην παραπάνω μέθοδο
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView subrouteDest;
        public final TextView subrouteDate;

        public final TextView subrouteStatus;

        public final TextView subroutepickUpPoint;
        public final Button  CompletedButton;

        public final Button  DeleteButton;
        public ViewHolder(View v)
        {
            super(v);
            subrouteDest = (TextView) v.findViewById(R.id.Destination);
            subroutepickUpPoint = (TextView) v.findViewById(R.id.pickUpPoint);
            subrouteDate = (TextView) v.findViewById(R.id.Date);
            subrouteStatus = (TextView) v.findViewById(R.id.RequestStatus);
            CompletedButton = (Button) v.findViewById(R.id.ComletedButton);
            DeleteButton = (Button) v.findViewById(R.id.DeletedButton);
        }



    }
    public interface SubrouteSelectionListener {
        void selectSubroute(Subroute subroute,Request_status status,Boolean b) ;
    }

}





