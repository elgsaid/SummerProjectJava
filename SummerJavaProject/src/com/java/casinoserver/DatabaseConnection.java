package com.java.casinoserver;
import java.sql.*;

public class DatabaseConnection {
	public Connection sqliteConn = null;
	
	public DatabaseConnection(){
	}
 
	public Connection getDatabaseConnection(String dbName) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		// Connection connection = null;
		try {
			// create a database connection
			sqliteConn = DriverManager.getConnection("jdbc:sqlite:" + dbName+ ".db");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return sqliteConn;
	}
 
	public ResultSet runSelectSql(String sql, Connection cn) throws SQLException {
		System.out.println("In Select Statement...."+sql);
		Statement sta = cn.createStatement();
		return sta.executeQuery(sql);
	}
	
	public void runUpdateSql(String sql, Connection cn) throws SQLException {
		
		 System.out.println("In DB update function .. "+sql);
		 Statement sta = cn.createStatement();
		 sta.executeUpdate(sql);
	}

	/*
	 * Driver program to check connection and run the sql query
	 */
	public static void main(String[] args) throws SQLException,ClassNotFoundException {
		Connection cn = null;
		try {

			LoginDetails.UserName = "Swapnil";
			LoginDetails.UserBalance = "200";
			LoginDetails.UserGameName = Games.SlotMachine;
			LoginDetails.UserId="1";
			
			
			DatabaseConnection dc = new DatabaseConnection();
			cn = dc.getDatabaseConnection("Casino");
			String name = "'swapnil'";
		    //dc.runUpdateSql("update User set Money ="+ LoginDetails.UserBalance + " where id =" + LoginDetails.UserId, cn);
			
			ResultSet rs = dc.runSelectSql("Select * from User", cn);
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
