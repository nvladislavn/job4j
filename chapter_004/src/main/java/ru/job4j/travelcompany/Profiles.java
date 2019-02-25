package ru.job4j.travelcompany;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Profiles
 *
 * @author Vladislav Nechaev
 * @since 25.02.2019
 */
public class Profiles {

    /**
     * collect
     *
     * @param profiles - a profiles list.
     * @return - the address list.
     */
    public List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(profile -> profile.getAddress()).collect(Collectors.toList());
    }
}
