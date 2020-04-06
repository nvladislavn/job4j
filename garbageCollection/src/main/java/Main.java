/**
 * Main
 *
 * @author Vladislav Nechaev
 * @since 06.04.2020
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().totalMemory());
        System.out.println(Runtime.getRuntime().freeMemory());
        int nameNumber = 0;
        for (int i = 0; i < 10000; i++) {
            new User(i + 1, "name" + nameNumber);
            nameNumber = nameNumber < 9 ? ++nameNumber : 0;
            if (i % 100 == 0) {
                runtimeInfo();
            }
        }
    }

    private static void runtimeInfo() {
        Runtime runtime = Runtime.getRuntime();
        System.err.printf(
                "Available free memory  %s%s",
                (runtime.freeMemory()),
                System.lineSeparator()
        );
        System.err.printf(
                "Used memory  %s%s",
                (runtime.totalMemory() - runtime.freeMemory()),
                System.lineSeparator()
        );
    }
}
