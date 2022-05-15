package com;
import java.sql.*;
public class Complain
{
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafcomplain", "root", "18058");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public String readComplains()
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{
	return "Error while connecting to the database for reading.";
	}
	
	// Prepare the html table to be displayed
	output = "<table class='table' border='1'><thead class='table-success'><tr><th scope='col'>Complainer's Full Name</th><th scope='col'>Complain Address</th>" +
			 "<th scope='col'>Email</th>" +"<th scope='col'>Contact Number</th>" +"<th scope='col'>Account Number</th>" +
			 "<th scope='col'>Complain</th>" +"<th scope='col'>Update</th>"+"<th scope='col'>Delete</th></tr></thead><tbody>";
	String query = "select * from complaint";
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	
	// iterate through the rows in the result set
	while (rs.next())
	{
	String complain_id = Integer.toString(rs.getInt("complain_id"));
	String name = rs.getString("name");
	String address = rs.getString("address");
	String email = rs.getString("email");
	String contact_no = rs.getString("contact_no");
	String account_no = rs.getString("account_no");
	String complain = rs.getString("complain");
	
	// Add into the html table
		output += "<tr><td class='table-warning'><input id='hidComplainIDUpdate' name='hidComplainIDUpdate' type='hidden' value='" + complain_id
		+ "'>" + name + "</td>";
		output += "<td class='table-warning'>" + address + "</td>";
		output += "<td class='table-warning'>" + email + "</td>";
		output += "<td class='table-warning'>" + contact_no + "</td>";
		output += "<td class='table-warning'>" + account_no + "</td>";
		output += "<td class='table-warning'>" + complain + "</td>";
	// buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-itemid='"+ complain_id + "'>" + "</td>"
	+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"+ complain_id + "'>" + "</td></tr>";
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
	

	public String insertComplain(String name, String address, String email, String contact_no, String account_no, String complain)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into complaint (`complain_id`,`name`,`address`,`email`,`contact_no`,`account_no`,`complain`)"
			+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, contact_no);
			preparedStmt.setString(6, account_no);
			preparedStmt.setString(7, complain);
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newComplains = readComplains();
			output = "{\"status\":\"success\", \"data\": \"" +
					newComplains + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the complain.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateComplain(String complain_id,String name,String address,String email, String contact_no, String account_no, String complain)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE complaint SET name=?,address=?,email=?,contact_no=?,account_no=?,complain=?WHERE complain_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, name);
			   preparedStmt.setString(2, address);
			   preparedStmt.setString(3, email);
			   preparedStmt.setString(4, contact_no);
			   preparedStmt.setString(5, account_no);
			   preparedStmt.setString(6, complain);
			   preparedStmt.setInt(7, Integer.parseInt(complain_id));

			//execute the statement
			preparedStmt.execute();
			con.close();
			String newComplains = readComplains();
			output = "{\"status\":\"success\", \"data\": \"" +
					newComplains + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while updating the complain.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteComplain(String complain_id)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from complaint where complain_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(complain_id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newComplains = readComplains();
			output = "{\"status\":\"success\", \"data\": \"" +
					newComplains + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the complain.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
