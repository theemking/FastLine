package Database.Client;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBUpdateClient
 * @Description: This page will be used to connect to the database and update Client.
 *  All other pages will be linked to this page
 * 
 */

//Imports:
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBUpdateClient extends DBConnection {
	//Variables
	public CallableStatement callable = null;
	public String cName;
	public int cType;
	public int cID;
	public String cPhone;
	
	/**
	 * Database structure:
	 * 1. Client ID
	 * 2. ClientName String
	 * 3. ClientTypeID int
	 * 4. ClientPhoneNumber String
	/** Blank
	* Default Constructor
	*/
	public DBUpdateClient() {
		//Do nothing call the class 
	}			
	
	/** 
	* updateClient
	* @param cID
	* @param cName
	* @param cIDtype
	* @param cPhone
	*
	*/
	public void updateC(int cID, String cName, int cType, String cPhone) {
	    
		try {
	    	String storedP = "{call fastline.dbo.Update_Client(?,?,?,?)}"; 

	        callable = connect.prepareCall(storedP);

	        callable.setInt(1, cID);
	        callable.setString(2, cName);
	        callable.setInt(3, cType);
	        callable.setString(4, cPhone);
	       // callable.executeQuery(); 
	        ResultSet rs = callable.executeQuery(); 
	    } catch (SQLException ex) {
	        System.out.println("Update Client Problem!");
	    }
		
		
	}//end updateClient

	public String getcName() {
		return cName;
	}

	public int getcType() {
		return cType;
	}

	public int getcID() {
		return cID;
	}

	public String getcPhone() {
		return cPhone;
	}	
	
	
	
	
	
	
}
