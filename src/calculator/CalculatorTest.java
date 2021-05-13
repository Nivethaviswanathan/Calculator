package calculator;

import org.junit.*;
import static org.junit.Assert.*;

public class CalculatorTest {
	
	@Test
	public void returnZeroOnEmptyString()
	{
		assertEquals(0,Calculator.add(""));
	}
	
	@Test
	public void returnNumberOnNumber()
	{
		assertEquals(1,Calculator.add("1"));
	}
	
	@Test
	public void returnSumofTwoNumDelimitedByComma()
	{
		assertEquals(3,Calculator.add("1,2"));
	}
	
	@Test
	public void returnSumOfMultipleValues()
	{
		assertEquals(6,Calculator.add("1,2,3"));
	}
	
	@Test
	public void acceptNewlineAsValidDelimiter()
	{
		assertEquals(6,Calculator.add("1,2\n3"));
	}
	
	@Test
	public void acceptCustomDelimiterAsValidDelimiter()
	{
		assertEquals(3,Calculator.add("//;\n1;2"));
	}
	
	@Test
	public void acceptCustomDelimiterAlsoRegExprSpecialChar()
	{
		assertEquals(3,Calculator.add("//.\n1.2"));
	}
	
	@Test
	public void returnExceptionOnNegatives()
	{
		try {
			Calculator.add("-1,2,3");
			fail("Exception expected");
		}
		catch(RuntimeException ex)
		{
			
		}
	}
	
	@Test
	public void returnNegativeValues()
	{
		try {
			Calculator.add("-1,-2,3");
			fail("Exception expected");
		}
		catch(RuntimeException ex)
		{
			assertEquals("Negative values not allowed: -1, -2",ex.getMessage());
		}
		
	}
	

	
	


}
