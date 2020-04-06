/**
 * User
 *
 * @author Vladislav Nechaev
 * @since 06.04.2020
 */
public class User {

    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        System.err.println(name);
    }

    @Override
    protected void finalize() throws Throwable {
        System.err.printf("Finalizing. Free memory %s%s", Runtime.getRuntime().freeMemory(), System.lineSeparator());
        super.finalize();
        System.err.printf("Destroy an instance of User with name:   %s%s", name, System.lineSeparator());
    }
}
