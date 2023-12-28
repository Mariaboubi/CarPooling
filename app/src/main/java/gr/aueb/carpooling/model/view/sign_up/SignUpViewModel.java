package gr.aueb.carpooling.model.view.sign_up;

import gr.aueb.carpooling.model.memoryDao.UserDAOmemory;
import androidx.lifecycle.ViewModel;

public class SignUpViewModel  extends ViewModel{
    private SignUpPresenter signUpPresenter;

    public SignUpViewModel() {signUpPresenter = new SignUpPresenter(new UserDAOmemory());}

    public SignUpPresenter getPresenter() {return signUpPresenter;}
}
