package ru.job4j.services.inputoutput.chat;

import java.util.*;
import java.util.function.Consumer;

/**
 * ChatMain
 *
 * @author Vladislav Nechaev
 * @since 05.07.2019
 */
public class ChatMain {

    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static final String FINISH = "закончить";
    private Log log;
    private Actions actions;
    private boolean isNeedAnAnswer = true;
    private Map<String, String> hiByeAnswers;
    private List<String> randomAnswers;

    public ChatMain(Log log,
                    Map<String, String> hiByeAnswers,
                    List<String> randomAnswers,
                    Map<String, Consumer<ChatMain>> commands) {
        this.log = log;
        this.hiByeAnswers = hiByeAnswers;
        this.randomAnswers = randomAnswers;
        actions = new Actions(commands);
    }

    /**
     * chat
     */
    public void chat() {
        System.out.println("Please type something:");
        Scanner scanner = new Scanner(System.in);
        String str;
//        while (scanner.hasNextLine() && !(str = scanner.nextLine()).equals(FINISH)) {
        while (scanner.hasNextLine()) {
            str = scanner.nextLine();
            if (str.equalsIgnoreCase(FINISH)) {
                break;
            }
            log.writeToLog(str);
            str = str.toLowerCase();
            Map<String, Consumer<ChatMain>> dispather = actions.getDispather();
            for (String command : dispather.keySet()) {
                if (command.equals(str)) {
                    dispather.get(command).accept(this);
                    break;
                }
            }
            if (!isNeedAnAnswer) {
                continue;
            }
            if (hiByeAnswers.containsKey(str)) {
                answer(hiByeAnswers.get(str));
            } else {
                answer(randomAnswers.get(new Random().nextInt(randomAnswers.size())));
            }
        }
        answer("Program is completed");
    }

    /**
     * answer
     *
     * @param answer - the text for the answer.
     */
    private void answer(String answer) {
        log.writeToLog(answer);
        System.out.println(answer);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid arguments.");
        }
        Args argums = new Args(args);
        ChatConfig conf = new ChatConfig(argums.getAnswersPath());
        Map<String, Consumer<ChatMain>> commands = new HashMap<>();
        commands.put(STOP, cm -> cm.isNeedAnAnswer = false);
        commands.put(CONTINUE, cm -> cm.isNeedAnAnswer = true);
        ChatMain chat = new ChatMain(new Log(argums.getLogPath()),
                conf.getHiByeAnswers(),
                conf.getRandomAnswers(),
                commands);
        chat.chat();
        System.out.println();
    }
}


