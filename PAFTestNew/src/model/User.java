	package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	//here is the common method to connect to the DB
		private Connection connect()
		{
			Connection con = null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				//always should have to provide the correct details: DBServer/DBName, username, password  (newpaf is the DB name)
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/newpaf", "root", "");
			}
			catch (Exception e)
			{e.printStackTrace();}
			return con;
		}
		
		
		//this is to insert the user data into the nepaf DB
		public String insertUser(String Iname, String address, String phone, String Email, String Type,String uName, String pass)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for inserting."; }
				// create a prepared statement
				String query = " insert into users(`userID`,`name`,`userAddress`,`phoneNo`,`email`,`userType`,`userName`,`password`)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, Iname);
				preparedStmt.setString(3, address);
				preparedStmt.setString(4, phone);
				preparedStmt.setString(5, Email);
				preparedStmt.setString(6, Type);
				preparedStmt.setString(7, uName);
				preparedStmt.setString(8, pass);
				
				
				
				// this is to execute the statements
				
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
			}
			catch (Exception e)
			{
				output = "Error while inserting the User.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		
		//This is to read the data from DB and fetch 	 
		
		public String readUser()
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for reading"; }
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th> Name </th><th>Address</th>" +
				"<th>Phone Number</th>" +
				"<th>E-mail</th>" + "<th>User Type</th>" + "<th>Username</th>" + "<th>Password</th>" +
				"<th>Update</th><th>Remove</th></tr>";
				String query = "select * from users";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next())
				{
				String userID = Integer.toString(rs.getInt("userID"));
				String name = rs.getString("name");
				String userAddress = rs.getString("userAddress");
				String phoneNo = Integer.toString(rs.getInt("phoneNo"));
				String email = rs.getString("email");
				String userType = rs.getString("userType");
				String userName = rs.getString("userName");
				String password = rs.getString("password");
				
				// Add into the html table
				output += "<tr><td>" + name + "</td>";
				output += "<td>" + userAddress + "</td>";
				output += "<td>" + phoneNo + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + userType + "</td>";
				output += "<td>" + userName + "</td>";
				output += "<td>" + password + "</td>";
				
				
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-itemid='" + userID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-itemid='" + userID + "'></td></tr>";
						}
						con.close();
						
				// Complete the html table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		
		//this is to update user data 
		public String updateUser(String ID, String Iname, String address, String phone, String Email, String Type,String uName, String pass)
		
		
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for updating."; }
				// create a prepared statement
				String query = "UPDATE users SET name=?,userAddress=?,phoneNo=?,email=?,userType=?,userName=?,password=? WHERE userID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, Iname);
				preparedStmt.setString(2, address);
				preparedStmt.setString(3, phone);
				preparedStmt.setString(4, Email);
				preparedStmt.setString(5, Type);
				preparedStmt.setString(6, uName);
				preparedStmt.setString(7, pass);
				
				
				preparedStmt.setInt(8, Integer.parseInt(ID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newItems = readUser(); 
				 output = "{\"status\":\"success\", \"data\": \"" + 
				 newItems + "\"}"; 
				 } 
				 catch (Exception e) 
				 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while inserting the user.\"}"; 
				 System.err.println(e.getMessage()); 
				 }
			return output;
		}
	
		//this is to delete the user data from the DB
		public String deleteUser(String userID)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for deleting."; }
				// create a prepared statement
				String query = "delete from users where userID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(userID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				String newItems = readUser(); 
				 output = "{\"status\":\"success\", \"data\": \"" + 
				 newItems + "\"}"; 
				 } 
				 catch (Exception e) 
				 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
				 System.err.println(e.getMessage()); 
				 }
			
			return output;
		}

}
