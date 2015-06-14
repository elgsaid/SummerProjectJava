package com.java.casinoserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class TestServer {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		ObjectInputStream serverInputStream = null;
		Socket soc = null;
		ObjectOutputStream clientOutputStream = null;

		ServerSocket localServer = new ServerSocket(6000);
		
		try {
			while (true) {
				soc = localServer.accept();
				try {
					
						System.out.println("Server started...");
						serverInputStream = new ObjectInputStream(
								soc.getInputStream());
						clientOutputStream = new ObjectOutputStream(
								soc.getOutputStream());

						@SuppressWarnings("unchecked")
						ArrayList<String> Userdata = (ArrayList<String>) serverInputStream.readObject();
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
								dc.runUpdateSql("update User set Money ="+ Userdata.get(3).toString()+ " where id ="+ Userdata.get(2).toString(), cn);
								System.out.println("update statement executed");
							} catch (Exception e) {
								System.out.println("Upadate Exception"+ e.getMessage());
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

								ResultSet rs = dc.runSelectSql(	"Select * from User where Upper(Name) == '"	+ Userdata.get(1).toUpperCase()	+ "'", cn);

								if (!rs.isBeforeFirst()) {

									System.out.println("Record set is empty");
									dc.runUpdateSql("INSERT INTO User (Name,Money) values ('"+ Userdata.get(1).toUpperCase()+ "',200);", cn);
									rs = dc.runSelectSql("Select * from User where Upper(Name) == '"+ Userdata.get(1).toUpperCase()	+ "'", cn);

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
							System.out.println("Select Exception"	+ e.getMessage()); 
						} finally {
							cn.close();
						}

						}

						clientOutputStream.writeObject(listOutput); 
						clientOutputStream.close();
						System.out.println("Server side code ended..");

					} finally {
					soc.close();
					serverInputStream.close();
					clientOutputStream.close();
				}
			}
		} finally {
			localServer.close();
		}
	}

}
