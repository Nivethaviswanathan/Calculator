package calculator;

//import ch.lambdaj.function.convert.Convertor;
import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.lambdaj.function.convert.Converter;

public class Calculator {

	public static int add(String number)
	{
		
			String[] numbers=tokenize(number);
			List<Integer> numList=convert(numbers, toInt());
			ensureNonNegatives(numList);
			return sum(numList).intValue();
	}
	
	private static void ensureNonNegatives(List<Integer> numList) throws RuntimeException{
		List<Integer> negative=filter(lessThan(0), numList);
		if(negative.size()>0)
		{
			throw new RuntimeException("Negative values not allowed: "+join(negative));
		}
	}
	
	private static String[] tokenize(String number)
	{
		if(number.isEmpty())
		{
			return new String[0];
		}
		else if(number.startsWith("//"))
		{
			return splitWithCustomDelimiter(number);
		}
		else
		{
			return splitWithNewLine(number);
		}
	}
	
	private static String[] splitWithNewLine(String number)
	{
		String[] numbers=number.split(",|\n");
		return numbers;
	}
	
	private static String[] splitWithCustomDelimiter(String number)
	{
		Matcher m=Pattern.compile("//(.)\n(.*)").matcher(number);
		m.matches();
		String customDelimiter=m.group(1);
		String numberMatch=m.group(2);
		return numberMatch.split(Pattern.quote(customDelimiter));
	}
	
	private static Converter<String,Integer> toInt()
	{
		return new Converter<String,Integer>()
				{
					public Integer convert(String from)
					{
						return toInt(from);
					}
				};
	}
	
	private static int toInt(String number) throws NumberFormatException
	{
		return Integer.parseInt(number);
	}
	

}
