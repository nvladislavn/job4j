package ru.job4j.converter;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test
 * @author Vladislav Nechaev
 * @version &Id$
 * @since 14/10/2018
 */
public class ConverterTest {

    /**
     * Test rubleToDollar
     */
    @Test
    public void when60RubleToDollarThen1() {
        Converter conv = new Converter();
        assertThat(conv.rubleToDollar(60), is(1));
    }

    /**
     * Test rubleToEuro
     */
    @Test
    public void when70RubleToEuroThen1() {
        Converter conv = new Converter();
        assertThat(conv.rubleToEuro(70), is(1));
    }

    /**
     * Test dollarToRuble
     */
    @Test
    public void when1DollarToRubleThen60() {
        Converter conv = new Converter();
        assertThat(conv.dollarToRuble(1), is(60));
    }

    /**
     * Test euroToRuble
     */
    @Test
    public void when1EuroToRubleThen70() {
        Converter conv = new Converter();
        assertThat(conv.euroToRuble(1), is(70));
    }
}
