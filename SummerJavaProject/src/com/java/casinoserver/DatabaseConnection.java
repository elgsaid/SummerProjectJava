package com.java.casinoserver;
import java.sql.*;
import java.util.ArrayList;
/*
 * @Author: Swapnil Aher
 * 
 * This is the database class where we are performing 
 * 1) Update data to database
 * 2) Insert user data into database
 * 
 * It also contains a driver program to check connection and check the above mentioned operation
 */
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
 
	public ResultSet runSelectSql(String sql, Connection cn,ArrayList<String> Userdata) throws SQLException {
		System.out.println("In Select Statement...."+sql);
		
		/*Statement sta = cn.createStatement();
		return sta.executeQuery(sql);*/
		
		 PreparedStatement ps = null;
		 ps = cn.prepareStatement(sql);
		 ps.setString(1, Userdata.get(1).toUpperCase());
		 return ps.executeQuery();
	}
	
	public void runUpdateSql(String sql, Connection cn,ArrayList<String> Userdata ) throws SQLException {
		
		 System.out.println("In DB update function .. "+sql);
		/* Statement sta = cn.createStatement();
		 sta.executeUpdate(sql);*/

		 PreparedStatement ps = null;
		 ps = cn.prepareStatement(sql);
		 
		 ps.setInt(1,Integer.parseInt(Userdata.get(3).toString()));
		 ps.setInt(2, Integer.parseInt(Userdata.get(2).toString()));
		 
		 int result= ps.executeUpdate();
		 System.out.println(result);
		 
	}

	public void runInsertSql(String sql, Connection cn,ArrayList<String> Userdata ) throws SQLException {
		
		 System.out.println("In DB update function .. "+sql);
		/* Statement sta = cn.createStatement();
		 sta.executeUpdate(sql);*/

		 PreparedStatement ps = null;
		 ps = cn.prepareStatement(sql);
		 ps.setString(1, Userdata.get(1).toUpperCase());
		 ps.executeUpdate();
			
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
		    
			ArrayList<String> Userdata = new ArrayList<String>();
			Userdata.add("Update");
			Userdata.add("mukhesh");
			Userdata.add("1");
			Userdata.add("750");
			
			dc.runUpdateSql("update User set Money =? where id =?", cn,Userdata);
			ResultSet rs = dc.runSelectSql("Select * from User where Upper(Name)== ?", cn,Userdata);
			while (rs.next()) {
				// read the result set
				System.out.println("name = " + rs.getString("Name"));
				System.out.println("id = " + rs.getInt("id"));
				System.out.println("Money = " + rs.getInt("Money"));
			}
		    
			dc.runInsertSql("INSERT INTO User (Name,Money) values (?,200);", cn, Userdata);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		} finally {

			if (cn != null)
				cn.close();
		}

	}
}
