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
        this.actions[0] = this.new AddItem();
        this.actions[1] = new MenuTracker.ShowAll();
        this.actions[2] = this.new EditItem();
        this.actions[3] = this.new DeleteItem();
        this.actions[4] = this.new FindById();
        this.actions[5] = new FindByName();
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
        this.actions[key - 1].execute(input, tracker);
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
    public class AddItem implements UserAction {

        @Override
        public String key() {
            return ADD;
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

        @Override
        public String info() {
            return "1. Add a new application.";
        }
    }

    /**
     * ShowAll.
     */
    public static class ShowAll implements UserAction {

        @Override
        public String key() {
            return SHOW_ALL;
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

        @Override
        public String info() {
            return "2. Display all items.";
        }
    }

    /**
     * EditItem.
     */
    public class EditItem implements UserAction {
        @Override
        public String key() {
            return EDIT;
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

        @Override
        public String info() {
            return "3. Change the application with the specified id to the new one";
        }
    }

    /**
     * DeleteItem.
     */
    public class DeleteItem implements UserAction {
        @Override
        public String key() {
            return DELETE;
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

        @Override
        public String info() {
            return "4. Delete an application.";
        }
    }

    /**
     * FindById.
     */
    public class FindById implements UserAction {

        @Override
        public String key() {
            return FIND_BY_ID;
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

        @Override
        public String info() {
            return "5. Show an application with the given id.";
        }
    }
}

/**
 * FindByName.
 */
class FindByName implements UserAction {
    @Override
    public String key() {
        return "5";
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

    @Override
    public String info() {
        return "6. Show an application array with the given name.";
    }
}

