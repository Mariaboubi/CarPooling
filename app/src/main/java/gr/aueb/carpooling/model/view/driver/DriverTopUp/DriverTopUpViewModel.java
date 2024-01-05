package gr.aueb.carpooling.model.view.driver.DriverTopUp;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.dao.DriverDAO;


public class DriverTopUpViewModel extends ViewModel {

    DriverTopUpPresenter presenter;

    public DriverTopUpViewModel(DriverDAO driverDAO){presenter = new DriverTopUpPresenter(driverDAO);}

    public DriverTopUpPresenter getPresenter(){return presenter;}

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("TopUp", "onCleared");
        presenter= null;
    }

}
