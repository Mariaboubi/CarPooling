package carPooling;
import java.sql.Time;

public interface Request {
	
    Passenger getPassenger();
    void setPassenger(Passenger passenger);

    Driver getDriver();
    void setDriver(Driver driver);

    Address getDestination();
    void setDestination(Address destination);

    Address getPickupPoint();
    void setPickupPoint(Address pickupPoint);

    Time getPickupTime();
    void setPickupTime(Time pickupTime);

    String getRequestStatus();
    void setRequestStatus(String requestStatus);
    
}

