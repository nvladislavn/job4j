package ru.job4j.travelcompany;

/**
 * Profile
 *
 * @author Vladislav Nechaev
 * @since 25.02.2019
 */
public class Profile {

    private Address address;

    public Profile(Address address) {
        this.address = address;
    }

    /**
     * getAddress
     * @return - the address
     */
    public Address getAddress() {
        return address;
    }
}
