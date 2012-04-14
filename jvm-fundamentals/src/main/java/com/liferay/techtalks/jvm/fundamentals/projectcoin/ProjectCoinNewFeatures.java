package com.liferay.techtalks.jvm.fundamentals.projectcoin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class shows some of the new features included in Project Coin
 * 
 * @author migue
 * 
 */
public class ProjectCoinNewFeatures {

	/**
	 * This method shows how to use the new <code>diamon syntax</code> included
	 * in the Coin project
	 * 
	 * @return
	 */
	protected static <T> Map<Integer, List<T>> diamondSyntax() {
		Map<Integer, List<T>> result = new HashMap<>();

		return result;
	}

	/**
	 * Catching multiple exceptions in the same <code>catch clause</code>
	 * 
	 * @param str
	 * @param methodName
	 */
	protected static void improvedExceptionHandling(String str,
			String methodName) {

		Method m = null;

		try {
			str.getClass().getMethod(methodName);
			m.invoke(str);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			System.err.println("Error while executing method " + methodName);
		}

	}

	/**
	 * Using strings as <code>switch</code> selector
	 * 
	 * @param dayOfTheWeek
	 */
	protected static void stringsAsSwitchSelector(String dayOfTheWeek) {

		switch (dayOfTheWeek) {
		case "Monday":
		case "Tuesday":
		case "Wednesday":
		case "Thursday":
		case "Friday":
			System.out.println("Working day!!");
			break;
		case "Saturday":
		case "Sunday":
			System.out.println("Rest day!!");
			break;
		default:
			System.err.println(dayOfTheWeek + " is not a valid name for a day");
			break;
		}
	}

	/**
	 * TWR. New way of managing resources in JDK7
	 * 
	 * @param input
	 * @param fileName
	 */
	protected static void tryWithResource(String fileName) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			System.out.println(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		BufferedReader consoleInputStream = new BufferedReader(
				new InputStreamReader(System.in));

		System.out.println("Introduce a day of the week");

		String line = consoleInputStream.readLine();

		ProjectCoinNewFeatures.stringsAsSwitchSelector(line);

		System.out.println("Introduce a file path: ");
		line = consoleInputStream.readLine();

		ProjectCoinNewFeatures.tryWithResource(line);

	}
}
