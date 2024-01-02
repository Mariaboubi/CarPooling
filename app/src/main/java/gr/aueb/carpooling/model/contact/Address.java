package gr.aueb.carpooling.model.contact;
import androidx.annotation.NonNull;

import gr.aueb.carpooling.model.contact.ZipCode;
import java.util.Objects;

public class Address implements AddressInterface {
    private String street;
    private String number;
    private String city;
    private ZipCode zip;
    private String country = "Greece";

    public Address() {
    }

    public Address(String street,String number,String city,ZipCode zip, String country) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.zip = zip;
        this.country = country;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setZipCode(ZipCode zipcode) {
        this.zip = zipcode;
    }

    public ZipCode getZipCode() {
        return zip;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof Address)) {
            return false;
        }

        Address theAddress = (Address) other;
        if (!(Objects.equals(street, theAddress.street))) {
            return false;
        }
        if (!(Objects.equals(number, theAddress.number))) {
            return false;
        }
        if (!(Objects.equals(city, theAddress.city))) {
            return false;
        }
        if (!(Objects.equals(zip, theAddress.zip))) {
            return false;
        }
        if (!(Objects.equals(country, theAddress.country))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        if (street == null && number == null && city == null
                && zip == null && country == null) {
            return 0;
        }

        int result = 0;
        result = street == null ? result : 13 * result + street.hashCode();
        result = number == null ? result : 13 * result + number.hashCode();
        result = city == null ? result : 13 * result + city.hashCode();
        result = zip == null ? result : 13 * result + zip.hashCode();
        result = country == null ? result : 13 * result + country.hashCode();
        return result;
    }

    public double calculateDistance(Address otherAddress) {
        double earthRadius = 6371; // Radius of the Earth in kilometers

        double lat1 = Math.toRadians(this.getZipCode().getLatitude());
        double lon1 = Math.toRadians(this.getZipCode().getLongitude());
        double lat2 = Math.toRadians(otherAddress.getZipCode().getLatitude());
        double lon2 = Math.toRadians(otherAddress.getZipCode().getLongitude());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }
    @NonNull
    public String toString() {
        return String.format(
                "Address: %s %s, %s, %s, %s, %s",
                street, number, city, zip, country
        );
    }
}
