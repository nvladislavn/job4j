package ru.job4j.services.inputoutput.chat;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Actions
 *
 * @author Vladislav Nechaev
 * @since 12.07.2019
 */
public class Actions {

    private Map<String, Consumer<ChatMain>> dispather = new HashMap<>();

    public Actions(Map<String, Consumer<ChatMain>> dispather) {
        this.dispather.putAll(dispather);
    }

    /**
     * load
     * loads incoming map.
     *
     * @param message - incoming message.
     * @param handler - message handler.
     * @return - the Map<String, Consumer<ChatMain>>.
     */
    public Map<String, Consumer<ChatMain>> load(String message, Consumer<ChatMain> handler) {
        dispather.put(message, handler);
        return dispather;
    }

    public Map<String, Consumer<ChatMain>> getDispather() {
        return dispather;
    }
}
