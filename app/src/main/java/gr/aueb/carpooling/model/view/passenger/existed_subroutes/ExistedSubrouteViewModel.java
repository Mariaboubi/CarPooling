package gr.aueb.carpooling.model.view.passenger.existed_subroutes;

import androidx.lifecycle.ViewModel;


import gr.aueb.carpooling.model.memoryDao.SubrouteDAOmemory;

public class ExistedSubrouteViewModel   extends ViewModel {

    ExistedSubroutePresenter presenter;

    /**
     * Initializes the presenter by passing new DAOs as parameters.
     */
    public ExistedSubrouteViewModel()
    {
        presenter = new ExistedSubroutePresenter(new SubrouteDAOmemory());
    }

    /**
     * Returns the presenter that holds the stored data.
     * @return The instance of the presenter created above.
     */
    public ExistedSubroutePresenter getPresenter() {
        return presenter;
    }
}
