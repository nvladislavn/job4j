package ru.job4j.services.store;

/**
 * RoleStore
 *
 * @author Vladislav Nechaev
 * @since 12.04.2019
 */
public class RoleStore extends AbstractStore<Role> {

    public RoleStore(int number) {
        super(number);
    }

    /**
     * add
     *
     * @param role - the insance of Role.
     */
    @Override
    public void add(Role role) {
        super.add(role);
    }
}
