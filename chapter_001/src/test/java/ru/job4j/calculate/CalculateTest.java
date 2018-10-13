package ru.job4j.calculate;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
*Test
*@author Vladislav Nechaev
*@version $Id$
*@since 1.0
*/
public class CalculateTest {

	/**
	*Test echo
	*/
	@Test
	public void whenTakeNameThenThreeEchoPlusName() {

		String input = "Legolas";
		String expect = "Echo, echo, echo: Legolas";

		Calculate  calc = new Calculate();

		String result = calc.echo(input);

		assertThat(result, is(expect));
	}
}