package gr.aueb.carpooling.model.view.sign_up;

import gr.aueb.carpooling.model.memoryDao.UserDAOmemory;

public class SignUpViewModel {
    private SignUpPresenter signUpPresenter;

    public SignUpViewModel() {signUpPresenter = new SignUpPresenter(new UserDAOmemory());}

    public SignUpPresenter getPresenter() {return signUpPresenter;}
}
