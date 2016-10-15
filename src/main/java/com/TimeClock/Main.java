package com.TimeClock;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		Scanner reader = new Scanner(System.in);
		Employee rocky = new Employee("rocky");
//		
//		SQL sql = new SQL();
//		
//		LocalDateTime time = LocalDateTime.now();
//		sql.insert("rocky", "start_time", time);
//		
//		System.out.println(time);
		
		
		
		boolean proceed = true;
		while(proceed){
			System.out.println("welcome\n"
					+ "1 - clock in\n"
					+ "2 - clock out\n"
					+ "3 - exit");
			
			String command = reader.nextLine();
			
			switch(command){
			case "1":
				rocky.clockIn();
				break;
			case "2":
				rocky.clockOut();
				break;
			case "3":
				proceed = false;
				System.out.println("Goodbye");
				break;
			}
		}
		
		
		
	}

}
