package gr.aueb.carpooling.model.view.LogIn;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.UserDAOmemory;

public class LoginViewModel extends ViewModel {
    private final LogInPresenter loginPresenter;
    public LoginViewModel()
    {
        loginPresenter = new LogInPresenter(new UserDAOmemory());
    }
    public LogInPresenter getPresenter() {
        return loginPresenter;
    }
}
