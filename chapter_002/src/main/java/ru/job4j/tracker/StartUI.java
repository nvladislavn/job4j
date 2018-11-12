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
    private final ConsoleInput input;
    private final Tracker tracker;
    private boolean exit;

    /**
     * StartUI. Creating an instance of StartUI class.
     *
     * @param input   - an instance of ConsoleInput class.
     * @param tracker - an instance of Tracker class.
     */
    public StartUI(ConsoleInput input, Tracker tracker) {
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
            }
        }
    }

    /**
     * exitProgram. Shuts dawn the program.
     */
    private void exitProgram() {
        String answer = input.ask("Do you want to exit the program? (y/n)");
        if (answer.toLowerCase().equals("y")) {
            System.out.println("The program is completed.");
            exit = true;
        } else if (!answer.toLowerCase().equals("y") && !answer.toLowerCase().equals("n")) {
            exitProgram();
        }
    }

    /**
     * findByName. This method shows an application array with the given name.
     */
    private void findByName() {
        System.out.println("---------------Search application by name---------------");
        showAll();
        String name = input.ask("Please enter the name of the application you want to found:");
        Item[] array = tracker.findByName(name);
        if (array.length == 0) {
            System.out.println("No applications with the given name were found!");
            return;
        }
        System.out.println("The applications were found: ");
        for (Item item : array) {
            System.out.println(item.toString());
        }
        System.out.println("\n\n");
    }

    /**
     * findById. This method shows an application with the given id.
     */
    private void findById() {
        System.out.println("---------------Search application by id---------------");
        showAll();
        String id = input.ask("Please enter the id of the application you want to found:");
        Item item = tracker.findById(id);
        if (item == null) {
            System.out.println("An application with the given id was not found!");
            return;
        }
        System.out.println("Was found the application: " + item.toString() + "\n\n");
    }


    /**
     * deleteItem. This method deletes an application with the given id.
     */
    private void deleteItem() {
        System.out.println("---------------Deleting item---------------");
        showAll();
        String id = input.ask("Please enter the id of the application you want to find:");
        Item item = tracker.findById(id);
        System.out.println("Application deleted.\n\n");
    }

    /**
     * editItem. This method changes the application with the specified id to the new one.
     */
    private void editItem() {
        System.out.println("---------------Editing item---------------");
        showAll();
        String id = input.ask("Please enter the id of the application you want to edit:");
        Item item = tracker.findById(id);
        if (item == null) {
            System.out.println("An application with the given id was not found!");
            return;
        }
        System.out.println("Found the item: " + item.toString() + "\nPlease enter new requisite found item");
        String newName = input.ask("Please enter a new name: ");
        String newDesc = input.ask("Please enter a new description: ");
        Item newItem = new Item(newName, newDesc);
        newItem.setId(id);
        tracker.replace(id, newItem);
        System.out.println("Application editing is complete.\n\n");
    }

    /**
     * showAll. This method shows all applications.
     */
    private void showAll() {
        Item[] array = tracker.findAll();
        if (array.length == 0) {
            System.out.println("No application has been created yet.\n\n");
            return;
        }
        System.out.println("---------------List of all applications---------------");
        for (Item item : array) {
            System.out.println(item.toString());
        }
        System.out.println("\n");
    }

    /**
     * createItem. This method creates and saves a new application.
     */
    private void createItem() {
        System.out.println("---------------Adding item---------------");
        String name = input.ask("Please enter the name of your application:");
        String desc = input.ask("Please enter the description of your application:");
        Item item = new Item(name, desc);
        tracker.add(item);
        System.out.println("Created a new application " + item.toString() + "\n\n");
    }

    /**
     * showMenu. This method displays the main program menu.
     */
    private void showMenu() {
        System.out.println("-------------------\nProgram menu" + "\n-------------------");
        System.out.println("0. Add new item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find by id");
        System.out.println("5. Find by name");
        System.out.println("6. Exit the Program");
    }
}
