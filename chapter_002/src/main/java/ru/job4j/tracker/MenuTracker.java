package ru.job4j.tracker;

/**
 * MenuTracker.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 19.11.2018
 */
public class MenuTracker {

    private static final String ADD = "0";
    private static final String SHOW_ALL = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FIND_BY_ID = "4";
    private static final String FIND_BY_NAME = "5";
    private Input input;
    private Tracker tracker;
    UserAction[] actions = new UserAction[6];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * This method fills an actions array.
     */
    public void fillActions() {
        this.actions[0] = this.new AddItem(ADD, "AddItem");
        this.actions[1] = new MenuTracker.ShowAll(SHOW_ALL, "ShowAll");
        this.actions[2] = this.new EditItem(EDIT, "EditItem");
        this.actions[3] = this.new DeleteItem(DELETE, "DeleteItem");
        this.actions[4] = this.new FindById(FIND_BY_ID, "FindById");
        this.actions[5] = new FindByName(FIND_BY_NAME, "FindByName");
    }

    public int getActionLength() {
        return this.actions.length;
    }

    /**
     * This method calls execute method the given action.
     *
     * @param key - the number of the menu item.
     */
    public void select(int key) {
        this.actions[key].execute(input, tracker);
    }

    /**
     * This method shows the menu.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * AddItem.
     */
    public class AddItem extends BaseAction {

        public AddItem(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("---------------Adding item---------------");
            String name = input.ask("Please enter the name of your application:");
            String desc = input.ask("Please enter the description of your application:");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("Created a new application " + item.toString() + System.lineSeparator());
        }
    }

    /**
     * ShowAll.
     */
    public static class ShowAll extends BaseAction {

        public ShowAll(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            Item[] array = tracker.findAll();
            if (array.length == 0) {
                System.out.println("No application has been created yet." + System.lineSeparator());
            } else {
                System.out.println("---------------List of all applications---------------");
                for (Item item : array) {
                    System.out.println(item.toString());
                }
                System.out.println();
            }
        }
    }

    /**
     * EditItem.
     */
    public class EditItem extends BaseAction {

        public EditItem(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("---------------Editing item---------------");
            String id = input.ask("Please enter the id of the application you want to edit:");
            String newName = input.ask("Please enter a new name: ");
            String newDesc = input.ask("Please enter a new description: ");
            Item newItem = new Item(newName, newDesc);
            newItem.setId(id);
            if (tracker.replace(id, newItem)) {
                System.out.println("Application editing is complete." + System.lineSeparator());
            } else {
                System.out.println("Application not found.");
            }
        }
    }

    /**
     * DeleteItem.
     */
    public class DeleteItem extends BaseAction {

        public DeleteItem(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("---------------Deleting item---------------");
            String id = input.ask("Please enter the id of the application you want to delete:");
            if (tracker.delete(id)) {
                System.out.println("Application deleted." + System.lineSeparator());
            } else {
                System.out.println("Application not found.");
            }
        }
    }

    /**
     * FindById.
     */
    public class FindById extends BaseAction {

        public FindById(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("---------------Search application by id---------------");
            String id = input.ask("Please enter the id of the application you want to found:");
            Item item = tracker.findById(id);
            if (item == null) {
                System.out.println("An application with the given id was not found!");
            } else {
                System.out.println("Was found the application: " + item.toString() + System.lineSeparator());
            }
        }
    }
}

/**
 * FindByName.
 */
class FindByName extends BaseAction {

    public FindByName(String key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("---------------Search application by name---------------");
        String name = input.ask("Please enter the name of the application you want to found:");
        Item[] array = tracker.findByName(name);
        if (array.length == 0) {
            System.out.println("No applications with the given name were found!");
        } else {
            System.out.println("The applications were found: ");
            for (Item item : array) {
                System.out.println(item.toString());
            }
            System.out.println();
        }
    }
}

