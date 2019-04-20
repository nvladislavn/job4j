package ru.job4j.services.store;

/**
 * UserStore
 *
 * @author Vladislav Nechaev
 * @since 12.04.2019
 */
public class UserStore extends AbstractStore<User> {

    public UserStore(int number) {
        super(number);
    }

    /**
     * add

     * @param user - the instance of User.
     */
    @Override
    public void add(User user) {
        super.add(user);
    }
}
