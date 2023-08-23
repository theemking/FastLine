package Database.Shipment;

/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBUpdateShipmentStatus
 * @Description: This page will be used to connect to the Amazon AWS to Update Shipment Status
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



public class DBUpdateShipmentStatus extends DBConnection{
	//Variables
	public CallableStatement callable = null;
	public int sStatus;
	public int sID;
	
/**
* DataBase Structure:
* 1. ShipmentStatusID int
* 2. ShipmentStatus int CHANGE	 
* MADE A CHANGE TO ShipmentStatus DataType to match DATABASE
* Change String to int
*/
	//Default Constructor
	public DBUpdateShipmentStatus() {

	}//end default constructor	
	
	/** 
	* updateShipmentStatus
	* @param sID
	* @param sStatus
	*/
	public void updateShipmentStatus(int sID,int sStatus) {
	    try {
	    	String storedP = "{call fastline.dbo.Update_Shipment_Status(?,?)}"; 
	     
	        callable = connect.prepareCall(storedP);
	        callable.setInt(1,sID);
	        callable.setInt(2,sStatus);	 

	        ResultSet rs = callable.executeQuery(); 

	    } catch (SQLException ex) {
	        System.out.println("Update Shipment Status Problem!");
	    }
	}//end updateShipmentStatus

	public int getsStatus() {
		return sStatus;
	}

	public int getsID() {
		return sID;
	}


}
