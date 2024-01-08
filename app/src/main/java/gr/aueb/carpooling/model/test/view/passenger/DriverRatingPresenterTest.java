package gr.aueb.carpooling.model.test.view.passenger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.DriverRating;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.dao.DriverRatingDAO;
import gr.aueb.carpooling.model.view.passenger.driver_rating.DriverRatingPresenter;
import gr.aueb.carpooling.model.view.passenger.driver_rating.DriverRatingView;

public class DriverRatingPresenterTest {

    private DriverRatingPresenter presenter;
    private DriverRatingViewStub view;
    private DriverRatingDAOStub driverRatingDAO;
    private Passenger passenger;
    private Route route;

    @BeforeEach
    public void setUp() {
        view = new DriverRatingViewStub();
        driverRatingDAO = new DriverRatingDAOStub();
        presenter = new DriverRatingPresenter(driverRatingDAO);
        presenter.setView(view);

        // Create a passenger, route, and associated driver for testing
        passenger = new Passenger("maria123", "Maria", "Mpoumpi", "1234567890", new EmailAddress("maria@gmail.com"), "12345678", "25", "1234567890","cardholder", "123");
        Driver driver = new Driver("eleniz", "Eleni", "Zanou", "9876543210",  new EmailAddress("eleni@gmail.com"), "driverpass", "11111111", "DL12345", "Car123", "mercedes");
        route = new Route(driver, null, null, null, 0, false);
    }

    @Test
    public void testOnCreateRateWithValidData() {
        // Set up the view to return valid data
        view.setPoliteness("4.0");
        view.setSecurity("4.5");
        view.setCleanliness("3.5");

        // Invoke the method under test
        presenter.onCreateRate(passenger, route);

        // Verify that the rate is added successfully
        assertTrue(driverRatingDAO.isSaveCalled());
        assertTrue(view.isRateAddedCalled());
    }

    @Test
    public void testOnCreateRateWithEmptyFields() {
        // Set up the view to return empty fields
        view.setPoliteness("");
        view.setSecurity("");
        view.setCleanliness("");

        // Invoke the method under test
        presenter.onCreateRate(passenger, route);

        // Verify that an error message is shown and no rate is added
        assertTrue(view.isShowErrorMessageCalled());
        assertFalse(driverRatingDAO.isSaveCalled());
        assertFalse(view.isRateAddedCalled());
    }

    @Test
    public void testOnCreateRateWithInvalidRatings() {
        // Set up the view to return invalid ratings
        view.setPoliteness("6.0");
        view.setSecurity("-1.0");
        view.setCleanliness("5.5");

        // Invoke the method under test
        presenter.onCreateRate(passenger, route);

        // Verify that error messages are shown for each invalid rating and no rate is added
        assertTrue(view.isShowErrorMessageCalled());
        assertFalse(driverRatingDAO.isSaveCalled());
        assertFalse(view.isRateAddedCalled());
    }

    @Test
    public void testCheckButtonCanBePressedWithAllFieldsEmpty() {
        // Set up the view to return empty fields
        view.setPoliteness("");
        view.setSecurity("");
        view.setCleanliness("");

        // Invoke the method under test
        boolean result = presenter.checkButtonCanBePressed();

        // Verify that the method returns true since all fields are empty
        assertTrue(result);
    }

    @Test
    public void testCheckButtonCanBePressedWithNonEmptyFields() {
        // Set up the view to return non-empty fields
        view.setPoliteness("4.0");
        view.setSecurity("3.5");
        view.setCleanliness("2.0");

        // Invoke the method under test
        boolean result = presenter.checkButtonCanBePressed();

        // Verify that the method returns false since at least one field is non-empty
        assertFalse(result);
    }

    // DriverRatingViewStub class for testing
    private static class DriverRatingViewStub implements DriverRatingView {
        private String politeness;
        private String security;
        private String cleanliness;
        private boolean rateAddedCalled;
        private boolean showErrorMessageCalled;

        @Override
        public String politeness() {
            return politeness;
        }

        @Override
        public String security() {
            return security;
        }

        @Override
        public String cleanliness() {
            return cleanliness;
        }

        @Override
        public void showErrorMessage(String title, String message) {
            showErrorMessageCalled = true;
        }

        @Override
        public void RateAdded() {
            rateAddedCalled = true;
        }

        public void setPoliteness(String politeness) {
            this.politeness = politeness;
        }

        public void setSecurity(String security) {
            this.security = security;
        }

        public void setCleanliness(String cleanliness) {
            this.cleanliness = cleanliness;
        }

        public boolean isRateAddedCalled() {
            return rateAddedCalled;
        }

        public boolean isShowErrorMessageCalled() {
            return showErrorMessageCalled;
        }
    }

    // DriverRatingDAOStub class for testing
    private static class DriverRatingDAOStub implements DriverRatingDAO {
        private boolean saveCalled;

        @Override
        public void deleteAll() {
        }

        @Override
        public void save(DriverRating entity) {
            saveCalled = true;
        }

        @Override
        public ArrayList<DriverRating> findAll() {
            return null;
        }

        public boolean isSaveCalled() {
            return saveCalled;
        }
    }
}
