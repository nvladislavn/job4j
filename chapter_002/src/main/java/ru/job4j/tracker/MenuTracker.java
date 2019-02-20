package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
    List<UserAction> actions = new ArrayList<>();
    private final Consumer<String> output;


    public MenuTracker(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * This method fills an actions array.
     */
    public void fillActions() {
        this.actions.add(this.new AddItem(ADD, "AddItem"));
        this.actions.add(new MenuTracker.ShowAll(SHOW_ALL, "ShowAll"));
        this.actions.add(this.new EditItem(EDIT, "EditItem"));
        this.actions.add(this.new DeleteItem(DELETE, "DeleteItem"));
        this.actions.add(this.new FindById(FIND_BY_ID, "FindById"));
        this.actions.add(new FindByName(FIND_BY_NAME, "FindByName"));
    }

    public int getActionLength() {
        return this.actions.size();
    }

    /**
     * This method calls execute method the given action.
     *
     * @param key - the number of the menu item.
     */
    public void select(int key) {
        this.actions.get(key).execute(input, tracker);
    }

    /**
     * This method shows the menu.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                MenuTracker.this.output.accept(action.info());
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
            MenuTracker.this.output.accept(("---------------Adding item---------------"));
            String name = input.ask("Please enter the name of your application:");
            String desc = input.ask("Please enter the description of your application:");
            Item item = new Item(name, desc);
            tracker.add(item);
            MenuTracker.this.output.accept("Created a new application " + item.toString() + System.lineSeparator());
        }
    }

    /**
     * ShowAll.
     */
    public class ShowAll extends BaseAction {

        public ShowAll(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            List<Item> list = tracker.findAll();
            if (list.isEmpty()) {
                MenuTracker.this.output.accept("No application has been created yet." + System.lineSeparator());
            } else {
                MenuTracker.this.output.accept("---------------List of all applications---------------");
                for (Item item : list) {
                    MenuTracker.this.output.accept(item.toString());
                }
                MenuTracker.this.output.accept(System.lineSeparator());
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
            MenuTracker.this.output.accept("---------------Editing item---------------");
            String id = input.ask("Please enter the id of the application you want to edit:");
            String newName = input.ask("Please enter a new name: ");
            String newDesc = input.ask("Please enter a new description: ");
            Item newItem = new Item(newName, newDesc);
            newItem.setId(id);
            if (tracker.replace(id, newItem)) {
                MenuTracker.this.output.accept("Application editing is complete." + System.lineSeparator());
            } else {
                MenuTracker.this.output.accept("Application not found.");
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
            MenuTracker.this.output.accept("---------------Deleting item---------------");
            String id = input.ask("Please enter the id of the application you want to delete:");
            if (tracker.delete(id)) {
                MenuTracker.this.output.accept("Application deleted." + System.lineSeparator());
            } else {
                MenuTracker.this.output.accept("Application not found.");
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
            MenuTracker.this.output.accept("---------------Search application by id---------------");
            String id = input.ask("Please enter the id of the application you want to found:");
            Item item = tracker.findById(id);
            if (item == null) {
                MenuTracker.this.output.accept("An application with the given id was not found!");
            } else {
                MenuTracker.this.output.accept("Was found the application: " + item.toString() + System.lineSeparator());
            }
        }
    }

    /**
     * FindByName.
     */
    public class FindByName extends BaseAction {

        public FindByName(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            MenuTracker.this.output.accept("---------------Search application by name---------------");
            String name = input.ask("Please enter the name of the application you want to found:");
            List<Item> list = tracker.findByName(name);
            if (list.isEmpty()) {
                MenuTracker.this.output.accept("No applications with the given name were found!");
            } else {
                MenuTracker.this.output.accept("The applications were found: ");
                for (Item item : list) {
                    MenuTracker.this.output.accept(item.toString());
                }
                MenuTracker.this.output.accept(System.lineSeparator());
            }
        }
    }
}
