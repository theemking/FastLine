package Database.Client;

/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: August 1, 2023
 * @Class: DBViewAllClient
 * @Description: This page will be used to connect to the Amazon AWS to view all stored procedure that will be display
 * on the client page. All other pages will be linked to this page
 * 
 */


//imports
import Database.DBConnection;
import Database.Client.DBViewAllClient;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBViewAllClient extends DBConnection {
	
	//variables
public CallableStatement callable = null; 
private ArrayList<Integer> ClientID = new ArrayList<>(); 
private ArrayList<String> ClientName = new ArrayList<>();
private ArrayList<String> ClientPhoneNumber =  new ArrayList<>(); 
private ArrayList<String> clientType = new ArrayList<>(); 
private ArrayList<String> Clientaddress1 = new ArrayList<>(); 
private ArrayList<String> Clientaddress2 = new ArrayList<>(); 
private ArrayList<String> Clientcity = new ArrayList<>(); 
private ArrayList<String> Clientstate = new ArrayList<>(); 
private ArrayList<String> Clientzip = new ArrayList<>(); 
private ArrayList<Integer> ClientaddressID = new ArrayList<>(); 


public DBViewAllClient() {
	try {
		statement = connect.createStatement();
	//	viewAll(); // call the viewAll function. 
			
	} catch(SQLException ex) {
		System.out.println("Database connection failed DBViewAllClient");
	}
}

public void viewAll() {
	try {
		String method = "{call fastline.dbo.View_All_Client(?,?,?,?,?,?,?,?,?,?)}"; 
		callable = connect.prepareCall(method); 
		
		//execute the query
		ResultSet rs = callable.executeQuery();
		/**
		 * 1 = ClientID
		 * 2 = ClientName
		 * 3 = Client Type
		 * 4 = phone number
		 * 5 = address line 1
		 * 6 = address line 2
		 * 7 = city
		 * 8 = state
		 * 9 = zip
		 * 10 = ClientAddressID
		 */
		
		while(rs.next()) {
			ClientID.add(rs.getInt(1)); 
			ClientName.add(rs.getString(2)); 
			clientType.add(rs.getString(3)); 
			ClientPhoneNumber.add(rs.getString(4)); 
			Clientaddress1.add(rs.getString(5)); 
			Clientaddress2.add(rs.getString(6)); 
			Clientcity.add(rs.getString(7)); 
			Clientstate.add(rs.getString(8)); 
			Clientzip.add(rs.getString(9));   
			ClientaddressID.add(rs.getInt(10)); 
		}
	
	}catch (SQLException ex) {
		Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("view all clients could not be completed"); 
	}
}

//use just to print all strings to test - this will show in CLI. 
public void getResults() {
	for(int i = 0; i<ClientName.size(); i++) {
		System.out.println(ClientName.get(i)+ "-" + clientType.get(i) + "-" + Clientaddress1.get(i) 
		+ " " + Clientaddress2.get(i) + "-" + Clientcity.get(i) + "-" + Clientstate.get(i) + "-" + Clientzip.get(i)); 
	}
}

public ArrayList<Integer> getID(){
	return ClientID; 
}
public ArrayList<String> getName(){
	return ClientName; 
}

public ArrayList<String> getClientType(){
	return clientType; 
}

public ArrayList<String> getClientPhone(){
	return ClientPhoneNumber; 
}

public ArrayList<String> getAddress1(){
	return Clientaddress1; 
}

public ArrayList<String> getAddress2(){
	return Clientaddress2; 
}

public ArrayList<String> getCity(){
	return Clientcity; 
}

public ArrayList<String> getState(){
	return Clientstate; 
}

public ArrayList<String> getZip(){
	return Clientzip; 
}
public ArrayList<Integer> getAddressID(){
	return ClientaddressID; 
}

/**
 * clearAllClients -clears the arraylists of all the client information. 
 */
public void clearAllClients() {
	ClientName.clear(); 
	clientType.clear(); 
	Clientaddress1.clear(); 
	Clientaddress2.clear(); 
	Clientcity.clear(); 
	Clientstate.clear(); 
	Clientzip.clear(); 
}	
	
	
}
