package Database.Client;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBUpdateClientAddress
 * @Description: This page will be used to connect to the database and update Client address.
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


public class DBUpdateClientAddress extends DBConnection{
	//Variables
		public CallableStatement callable = null;
		public String clientAdd1;
		public String clientAdd2;
		public String clientCity;
		public String clientState;
		public String clientInfo;
		public String clientZip;
		public int cID;
		public int cAddressID;


	/**
	 * DataBase structure:
	 * 1. ClientAddressID int
	 * 2. ClientID int
	 * 3. ClientAddressLine1 String
	 * 4. ClientAddressLine2 String
	 * 5. ClientAddressCity String
	 * 6. ClientAddressState String
	 * 7. ClientAddressZip int
	* Default Constructor
	* Blank
	 */
		public DBUpdateClientAddress() {
			//Just call class do nothing
		}		

/** 
* updateClientAddress
* @param cID
* @param add1
* @param add2
* @param city
* @param state
* @param zip
* updateClientAddress

*/
public void updateClientA(int cAddressID, int cID, String add1, String add2,String city,String state,String zip) {
    try {
    	String storedP = "{call fastline.dbo.Update_Client_Address(?,?,?,?,?,?,?)}"; 
     
        callable = connect.prepareCall(storedP);
          callable.setInt(1, cAddressID);
          callable.setInt(2, cID);
          callable.setString(3, add1);
          callable.setString(4, add2);
          callable.setString(5, city);
          callable.setString(6, state);
          callable.setString(7, zip);


          ResultSet rs = callable.executeQuery(); 

    } catch (SQLException ex) {
        System.out.println("Update Client Address Problem!");
    }
}//end updateClientAddress

public String getClientAdd1() {
	return clientAdd1;
}
public String getClientAdd2() {
	return clientAdd2;
}
public String getClientCity() {
	return clientCity;
}
public String getClientState() {
	return clientState;
}
public String getClientInfo() {
	return clientInfo;
}
public String getClientZip() {
	return clientZip;
}
public int getcID() {
	return cID;
}
public int getcAddressID() {
	return cAddressID;
}
	
}
