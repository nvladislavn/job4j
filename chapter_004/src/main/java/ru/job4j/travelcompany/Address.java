package ru.job4j.travelcompany;

import java.util.Objects;

/**
 * Address
 *
 * @author Vladislav Nechaev
 * @since 25.02.2019
 */
public class Address {

    private String city;
    private String street;
    private int home;
    private int apartment;

    public Address(String city, String street, int home, int apartment) {
        this.city = city;
        this.street = street;
        this.home = home;
        this.apartment = apartment;
    }

    public String getCity() {
        return city;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return this.city.equals(address.getCity());
    }

    @Override
    public int hashCode() {
        return 31 + Objects.hash(city);
    }
}
