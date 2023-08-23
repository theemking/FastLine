package Database.Shipment;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBAddShipmentStatus
 * @Description: This page will be used to connect to the Amazon AWS to Add Shipment Status
 *  All other pages will be linked to this page
 *  
 */

// Imports:
import Database.DBConnection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.CallableStatement;

public class DBAddShipmentStatus extends DBConnection {
	//Variables
	public CallableStatement callable = null;
	public String shipmentStatus;

	//Default Constructor

public DBAddShipmentStatus(String status) {
	try {
		this.shipmentStatus=status;
		
		
		String storedP = "{call fastline.dbo.Add_Shipment_Status}"; 
		callable = connect.prepareCall(storedP);
		insertSQL(shipmentStatus);		
	}
	catch (SQLException ex) {
		Logger.getLogger(DBAddShipmentStatus.class.getName()).log(Level.SEVERE, null, ex);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("Problem adding New Shipment Status"); 
	}
	
}
/**
* insertSQL Method 
* Database structure:
* 1 ShipmentStatus String
*/
public void insertSQL(String status) {
	try {
		String sql = "{call fastline.dbo.Add_Flight(?)}";
		callable=connect.prepareCall(sql);
		callable.setString(1,  status);
		
		//Execute Stored Procedure
		callable.executeQuery();
		
	} catch (SQLException ex) {
        System.out.println("Insert Shipment Status Problem !");
    }
		
}

}
