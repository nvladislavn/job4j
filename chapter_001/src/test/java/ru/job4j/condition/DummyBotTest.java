package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DummyBotTest {

    private final String SAY_HELLO = "Привет, Бот.";
    private final String SAY_BY = "Пока.";
    private final String ANSWER_HELLO = "Привет, умник.";
    private final String ANSWER_BY = "До скорой встречи.";
    private final String ANSWER_OTHER = "Это ставит меня в тупик. Задайте другой вопрос.";

    /**
     * Test answer with "Привет, Бот."
     */
    @Test
    public void WhenHelloBotThenHello() {
        DummyBot bot = new DummyBot();
        assertThat(bot.answer(SAY_HELLO), is(ANSWER_HELLO));
    }

    /**
     * Test answer with "Пока."
     */
    @Test
    public void WhenByThenBy() {
        DummyBot bot = new DummyBot();
        assertThat(bot.answer(SAY_BY), is(ANSWER_BY));
    }

    /**
     * Test answer with "Другое"
     */
    @Test
    public void WhenOther() {
        DummyBot bot = new DummyBot();
        assertThat(bot.answer("Other"), is(ANSWER_OTHER));
    }
}