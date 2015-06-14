package com.java.casinoserver;

/*@Author: Swapnil Aher
 * This is server where we are managing the User information with CASINO database
 * Multithreaded feature added to the server class
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestServer implements Runnable {
	static int count = 0;
	ObjectInputStream serverInputStream = null;
	static Socket sock= null;
	ObjectOutputStream clientOutputStream = null;

	/*public TestServer(Socket soc) {
		this.soc = soc;
	}*/

	public static void main(String[] args) throws IOException,
			ClassNotFoundException, SQLException {
		
		@SuppressWarnings("resource")
		ServerSocket localServer = new ServerSocket(6000);
		
		System.out.println("started Listening in Test Server class...");

		//spawn a thread for each request
		while (true) {
			sock = localServer.accept();
			System.out.println("local server connected...");
			new Thread(new TestServer()).start();
		}

	}

	@Override
	public void run() {

		try {
			System.out.println("Multi Thread Server side code execution started..."+ ++count);
			serverInputStream = new ObjectInputStream(sock.getInputStream());
			clientOutputStream = new ObjectOutputStream(sock.getOutputStream());

			@SuppressWarnings("unchecked")
			ArrayList<String> Userdata = null;

			try {
				Userdata = (ArrayList<String>) serverInputStream.readObject();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			ArrayList<String> listOutput = null;

			System.out.println("Perform ..." + Userdata.get(0));

			// Update Operation on Database is performed
			if (Userdata.get(0).equalsIgnoreCase("Update")) {

				Connection cn = null;
				try {
					System.out.println("Upadate Started....");
					DatabaseConnection dc = new DatabaseConnection();
					System.out.println("Connection created....");
					cn = dc.getDatabaseConnection("Casino");
					dc.runUpdateSql("update User set Money ="+ Userdata.get(3).toString() + " where id ="+ Userdata.get(2).toString(), cn);
					System.out.println("update statement executed");
				} catch (Exception e) {
					System.out.println("Upadate Exception" + e.getMessage());
				} finally {
					cn.close();
				}

			} else if (Userdata.get(0).equalsIgnoreCase("Select")) {

				Connection cn = null;
				try {

					listOutput = new ArrayList<String>();
					System.out.println("select Started....");
					DatabaseConnection dc = new DatabaseConnection();
					System.out.println("Connection created....");
					cn = dc.getDatabaseConnection("Casino");

					ResultSet rs = dc.runSelectSql("Select * from User where Upper(Name) == '"+ Userdata.get(1).toUpperCase() + "'", cn);

					if (!rs.isBeforeFirst()) {

						System.out.println("Record set is empty");
						dc.runUpdateSql(
								"INSERT INTO User (Name,Money) values ('"+ Userdata.get(1).toUpperCase()+ "',200);", cn);
						rs = dc.runSelectSql("Select * from User where Upper(Name) == '"+ Userdata.get(1).toUpperCase() + "'",cn);

						while (rs.next()) {
							listOutput.add(rs.getString("Name"));
							listOutput.add(String.valueOf(rs.getInt("id")));
							listOutput.add(String.valueOf(rs.getInt("Money")));
						}

					} else {

						while (rs.next()) {
							listOutput.add(rs.getString("Name"));
							listOutput.add(String.valueOf(rs.getInt("id")));
							listOutput.add(String.valueOf(rs.getInt("Money")));
						}
					}

				} catch (Exception e) {
					System.out.println("Select Exception" + e.getMessage());
				}
				cn.close();

			}

			clientOutputStream.writeObject(listOutput);
			
			System.out.println("Multi Thread Server side code ended..");

			// closing the socket and other stream objects
			sock.close();
			serverInputStream.close();
			clientOutputStream.close();
			
		} catch (IOException | SQLException e) {
			System.out.println(e);
		}

	}

}
