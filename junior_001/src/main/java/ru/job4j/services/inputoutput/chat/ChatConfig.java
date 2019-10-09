package ru.job4j.services.inputoutput.chat;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ChatConfig
 *
 * @author Vladislav Nechaev
 * @since 05.07.2019
 */
public class ChatConfig {

    private String answersPath;
    private Map<String, String> hiByeAnswers = new HashMap<>();
    private List<String> randomAnswers = new ArrayList<>();

    public ChatConfig(String answersPath) {
        this.answersPath = answersPath;
        loadAnswers();
    }

    /**
     * +
     * loadAnswers
     * <p>
     * fills answers from a text file.
     */
    private void loadAnswers() {
        String str = null;
        try (BufferedReader in = new BufferedReader(new FileReader(answersPath))) {
            while ((str = in.readLine()) != null) {
                if (str.contains("=")) {
                    String[] kv = str.split("=");
                    hiByeAnswers.put(kv[0].trim(), kv[1].trim());
                } else if (!str.isEmpty()) {
                    randomAnswers.add(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getHiByeAnswers() {
        return hiByeAnswers;
    }

    public List<String> getRandomAnswers() {
        return randomAnswers;
    }
}
