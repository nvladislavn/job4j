package ru.job4j.condition;

/**
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 15/10/2018
 */
public class DummyBot {

    private final String SAY_HELLO = "Привет, Бот.";
    private final String SAY_BY = "Пока.";
    private final String ANSWER_HELLO = "Привет, умник.";
    private final String ANSWER_BY = "До скорой встречи.";
    private final String ANSWER_OTHER = "Это ставит меня в тупик. Задайте другой вопрос.";

    /**
     * Return the bot's answer to the client's question
     * @param question
     * @return answer
     */
    public String answer(String question) {
        String answer = ANSWER_OTHER;
        if (question.equals(SAY_HELLO)) {
            answer = ANSWER_HELLO;
        } else if (question.equals(SAY_BY)) {
            answer = ANSWER_BY;
        }
        return answer;
    }
}
