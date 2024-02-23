package SALPD7;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	private static final Map<String, Integer> WEEK = new HashMap<>();

    static {
        WEEK.put("lunes", 1);
        WEEK.put("martes", 2);
        WEEK.put("miércoles", 3);
        WEEK.put("jueves", 4);
        WEEK.put("viernes", 5);
    }
    
    private static boolean validateDayWeek(String dayWeek) {
        return WEEK.containsKey(dayWeek);
	}
	
	private static boolean validateHour(int hour) {
		return hour >= 0 && hour <= 23;
	}
	
	private static boolean validateMinute(int minute) {
		return minute >= 0 && minute <= 59;
	}
	
	private static int getDayWeek(String dayWeek) {
		return WEEK.getOrDefault(dayWeek, 0);
	}
	
	private static int getMinutesUntilTheWeekend(String dayWeek, int hour, int minute) {
		int minutesUntilTheWeekend = 0;
		if(dayWeek.equals("viernes") && hour < 15) {
			minutesUntilTheWeekend = (15 - hour - 1) * 60 + (60 - minute);
		} else {
			int currentDate = getDayWeek(dayWeek);
			minutesUntilTheWeekend = (4 - currentDate) * 24 * 60 + (15 - hour - 1) * 60 + (60 - minute);
		}
		
		return minutesUntilTheWeekend;
	}

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String dayWeek = "";
        int hour = 0;
        int minute = 0;
        
        do {
            System.out.print("Ingresa un día de la semana (lunes a viernes): ");
            dayWeek = scanner.nextLine().toLowerCase();
        } while(!validateDayWeek(dayWeek));
        	
    	do {
            System.out.print("Ingresa la hora (0-23): ");
            hour = scanner.nextInt();
    	} while(!validateHour(hour));
        	
    	do {
            System.out.print("Ingresa los minutos (0-59): ");
            minute = scanner.nextInt();
    	} while(!validateMinute(minute));
        	
    	int minutesUntilTheWeekend = getMinutesUntilTheWeekend(dayWeek, hour, minute);
    	System.out.println("Faltan " + minutesUntilTheWeekend + " minutos para el fin de semana.");
    	
    	scanner.close();
	}
}
