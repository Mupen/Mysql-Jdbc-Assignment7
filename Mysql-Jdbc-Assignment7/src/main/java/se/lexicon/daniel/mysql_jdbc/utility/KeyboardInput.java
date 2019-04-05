package se.lexicon.daniel.mysql_jdbc.utility;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class KeyboardInput {
	// Scanner function and Random function.
	static Scanner keyboard = new Scanner(System.in);
	
	
    public static String getString(String inputString) {
        System.out.print(inputString);
        return keyboard.nextLine();
    }
    
	public static LocalDate getLocalDate(String inputString) {
		System.out.print(inputString);
		LocalDate newDateInput = LocalDate.parse(keyboard.nextLine());
        return newDateInput;
	}
	
	public static int getInt() {
		try {
			System.out.print(" Enter a number... ");
			String input = keyboard.nextLine();
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {System.out.println("Not a valid number");
			return getInt();
		}
	}
	
    public static int StringToInt(String inputString) {
		if(inputString.equals(null) || inputString.equals("") || !inputString.matches("[0-9]+")) {
			System.out.println("Not a valid input...");
			return StringToInt(KeyboardInput.getString("Please enter a valid input: "));
		}
		else {
			int intNumber = Integer.parseInt(inputString);
			return intNumber;
		}
    }
    
	public static String intArrayToString(int[] denominationValues) {
		return null;
	}
	
	//Utility function nullCheck
	public static boolean nullCheck(Object... objects) {
		for (Object obj : objects) {
			if (obj == null) {
				return true;
			}
		}
		return false;
	}
	
    //Utility function distinctByKey
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
	    Map<Object, Boolean> map = new ConcurrentHashMap<>();
	    return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	
}
