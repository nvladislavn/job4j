package ru.job4j.sort;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * SortUserTest
 *
 * @author Vladislav Nechaev
 * @since 11.01.2019
 */
public class SortUserTest {

    SortUser sort;
    StringBuilder actual;

    @Before
    public void createSortUser() {
        this.sort = new SortUser();
        this.actual = new StringBuilder();
    }

    /**
     * tests sort.
     */
    @Test
   public void whenJohnKateMichelleThenKateMichelleJohn() {
        List<User> userList = new ArrayList<>(Arrays.asList(
                                                        new User("John", 45),
                                                            new User("Kate", 25),
                                                            new User("Michelle", 35)
                                                            )
                                                );
        for (User user : sort.sort(userList)) {
            actual.append(user.getName());
        }
        assertThat(actual.toString(), is("KateMichelleJohn"));
    }

    /**
     * tests sortNameLength.
     */
    @Test
    public void whenJohnMichelleGarryThenJohnGarryMichelle() {
        List<User> userList = new ArrayList<>(Arrays.asList(
                                                        new User("John", 45),
                                                        new User("Michelle", 25),
                                                        new User("Garry", 35)
                                                            )
                                                );
        for (User user : this.sort.sortNameLength(userList)) {
            actual.append(user.getName());
        }
        assertThat(actual.toString(), is("JohnGarryMichelle"));
    }

    /**
     * tests sortByFields.
     */
    @Test
    public void whenJohnKateJohnThenJohnJohnKate() {
        List<User> userList = new ArrayList<>(Arrays.asList(
                                                        new User("John", 45),
                                                        new User("Kate", 25),
                                                        new User("John", 35),
                                                        new User("Kate", 20)
                                                        )
                                                );
        for (User user : this.sort.sortByFields(userList)) {
            actual.append(user.getName()).append(user.getAge());
        }
        assertThat(actual.toString(), is("John35John45Kate20Kate25"));
    }
}