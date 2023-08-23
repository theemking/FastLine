package Database.Shipment;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBAddShipment
 * @Description: This page will be used to connect to the Amazon AWS to Add Shipment
 *  All other pages will be linked to this page
 *   
 */

// Imports:
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBAddShipment extends DBConnection{
	//Variables
			public CallableStatement callable = null;
			// shipment ID not in stored procedure -- public int shipmentID; 
			public int clientID;
			public float volume;
			public float weight;
			public int statusID;
			public Date start;
			public Date end;
			public String notes;

	//Default Constructor
	
public DBAddShipment() {
	//just initialize the class
	}
	
/**
* insertSQL Method 
* Database structure:
* 2 ClientID int
* 3 ShipmentVolume float
* 4 ShipmentWieght float
* 5 ShipmentStatusID int
* 6 ShipmentStartDate Date
* 7 ShipmentEndDate Date
* 8 ShipmentNotes String
*
*/
public void insertSQL(int cID, float vol, float weight, int stat, Date start, Date end, String notes) {
	try {
		String sql = "{call fastline.dbo.Add_Shipment(?,?,?,?,?,?,?)}";
		callable=connect.prepareCall(sql);
		callable.setInt(1,  cID);
		callable.setFloat(2,  vol);
		callable.setFloat(3,  weight);
		callable.setInt(4, stat);
		callable.setDate(5, start);
		callable.setDate(6, end);
		callable.setString(7, notes);
			
		//Execute Stored Procedure
		callable.executeQuery();
		
	} catch (SQLException ex) {
        System.out.println("Insert Shipment Problem !");
    }
	
}


}
