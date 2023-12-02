package carPooling;
    
public class Request implements RequestInterface {

    private Passenger passenger;
    private Driver driver;
    private Address destination;
    private Address pickupPoint;
    private Time pickupTime;
    private RequestStatus requestStatus;

    public Request(Passenger passenger, Driver driver, Address destination,
                   Address pickupPoint, Time pickupTime, RequestStatus requestStatus) {
        this.passenger = passenger;
        this.driver = driver;
        this.destination = destination;
        this.pickupPoint = pickupPoint;
        this.pickupTime = pickupTime;
        this.requestStatus = requestStatus;
    }
    
    @Override
    public Passenger getPassenger() {
        return passenger;
    }

    @Override
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public Driver getDriver() {
        return driver;
    }

    @Override
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public Address getDestination() {
        return destination;
    }

    @Override
    public void setDestination(Address destination) {
        this.destination = destination;
    }

    @Override
    public Address getPickupPoint() {
        return pickupPoint;
    }

    @Override
    public void setPickupPoint(Address pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    @Override
    public Time getPickupTime() {
        return pickupTime;
    }

    @Override
    public void setPickupTime(Time pickupTime) {
        this.pickupTime = pickupTime;
    }

    @Override
    public String getRequestStatus() {
        return requestStatus;
    }

    @Override
    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}

