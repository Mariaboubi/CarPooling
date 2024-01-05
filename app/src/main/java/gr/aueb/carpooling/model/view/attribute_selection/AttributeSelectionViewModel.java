package gr.aueb.carpooling.model.view.attribute_selection;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;

import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;

public class AttributeSelectionViewModel extends ViewModel {
    AttributeSelectionPresenter presenter;

    /**
     * Initializes the presenter and passes a new instance of driver DAO and passenger DAO for it to use.
     */
    public AttributeSelectionViewModel() {
        presenter = new AttributeSelectionPresenter(new DriverDAOmemory(), new PassengerDAOmemory());
    }

    /**
     * Returns the presenter to the classes that contain the information.
     *
     * @return the instance of the presenter created above
     */
    public AttributeSelectionPresenter getPresenter() {
        return this.presenter;
    }
}
