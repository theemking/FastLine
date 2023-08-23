package Database.Shipment;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBUpdateShipment
 * @Description: This page will be used to connect to the Amazon AWS to Update Shipment
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
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUpdateShipment extends DBConnection{
	//Variables
			public CallableStatement callable = null;
			public int cID;
			public float sVol;
			public float sWeight;
			public int sID;
			public int status;
			public Date sDate;
			public Date eDate;
			public String sNote;

/**
* DataBase Structure:
* 1. ShipmentID int
* 2. ClientID int
* 3. ShipmentVolume float
* 4. ShipmentWeight float
* 5. ShipmentStatusID int
* 6. ShipmentStartDate Date
* 7. ShipmentEndDate DATE
* 8. ShipmentNotes String
*/
	//Default Constructor
public DBUpdateShipment() {
					
			//blank
}//end default constructor	

/** 
* updateShipment
* @param sID
* @param cID
* @param sVol
* @param sWeight
* @param status
* @param sDate
* @param eDate
* @param sNote

*/
public void updateShipment(int sID,int cID, float sVol,float sWeight, int status, Date sDate, Date eDate, String sNote) {
    try {
    	String storedP = "{call fastline.dbo.Update_Shipment(?,?,?,?,?,?,?,?)}"; 

        callable = connect.prepareCall(storedP);
        callable.setInt(1, sID);
        callable.setInt(2,cID);
        callable.setFloat(3, sVol);
        callable.setFloat(4,sWeight);
        callable.setInt(5, status);
        callable.setDate(6, sDate);
        callable.setDate(7, eDate);
        callable.setString(8, sNote);
        ResultSet rs = callable.executeQuery(); 

    } catch (SQLException ex) {
        System.out.println("Update Shipment Problem!");
    }
}//end updateShipment

public int getcID() {
	return cID;
}

public float getsVol() {
	return sVol;
}

public float getsWeight() {
	return sWeight;
}

public int getsID() {
	return sID;
}

public int getStatus() {
	return status;
}

public Date getsDate() {
	return sDate;
}

public Date geteDate() {
	return eDate;
}

public String getsNote() {
	return sNote;
}

}
