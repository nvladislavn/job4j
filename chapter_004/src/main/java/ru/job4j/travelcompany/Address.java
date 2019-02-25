package ru.job4j.travelcompany;

/**
 * Address
 *
 * @author Vladislav Nechaev
 * @since 25.02.2019
 */
public class Address {

    private String sity;
    private String street;
    private int home;
    private int apartment;

    public Address(String sity, String street, int home, int apartment) {
        this.sity = sity;
        this.street = street;
        this.home = home;
        this.apartment = apartment;
    }

    public String getSity() {
        return sity;
    }

    public String getStreet() {
        return street;
    }

    public int getHome() {
        return home;
    }

    public int getApartment() {
        return apartment;
    }
}
