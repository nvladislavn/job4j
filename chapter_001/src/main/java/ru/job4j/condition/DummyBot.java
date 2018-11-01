package ru.job4j.condition;

/**
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 15/10/2018
 */
public class DummyBot {

    private final String sayHello = "Привет, Бот.";
    private final String sayBy = "Пока.";
    private final String answerHello = "Привет, умник.";
    private final String answerBy = "До скорой встречи.";
    private final String answerOther = "Это ставит меня в тупик. Задайте другой вопрос.";

    /**
     * Return the bot's answer to the client's question
     * @param question
     * @return answer
     */
    public String answer(String question) {
        String answer = answerOther;
        if (question.equals(sayHello)) {
            answer = answerHello;
        } else if (question.equals(sayBy)) {
            answer = answerBy;
        }
        return answer;
    }
}
