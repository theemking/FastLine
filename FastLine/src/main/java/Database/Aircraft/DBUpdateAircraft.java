package Database.Aircraft;

/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBUpdateAircraft
 * @Description: This page will be used to connect to the Amazon AWS to DB Update Aircraft
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

public class DBUpdateAircraft extends DBConnection {
	//Variables
	public CallableStatement callable = null;
	public int modelID;
	public int aID;

/**
 * Database structure:
 * 1. AircraftID int
 * 2. ACModelID int
*/	
public DBUpdateAircraft() {
	//Do nothing call the class 

}//end default constructor

/**
* updateAircraft
* @param aID
* @param modelID
* @param idStatus
*/	
public void updateAircraft(int aID, int modelID) {
    try {
    	String storedP = "{call fastline.dbo.Update_Aircraft(?,?)}"; 
     
        callable = connect.prepareCall(storedP);
        callable.setInt(1, aID);
        callable.setInt(2,modelID);
      
       
        ResultSet rs = callable.executeQuery(); 

    } catch (SQLException ex) {
        System.out.println("Update Aircraft Problem!");
    }
}

//setting up getters
public int getModelID() {
	return modelID;
}

public int getaID() {
	return aID;
}


}//end class

