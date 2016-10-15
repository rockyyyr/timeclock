package com.TimeClock;

public class Employee {

	private String name;
	private boolean clockedIn;
	private int day;
	
	private Clock clock;
	
	public Employee(String name) {
		setName(name);
		clock = new Clock();
	}

	public String getName() {
		return this.name;
	}
	
	public boolean isClockedIn() {
		return this.clockedIn;
	}
	
	public int getDay(){
		return this.day;
	}

	public void setName(String name) {
		if (name != null && name.trim().length() > 0)
			this.name = name.trim().toLowerCase();
	}
	
	public void clockIn() {
		clockedIn = true;
		day++;
		clock.clockIn(this);
	}

	public void clockOut() {
		clockedIn = false;
		clock.clockOut(this);
	}

}
