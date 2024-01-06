package gr.aueb.carpooling.model.view.driver.show_request;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import gr.aueb.carpooling.model.Subroute;


public class ShowRequestRecyclerViewAdapter extends RecyclerView.Adapter<ShowRequestRecyclerViewAdapter.ViewHolder> {

    private final List<Subroute> subroutes;
    private Subroute currentItem;
    private ShowRequestViewModel viewModel;
    private final ShowRequestRecyclerViewAdapter.ShowRequestListener listener;
    public ShowRequestRecyclerViewAdapter(ArrayList<Subroute> subroutes, ShowRequestRecyclerViewAdapter.ShowRequestListener listener) {
        this.subroutes = subroutes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ShowRequestRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowRequestRecyclerViewAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
//        public final TextView routeDest;
//        public final TextView routeDate;
//
//        //public final TextView routeCompleted;
//        public final Button CompletedButton;
        public ViewHolder(View v)
        {
            super(v);
//            routeDest = (TextView) v.findViewById(R.id.Destination);
//            routeDate = (TextView) v.findViewById(R.id.Date);
//            //routeCompleted = (Button) v.findViewById(R.id.Completed);
//            CompletedButton = (Button) v.findViewById(R.id.CompletedButton);
        }

    }

    public interface ShowRequestListener {
        void selectRequest();


    }
}
