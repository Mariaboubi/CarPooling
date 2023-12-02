public class Address {
    private String street;
    private String number;
    private String city;
    private ZipCode zip;
    private String country = "Ελλάδα";

    
    public Address() {
    }

   
    public Address(Address address) {
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.city = address.getCity();
        this.zip = address.getZipCode();
        this.country = address.getCountry();
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
        if (!(street == null ? theAddress.street == null : street.equals(theAddress.street))) {
            return false;
        }
        if (!(number == null ? theAddress.number == null : number.equals(theAddress.number))) {
            return false;
        }
        if (!(city == null ? theAddress.city == null : city.equals(theAddress.city))) {
            return false;
        }
        if (!(zip == null ? theAddress.zip == null : zip.equals(theAddress.zip))) {
            return false;
        }
        if (!(country == null ? theAddress.country == null : country.equals(theAddress.country))) {
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
}