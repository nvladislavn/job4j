package ru.job4j.calculator;

/**
*Calculator
*@author Vladislav Nechaev
*@since 13/10/2018
*@version 1.0
*/
public class Calculator {

	private double result;

	/**
	*summarizes the given numbers
	*@param first, second
	*/
	public void add(double first, double second) {
		this.result = first + second;
	}
	
	/**
	*subtracts the second number from the first number
	*@param  first, second
	*/
	public void subtract(double first, double second) {
		this.result = first - second;
	}
	
	/**
	*divide the first number by the second number
	*@param first, second
	*/
	public void div(double first, double second) {
		this.result = first / second;
	}
	
	/**
	*multiples the given numbers
	*@param first, second
	*/
	public void multiple(double first, double second) {
		this.result = first * second;
	}
	
	/**
	*returns the result of an arithmetic operations
	*/
	public double getResult() {
		return this.result;
	}
}