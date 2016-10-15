package com.TimeClock;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Clock.
 * @author Rocky Robson - A00914509
 * @version Oct 13, 2016
 */
public class Clock {

	private SQL SQL;
	private static final String start = "start_time";
	private static final String end = "end_time";
	private static final String duration ="duration";

	public Clock() {
		SQL = new SQL();
	}

	public void clockIn(Employee employee) {
		try {
			LocalDateTime time = LocalDateTime.now();
			SQL.insert(employee.getName(), start, time);
		} catch (Exception e) {
			System.out.println("ERROR: unable to clock in ");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public void clockOut(Employee employee) {
		try {
			LocalDateTime formalEndTime = LocalDateTime.now();
			SQL.update(employee.getName(), employee.getDay(), end, formalEndTime);
			
			LocalDateTime queriedStart = stampToTime(SQL.select(employee.getName(), start));
			
			String dur = getDuration(queriedStart, formalEndTime);
			SQL.update(employee.getName(), employee.getDay(), duration, dur);

		} catch (Exception e) {
			System.out.println("ERROR: unable to clock out ");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	private String getDuration(LocalDateTime start, LocalDateTime end) {
		Duration duration = Duration.between(start, end);
		return formatDuration(duration);
	}

	private String formatDuration(Duration duration) {
		long seconds = duration.getSeconds();
		long absSeconds = Math.abs(seconds);
		String positive = String.format(
				"%d:%02d:%02d",
				absSeconds / 3600,
				(absSeconds % 3600) / 60,
				absSeconds % 60);
		return seconds < 0 ? "-" + positive : positive;
	}
	
	private LocalDateTime stampToTime(Timestamp timestamp){
		return timestamp.toLocalDateTime();
	}

}
