/**
 * Main
 *
 * @author Vladislav Nechaev
 * @since 06.04.2020
 */
public class Main {

    private static Runtime runtime = Runtime.getRuntime();
    public static final String LS = System.lineSeparator();

    public static void main(String[] args) {
        System.out.printf("Start Total memory %s byte%s", runtime.totalMemory(), LS);
        System.out.printf("Start Free memory %s byte%s", runtime.freeMemory(), LS);
        int nameNumber = 0;
        int counter = 0;
        for (int i = 1; i < 1001; i++) {
            new User(i, "name" + nameNumber);
            counter++;
            System.err.printf("%d. User with name name%d created%s", counter, nameNumber, LS);
            nameNumber = nameNumber < 9 ? ++nameNumber : 0;
            if (i % 100 == 0) {
                runtimeInfo();
            }
        }
    }

    private static void runtimeInfo() {
        System.err.printf(
                "Available free memory  %s%s",
                (runtime.freeMemory()),
                LS
        );
        System.err.printf(
                "Used memory  %s%s",
                (runtime.totalMemory() - runtime.freeMemory()),
                LS
        );
    }
}
