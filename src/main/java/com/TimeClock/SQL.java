package com.TimeClock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * SQL.
 * @author Rocky Robson - A00914509
 * @version Oct 13, 2016
 */
public class SQL {

	private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/timeclock?autoReconnect=true&useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	private Connection connection;

	public void connect() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Unable to connect to database.");
			e.printStackTrace();
		}
	}

	public void insert(String name, String column, LocalDateTime value) {
		String command = ("INSERT INTO " + name + " (" + column + ") VALUES ('" + Timestamp.valueOf(value) + "')");

		try {
			connect();
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.executeUpdate(command);
			statement.close();
			connection.commit();
			connection.close();
			System.out.println("Datebase updated");
		} catch (Exception e) {
			System.out.println("ERROR: database failed to update");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public void insert(String name, String column, Double value) {
		String command = String.format("INSERT INTO %s (%s) VALUES (%f)", name, column, value);

		try {
			connect();
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.executeUpdate(command);
			statement.close();
			connection.commit();
			connection.close();
			System.out.println("Datebase updated");
		} catch (Exception e) {
			System.out.println("ERROR: database failed to update");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public void update(String name, int day, String column, LocalDateTime value) {
		String command = ("UPDATE  " + name
				+ " SET " + column + "='" + Timestamp.valueOf(value) + "'"
				+ " WHERE day='" + day + "';");

		try {
			connect();
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.executeUpdate(command);
			statement.close();
			connection.commit();
			connection.close();
			System.out.println("Datebase updated");
		} catch (Exception e) {
			System.out.println("ERROR: database failed to update");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public void update(String name, int day, String column, String value) {
		String command = "UPDATE  " + name
				+ " SET " + column + "='" + value + "'"
				+ " WHERE day='" + day + "';";

		try {
			connect();
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.executeUpdate(command);
			statement.close();
			connection.commit();
			connection.close();
			System.out.println("Datebase updated");
		} catch (Exception e) {
			System.out.println("ERROR: database failed to update");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public Timestamp select(String name, String column) {
		String command = String.format("SELECT %s FROM %s", column, name);
		ResultSet rs = null;
		Timestamp ts = null;

		try {
			connect();
			connection.setAutoCommit(false);
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(command);
			while (rs.next())
				ts = rs.getTimestamp(column);
			connection.commit();
			connection.close();
			System.out.println("Data retrieved");
		} catch (Exception e) {
			System.out.println("ERROR: failed to retrieve data");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return ts;
	}
}
