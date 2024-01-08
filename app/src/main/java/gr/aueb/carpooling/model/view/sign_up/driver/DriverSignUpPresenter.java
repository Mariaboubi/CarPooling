package gr.aueb.carpooling.model.view.sign_up.driver;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.dao.UserDAO;

public class DriverSignUpPresenter {
    private final UserDAO userDao;

    private final DriverDAO driverDao;

    private DriverSignUpView view;

    public DriverSignUpPresenter(UserDAO userDao, DriverDAO driverDao) {
        this.userDao = userDao;
        this.driverDao = driverDao;
    }

    public void setView(DriverSignUpView v) {
        this.view = v;
    }

    public DriverSignUpView getView() {
        return view;
    }

    public void onCreateDriverAccount(String username) {
        String inputIban = view.getIban();
        String inputDriverLicense = view.getDriverLicense();
        String inputCarType = view.getCarType();

        if(inputIban.isEmpty() || inputCarType.isEmpty() || inputDriverLicense.isEmpty()) {
            view.showErrorMessage("Error!", "Complete all the fields");
        } else {
            User user = userDao.findByUsername(username);
            Driver driver = new Driver(user.getUsername(),user.getName(),user.getSurname(),user.getPhone(),user.getEmail(),user.getPassword(),user.getAge(),inputIban,inputDriverLicense,inputCarType);
            driverDao.save(driver);

            boolean b =driverDao.find(username);
            view.showRegistrationSuccessMessage("is registered as driver",String.valueOf(b));
        }
    }
}
