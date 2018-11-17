package ru.job4j.tracker;

/**
 * StartUI.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 11.11.2018
 */
public class StartUI {

    private static final String ADD = "0";
    private static final String SHOW_ALL = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FIND_BY_ID = "4";
    private static final String FIND_BY_NAME = "5";
    private static final String EXIT = "6";
    private final Input input;
    private final Tracker tracker;
    private boolean exit;

    /**
     * StartUI. Creating an instance of StartUI class.
     *
     * @param input   - an instance of ConsoleInput class.
     * @param tracker - an instance of Tracker class.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public static void main(String[] args) {
        StartUI startUI = new StartUI(new ConsoleInput(), new Tracker());
        startUI.init();
    }

    /**
     * init.
     */
    public void init() {
        while (!exit) {
            showMenu();
            String answer = input.ask("Пожалуйста, введите пункт меню и нажмите Enter:");
            switch (answer) {
                case ADD:
                    createItem();
                    break;
                case SHOW_ALL:
                    showAll();
                    break;
                case EDIT:
                    editItem();
                    break;
                case DELETE:
                    deleteItem();
                    break;
                case FIND_BY_ID:
                    findById();
                    break;
                case FIND_BY_NAME:
                    findByName();
                    break;
                case EXIT:
                    exitProgram();
                    break;
                default:
            }
        }
    }

    /**
     * exitProgram. Shuts dawn the program.
     */
    private void exitProgram() {
        String answer = input.ask("Do you want to exit the program? (y/n)");
        if (answer.toLowerCase().equals("y")) {
            System.out.print("The program is completed." + System.lineSeparator());
            exit = true;
        } else if (!answer.toLowerCase().equals("y") && !answer.toLowerCase().equals("n")) {
            exitProgram();
        }
    }

    /**
     * findByName. This method shows an application array with the given name.
     */
    private void findByName() {
        System.out.print("---------------Search application by name---------------" + System.lineSeparator());
        String name = input.ask("Please enter the name of the application you want to found:");
        Item[] array = tracker.findByName(name);
        if (array.length == 0) {
            System.out.print("No applications with the given name were found!" + System.lineSeparator());
        } else {
            System.out.print("The applications were found: " + System.lineSeparator());
            for (Item item : array) {
                System.out.print(item.toString() + System.lineSeparator());
            }
            System.out.print(System.lineSeparator());
        }
    }

    /**
     * findById. This method shows an application with the given id.
     */
    private void findById() {
        System.out.print("---------------Search application by id---------------" + System.lineSeparator());
        String id = input.ask("Please enter the id of the application you want to found:");
        Item item = tracker.findById(id);
        if (item == null) {
            System.out.print("An application with the given id was not found!" + System.lineSeparator());
        } else {
            System.out.print("Was found the application: " + item.toString() + System.lineSeparator() + System.lineSeparator());
        }
    }


    /**
     * deleteItem. This method deletes an application with the given id.
     */
    private void deleteItem() {
        System.out.print("---------------Deleting item---------------" + System.lineSeparator());
        String id = input.ask("Please enter the id of the application you want to delete:");
        if (tracker.delete(id)) {
            System.out.print("Application deleted." + System.lineSeparator() + System.lineSeparator());
        } else {
            System.out.print("Application not found." + System.lineSeparator());
        }
    }

    /**
     * editItem. This method changes the application with the specified id to the new one.
     */
    private void editItem() {
        System.out.print("---------------Editing item---------------" + System.lineSeparator());
        String id = input.ask("Please enter the id of the application you want to edit:");
        String newName = input.ask("Please enter a new name: ");
        String newDesc = input.ask("Please enter a new description: ");
        Item newItem = new Item(newName, newDesc);
        newItem.setId(id);
        if (tracker.replace(id, newItem)) {
            System.out.print("Application editing is complete." + System.lineSeparator() + System.lineSeparator());
        } else {
            System.out.print("Application not found." + System.lineSeparator());
        }
    }

    /**
     * showAll. This method shows all applications.
     */
    private void showAll() {
        Item[] array = tracker.findAll();
        if (array.length == 0) {
            System.out.print("No application has been created yet." + System.lineSeparator() + System.lineSeparator());
        } else {
            System.out.print("---------------List of all applications---------------" + System.lineSeparator());
            for (Item item : array) {
                System.out.print(item.toString() + System.lineSeparator());
            }
            System.out.print(System.lineSeparator());
        }
    }

    /**
     * createItem. This method creates and saves a new application.
     */
    private void createItem() {
        System.out.print("---------------Adding item---------------" + System.lineSeparator());
        String name = input.ask("Please enter the name of your application:");
        String desc = input.ask("Please enter the description of your application:");
        Item item = new Item(name, desc);
        tracker.add(item);
        System.out.print("Created a new application " + item.toString() + System.lineSeparator() + System.lineSeparator());
    }

    /**
     * showMenu. This method displays the main program menu.
     */
    private void showMenu() {
        System.out.print("-------------------" + System.lineSeparator() + "Program menu"
                + System.lineSeparator() + "-------------------" + System.lineSeparator());
        System.out.print("0. Add new item" + System.lineSeparator());
        System.out.print("1. Show all items" + System.lineSeparator());
        System.out.print("2. Edit item" + System.lineSeparator());
        System.out.print("3. Delete item" + System.lineSeparator());
        System.out.print("4. Find by id" + System.lineSeparator());
        System.out.print("5. Find by name" + System.lineSeparator());
        System.out.print("6. Exit the Program" + System.lineSeparator());
    }
}
