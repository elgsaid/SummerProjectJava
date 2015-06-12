package com.java.casinoserver;

import java.net.*;
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Client {

	static Socket s = null;
	static ObjectOutputStream out = null;
	static ObjectInputStream serverInput = null;

	@SuppressWarnings("unchecked")
	public static void SelectOrInsertUser(String UserName) throws IOException {
		try {
			s = new Socket("127.0.0.1", 6000);
			out = new ObjectOutputStream(s.getOutputStream());
			 
			ArrayList<String> Userdata = new ArrayList<String>();

			Userdata.add("Select");
			Userdata.add(UserName);

			out.writeObject(Userdata);
			out.flush();

			serverInput = new ObjectInputStream(s.getInputStream());
			ArrayList<String> receiveddata = ((ArrayList<String>) serverInput
					.readObject());

			// assign login user details to the LoginDetails class
			LoginDetails.UserName = receiveddata.get(0);
			LoginDetails.UserId = receiveddata.get(1);
			LoginDetails.UserBalance = receiveddata.get(2);

			System.out.println("Message receive from server. "
					+ receiveddata.get(0));
			System.out.println("Message receive from server. "
					+ receiveddata.get(1));
			System.out.println("Message receive from server. "
					+ receiveddata.get(2));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
			//serverInput.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static void UpdateUser() throws IOException {

		// Socket sUpdate = null;
		// ObjectOutputStream out = null;
		// ObjectInputStream serverInput =null;

		try {
			s = new Socket("127.0.0.1", 6000);
			// sUpdate= s;
			out = new ObjectOutputStream(s.getOutputStream());

			ArrayList<String> Userdata = new ArrayList<String>();

			Userdata.add("Update");
			Userdata.add(LoginDetails.UserName);
			Userdata.add(String.valueOf(LoginDetails.UserId));
			Userdata.add(String.valueOf(LoginDetails.UserBalance));

			out.writeObject(Userdata);
			out.flush();

			serverInput = new ObjectInputStream(s.getInputStream());
			System.out.println("Message receive from server"
					+ (ArrayList<String>) serverInput.readObject());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
			serverInput.close();
		}
	}

	public static void main(String[] args) throws IOException {

		Client.SelectOrInsertUser("Swapnil");
		Client.UpdateUser();

	}
}