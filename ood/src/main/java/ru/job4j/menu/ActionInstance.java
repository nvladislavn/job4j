package ru.job4j.menu;

/**
 * ActionInstance
 *
 * @author Vladislav Nechaev
 * @since 08.07.2020
 */
public class ActionInstance implements Action {

    private static int postfix;
    private String name;

    public ActionInstance() {
        this.name = "Stub" + postfix++;
    }

    /**
     * Print the text "Execution" to the console.
     */
    @Override
    public void execute() {
        System.out.println("Execution");
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
