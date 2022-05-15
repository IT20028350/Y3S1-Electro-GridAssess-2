package com;


import model.User;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Users")
public class UserService {
	
	User userObj = new User();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUser()
	{ 
		return userObj.readUser();
	}

	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertUser(
	
			@FormParam("name") String name, 
			@FormParam("userAddress") String userAddress, 
			 @FormParam("phoneNo") String phoneNo, 
			 @FormParam("email") String email,
			 @FormParam("userType") String userType, 
			 @FormParam("userName") String userName, 
			 @FormParam("password") String password) 
	{ 
		 String output = userObj.insertUser(name, userAddress, phoneNo, email,userType,userName,password); 
		return output; 
	}


	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateUser(String userData) 
	{ 
		//This is to Convert the input string to a JSON object 
		 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject(); 
		//this is to Read the values from the JSON object
		 String userID = userObject.get("userID").getAsString(); 
		 String name = userObject.get("name").getAsString(); 
		 String userAddress = userObject.get("userAddress").getAsString(); 
		 String phoneNo = userObject.get("phoneNo").getAsString(); 
		 String email = userObject.get("email").getAsString(); 
		 String userType = userObject.get("userType").getAsString(); 
		 String userName = userObject.get("userName").getAsString(); 
		 String password = userObject.get("password").getAsString(); 
		 
		 String output = userObj.updateUser(userID, name, userAddress, phoneNo, email,userType,userName,password); 
		return output; 
	}

	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteUser(String userData) 
	{ 
		//Convert the input string to  XML document
		 Document doc = Jsoup.parse(userData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <userID>
		 String userID = doc.select("userID").text(); 
		 String output = userObj.deleteUser(userID); 
		return output; 
	}


	}