package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Item {

	//A common method to connect to the DB 
		private Connection connect() {
			Connection con = null;
			
			try {
				 Class.forName("com.mysql.cj.jdbc.Driver");
				 //Provide the correct details: DBServer/DBName, username, password 
				 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorysystem","root","");
				//For testing          
				 System.out.print("Successfully connected");
				 
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return con; 
		}
		
		public String readItem() {  
			String output = "";  
			
			try {  
				Connection con = connect();  
				if (con == null)  {   
					return "Error while connecting to the database for reading.";  
				} 

				// Prepare the html table to be displayed   
				output = "<table border='1'><tr>"
						+ "<th>itemcode</th>"
						+ "<th>itemcategory</th>"
						+ "<th>itemspecific</th>"
						+ "<th>price</th>"
						+ "<th>Update</th><th>Delete</th></tr>";


				  String query = "select * from item";   
				  Statement stmt = con.createStatement();   
				  ResultSet rs = stmt.executeQuery(query); 

				  // iterate through the rows in the result set   
				  while (rs.next())   {  			
					  
						String itemcode = rs.getString("itemcode");
						String itemcategory = rs.getString("itemcategory"); 
						String itemspecific = rs.getString("itemspecific"); 
						String price = Integer.toString(rs.getInt("price"));
				
						
										
					
					  // Add into the html table    

					  
					  output += "<tr><td>" + itemcode + "</td>";
						output += "<td>" + itemcategory + "</td>";
						output += "<td>" + itemspecific + "</td>";
						output += "<td>" + price + "</td>";
						
					  
					// buttons     
					  output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
					  		+ "<td><input name='btnDelete' type='button' value='Delete' class='btnDelete btn btn-danger' data-appID='"+ itemcode +"'>"+"</td></tr>";

					} 
				  
				  con.close(); 

				  // Complete the html table   
				  output += "</table>"; 
				}
				catch (Exception e) {  
					output = "Error while reading the Item.";  
					System.err.println(e.getMessage()); 
				}

				return output;
			}
		
		//Insert appointment
		public String insertItem(String itemcode , String itemcategory, String itemspecific, String price ) {
			String output = "";

			try {
				Connection con = connect();  

				if (con == null) {
					return "Error while connecting to the database";
				}

				// create a prepared statement   
				String query = " insert into item (`itemcode`,`itemcategory`,`itemspecific`,`price`)"+" values (?, ?, ?, ?)";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values 
				preparedStmt.setString(1, itemcode);
				preparedStmt.setString(2, itemcategory);
				preparedStmt.setString(3, itemspecific);
				preparedStmt.setInt(4,Integer.parseInt(price));
				
				//execute the statement   
				preparedStmt.execute();   
				con.close(); 

				//Create JSON Object to show successful msg.
				String newItem = readItem();
				output = "{\"status\":\"success\", \"data\": \"" + newItem + "\"}";
			}
			catch (Exception e) {  
				//Create JSON Object to show Error msg.
				output = "{\"status\":\"error\", \"data\": \"Error while Inserting Item.\"}";   
				System.err.println(e.getMessage());  
			} 

			 return output; 
		}
		
		//Update appointment
		public String updateItem(String itemcode , String itemcategory, String itemspecific, String Price)  {   
			String output = ""; 
		 
		  try   {   
			  Connection con = connect();
		 
			  if (con == null)    {
				  return "Error while connecting to the database for updating."; 
			  } 
		 
		   // create a prepared statement    
			   String query =  "update item set itemcategory=?,itemspecific=?,price=? where itemcode=?";
				 
		   PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		   // binding values   
			preparedStmt.setString(1, itemcategory);
			preparedStmt.setString(2, itemspecific);
			preparedStmt.setInt(3,Integer.parseInt(Price));
			preparedStmt.setString(4, itemcode);
			
		   
		 
		   // execute the statement    
		   preparedStmt.execute();    
		   con.close(); 
		 
		   //create JSON object to show successful msg
		   String newItem = readItem();
		   output = "{\"status\":\"success\", \"data\": \"" + newItem + "\"}";
		   }   catch (Exception e)   {    
			   output = "{\"status\":\"error\", \"data\": \"Error while Updating Item Details.\"}";      
			   System.err.println(e.getMessage());   
		   } 
		 
		  return output;  
		  }
		
		public String deleteItem(String itemcode) {  
			String output = ""; 
		 
		 try  {   
			 Connection con = connect();
		 
		  if (con == null)   {    
			  return "Error while connecting to the database for deleting.";   
		  } 
		 
		  // create a prepared statement   
		  String query = "DELETE FROM item WHERE itemcode=?"; 
		 
		  PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		  // binding values   
		  preparedStmt.setString(1, itemcode);      
		  // execute the statement   
		  preparedStmt.execute();   
		  con.close(); 
		 
		  //create JSON Object
		  String newItem = readItem();
		  output = "{\"status\":\"success\", \"data\": \"" + newItem + "\"}";
		  }  catch (Exception e)  {  
			  //Create JSON object 
			  output = "{\"status\":\"error\", \"data\": \"Error while Deleting Item.\"}";
			  System.err.println(e.getMessage());  
			  
		 } 
		 
		 return output; 
		 }
}
