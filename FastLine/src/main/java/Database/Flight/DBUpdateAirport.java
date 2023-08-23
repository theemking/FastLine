package Database.Flight;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBAddAirport
 * @Description: This page will be used to connect to the Amazon AWS to Update Airport
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


public class DBUpdateAirport extends DBConnection {
	//Variables
		public CallableStatement callable = null;
		public String aName;
		public String aLoc;
		public boolean aHub;
		public float aDis;
		public int aID;
		

/**
 * Database Structure:
 * 1. AirportID int
 * 2. AirportName String
 * 3. AirportLocation String
 * 4. AirportHub Boolean
 * 5. AirportDistanceFromHub float
* 
*/	
	//Default Constructor
public DBUpdateAirport() {
			
}

/**
* updateAirport
* @param aID
* @param aName
* @param aLoc
* @param aHub
* @param aDist
* @param input
*/
public void updateAirport(int aID, String aName, String aLoc, boolean aHub, float aDist) {
    try {
    	String storedP = "{call fastline.dbo.Update_Airport(?,?,?,?,?)}"; 
       
        callable = connect.prepareCall(storedP);
        callable.setInt(1, aID);
        callable.setString(2, aName);
        callable.setString(3, aLoc);
        callable.setBoolean(4, aHub);
        callable.setFloat(5, aDist);

        ResultSet rs = callable.executeQuery(); 

    } catch (SQLException ex) {
        System.out.println("Update Airport Problem!");
    }
}//end updateAirport

public String getaName() {
	return aName;
}

public String getaLoc() {
	return aLoc;
}

public boolean isaHub() {
	return aHub;
}

public float getaDis() {
	return aDis;
}

public int getaID() {
	return aID;
}


}
