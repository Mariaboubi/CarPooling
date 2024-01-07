package gr.aueb.carpooling.model.contact;

import androidx.annotation.NonNull;

import java.util.Objects;
import java.util.Random;

public class ZipCode {
    private String zipcode;

    private  double latitude ;
    private   double longitude ;

    Random random = new Random();

    public ZipCode(String zipcode) {
        this.latitude = 35.0 +(42.0-35.0)* random.nextDouble();
        this.longitude = 19.0 +(28.0-19.0)* random.nextDouble();
        this.zipcode = zipcode;
    }

    public String getCode() {
        return zipcode;
    }

    public void setCode(String zip) throws IllegalArgumentException {
        if (!isValid(zip)) {
            throw new IllegalArgumentException("Invalid zipcode");
        }
        this.zipcode = zip;
    }


    public static boolean isValid(String zipcode) {
        return zipcode != null && zipcode.matches("[0-9]{5}");
    }

    public  double getLatitude() {
        return this.latitude;
    }

    public  double getLongitude() {
        return this.longitude;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this == other) {
            return true;
        }

        if (!(other instanceof ZipCode)) {
            return false;
        }

        ZipCode theZipCode = (ZipCode) other;
        return Objects.equals(zipcode, theZipCode.zipcode);
    }

    @Override
    public int hashCode() {
        return zipcode == null ? 0 : zipcode.hashCode();
    }


    @NonNull
    public String toString() {
        return zipcode;
    }
}