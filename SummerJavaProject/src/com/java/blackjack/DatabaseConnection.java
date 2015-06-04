package com.java.blackjack;
import java.sql.*;
import javax.swing.*;

public class DatabaseConnection {
	public Connection connection = null;
	
	public DatabaseConnection(){
	}
 
	public Connection getDatabaseConnection(String dbName)
			throws ClassNotFoundException {
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		// Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbName
					+ ".db");

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return connection;
	}
 
	public ResultSet runSql(String sql, Connection cn) throws SQLException {
		Statement sta = cn.createStatement();
		return sta.executeQuery(sql);
	}

	/*
	 * Driver program to check connection and run the sql query
	 */
	public static void main(String[] args) throws SQLException,ClassNotFoundException {
		Connection cn = null;
		try {

			DatabaseConnection dc = new DatabaseConnection();
			cn = dc.getDatabaseConnection("Casino");
			ResultSet rs = dc.runSql("Select * from User", cn);
			while (rs.next()) {
				// read the result set
				System.out.println("name = " + rs.getString("Name"));
				System.out.println("id = " + rs.getInt("id"));
				System.out.println("Money = " + rs.getInt("Money"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			if (cn != null)
				cn.close();
		}

	}
}
