package gr.aueb.carpooling.model;

import static gr.aueb.carpooling.model.Request_status.APPROVED;
import static gr.aueb.carpooling.model.Request_status.NOT_EXIST;
import static gr.aueb.carpooling.model.Request_status.PENDING;
import static gr.aueb.carpooling.model.Request_status.REJECTED;

import org.threeten.bp.LocalDateTime;
import java.util.Currency;
import java.util.Objects;

import gr.aueb.carpooling.model.contact.Address;
import gr.aueb.carpooling.model.contact.Money;

public class Subroute implements SubrouteInterface {
    private static int subroute_id = 0; // Id of Subrote Objects
    private int id; // Id of the Subroute
    private Address destination; // The destination of the subroute
    private Money cost; // The cost of the subroute
    private Address pickupPoint; // The Address of the pick up point
    private LocalDateTime pickupTime; // The Time of the pick up time
    private final Currency euroCurrency = Currency.getInstance("EUR");

    private Request_status status;


    public Subroute(Address destination, Address pickupPoint, LocalDateTime pickupTime) {
        this.id = ++subroute_id;
        this.destination = destination;
        this.pickupPoint = pickupPoint;
        this.pickupTime = pickupTime;
        this.cost = new Money(0.0, euroCurrency);
        this.status = NOT_EXIST;
    }


    public void setPendingStatus() {
        this.status = PENDING;
    }
    public void setApprovedStatus() {
        this.status = APPROVED;
    }
    public void setRejectedStatus() {
        this.status = REJECTED;
    }
    public void setStatus(Request_status stat) {
        this.status = stat;
    }

    public Request_status getStatus() {
        return this.status;
    }
    public Address getDestination() {
        return destination;
    }

    public int getId() {
        return id;
    }
    public Money getCost() {
        return cost;
    }

    public void setCost(Money cost) {
        this.cost = cost;
    }

    public void setDestination(Address destination) {
        this.destination = destination;
    }

    public Address getPickupPoint() {
        return pickupPoint;
    }


    public void setPickupPoint(Address pickupPoint) {
        this.pickupPoint = pickupPoint;
    }


    public LocalDateTime getPickupTime() {
        return pickupTime;
    }


    public void setPickupTime(LocalDateTime pickupTime)  {this.pickupTime = pickupTime;}


    public Money calculateCost() {
        double distance = this.getPickupPoint().calculateDistance(this.getDestination());
        double cost = distance * AppGlobals.COST_PER_KM;
        Money money = new Money(cost, euroCurrency);
        setCost(money);
        return money;
    }
}