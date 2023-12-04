package gr.aueb.carpooling.model.contact;

/**
 * Interface for Address functionalities.
 */
public interface AddressInterface {

    /**
     * Sets the street name of the address.
     *
     * @param street The street name to set.
     */
    void setStreet(String street);

    /**
     * Gets the street name of the address.
     *
     * @return The street name.
     */
    String getStreet();

    /**
     * Sets the number of the address.
     *
     * @param number The address number to set.
     */
    void setNumber(String number);

    /**
     * Gets the number of the address.
     *
     * @return The address number.
     */
    String getNumber();

    /**
     * Sets the city of the address.
     *
     * @param city The city name to set.
     */
    void setCity(String city);

    /**
     * Gets the city of the address.
     *
     * @return The city name.
     */
    String getCity();

    /**
     * Sets the zip code of the address.
     *
     * @param zip The ZipCode object to set.
     */
    void setZipCode(ZipCode zip);

    /**
     * Gets the zip code of the address.
     *
     * @return The ZipCode object.
     */
    ZipCode getZipCode();

    /**
     * Sets the country of the address.
     *
     * @param country The country name to set.
     */
    void setCountry(String country);

    /**
     * Gets the country of the address.
     *
     * @return The country name.
     */
    String getCountry();
}
