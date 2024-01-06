package gr.aueb.carpooling.model.view.passenger.ExistedSubroutes;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;

public class ExistedSubrouteRecyclerViewAdapter extends RecyclerView.Adapter<ExistedSubrouteRecyclerViewAdapter.ViewHolder> {

    private final List<Subroute> subroutes;

    private Subroute currentSubroute;

    private Route currentItem;

    private ExistedSubrouteView viewModel;

    private ExistedSubroutePresenter presenter;

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
        currentSubroute = subroutes.get(position);

        holder.subrouteDest.setText(currentSubroute.getDestination().toString());
        holder.subroutepickUpPoint.setText(currentSubroute.getPickupPoint().toString());
        holder.subrouteDate.setText((currentSubroute.getPickupTime().toString()));

//        holder.Delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int clickedPosition = holder.getAdapterPosition();
//                if (clickedPosition != RecyclerView.NO_POSITION) {
//                    deleteSubroute(clickedPosition);
//                }
//            }
//        });

    }

    private void deleteSubroute(int position) {
        subroutes.remove(position); // Remove the item from the list
        notifyItemRemoved(position); // Notify adapter about the item removal

        // If needed, notify any listener about the deletion
        if (listener != null) {
            listener.selectSubroute(currentSubroute);
        }
    }


        @Override
    public int getItemCount() {return subroutes.size();}


    /**
     * Αρχικοποιεί τα Text Views που χρησιμοποιούμε στην παραπάνω μέθοδο
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView subrouteDest;
        public final TextView subrouteDate;

      //  public final TextView subrouteName;

        public final TextView subroutepickUpPoint;
        public final Button  Delete;
        public ViewHolder(View v)
        {
            super(v);
            subrouteDest = (TextView) v.findViewById(R.id.Destination);
            subroutepickUpPoint = (TextView) v.findViewById(R.id.pickUpPoint);
            subrouteDate = (TextView) v.findViewById(R.id.Date);
            //subrouteName = (TextView) v.findViewById(R.id.driver_name);
            Delete = (Button) v.findViewById(R.id.DeleteButton);
        }



    }
    public interface SubrouteSelectionListener {
        void selectSubroute(Subroute subroute) ;
    }

}





