package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
*Test 
*@author Vladislav Nechaev
*@version $Id$
*@since 13/10/2018
*/
public class CalculatorTest {

	/**
	*Test add method
	*/
	@Test
	public void whenAddOnePlusOneThenTwo() {
		Calculator calc = new Calculator();
		calc.add(1D, 1D);
		double result = calc.getResult();
		double expected = 2D;
		assertThat(result, is(expected));
	}
	
	/**
	*Test subtract method
	*/
	@Test
	public void whenThreeMinusTwoThenOne() {
		Calculator calc = new Calculator();
		calc.subtract(3D, 2D);
		double result = calc.getResult();
		double expected = 1D;
		assertThat(result, is(expected));
	}
	
	/**
	*Test multiple method
	*/
	@Test
	public void whenTwoMultipleThreeThenSix() {
		Calculator calc = new Calculator();
		calc.multiple(2D, 3D);
		double result = calc.getResult();
		double expected = 6D;
		assertThat(result, is(expected));
	}
	
	/**
	*Test divide method
	*/
	@Test
	public void whenSixDividedByThreeThenTwo() {
		Calculator calc = new Calculator();
		calc.div(6D, 3D);
		double result = calc.getResult();
		double expected = 2D;
		assertThat(result, is(expected));
	}
}

