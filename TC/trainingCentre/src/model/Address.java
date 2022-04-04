package model;
import java.util.*;

/**
 * 
 */
public class Address {

 
    public Address() {
    }

    private int id;

    //private User user; //Adress now a field of user

    private String street;

    private String houseNumber;

    private String city;

    private String postcode;

    private boolean enabled;

    public Address(int id, String street, String houseNumber, String city, String postcode, boolean enabled) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.postcode = postcode;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", street=" + street + ", houseNumber=" + houseNumber + ", city=" + city + ", postcode=" + postcode + ", enabled=" + enabled + '}';
    }
    
    

}