package gr.aueb.carpooling.model.view.passenger.top_up;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.dao.PassengerDAO;

public class TopUpViewModel  extends ViewModel {

    TopUpPresenter presenter;

    public TopUpViewModel(PassengerDAO passengerDAO){presenter = new TopUpPresenter(passengerDAO);}

    public TopUpPresenter getPresenter(){return presenter;}

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("TopUp", "onCleared");
        presenter= null;
    }

}
