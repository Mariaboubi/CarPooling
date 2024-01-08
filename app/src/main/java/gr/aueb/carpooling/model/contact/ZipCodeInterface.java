package gr.aueb.carpooling.model.contact;

import androidx.annotation.NonNull;

public interface ZipCodeInterface {
    /**
     * Returns the zipcode.
     *
     * @return A string representing the zipcode.
     */
    String getCode();

    /**
     * Generates a hash code for the ZipCode.
     *
     * @return An integer hash code.
     */
    int hashCode();

    /**
     * Compares this ZipCode object with another object for equality.
     *
     * @param other The object to be compared with this ZipCode.
     * @return true if the given object represents a ZipCode equivalent to this object, false otherwise.
     */
    boolean equals(Object other);

    /**
     * Returns a string representation of the ZipCode.
     *
     * @return A string representation of the ZipCode.
     */
    @NonNull
    @Override
    String toString();
}
