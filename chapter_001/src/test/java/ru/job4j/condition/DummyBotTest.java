package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DummyBotTest {

    private final String sayHello = "Привет, Бот.";
    private final String sayBy = "Пока.";
    private final String answerHello = "Привет, умник.";
    private final String answerBy = "До скорой встречи.";
    private final String answerOther = "Это ставит меня в тупик. Задайте другой вопрос.";

    /**
     * Test answer with "Привет, Бот."
     */
    @Test
    public void whenHelloBotThenHello() {
        DummyBot bot = new DummyBot();
        assertThat(bot.answer(sayHello), is(answerHello));
    }

    /**
     * Test answer with "Пока."
     */
    @Test
    public void whenByThenBy() {
        DummyBot bot = new DummyBot();
        assertThat(bot.answer(sayBy), is(answerBy));
    }

    /**
     * Test answer with "Другое"
     */
    @Test
    public void whenOther() {
        DummyBot bot = new DummyBot();
        assertThat(bot.answer("Other"), is(answerOther));
    }
}