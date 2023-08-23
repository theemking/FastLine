package Database.Shipment;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBAddShipmentStatus
 * @Description: This page will be used to connect to the Amazon AWS to Add Shipment Status
 *  All other pages will be linked to this page
 *  fastline
 */
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBDeleteShipmentStatus extends DBConnection{
	
	public CallableStatement callable = null;
	private int shipmentStatusID;
	
	public DBDeleteShipmentStatus() {
		try {
			statement = connect.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteShipmentStatus"); 
		}
	}
	
	public void deleteShipmentStatus(int ssID) {
		try {
			String method = "{call fastline.dbo.Delete_Shipment_Status(?)}"; 
			shipmentStatusID = ssID;
			callable = connect.prepareCall(method); 
			callable.setInt(1, shipmentStatusID);			
			callable.executeQuery();
						
		}catch (SQLException ex) {
			Logger.getLogger(DBDeleteShipmentStatus.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete shipment status could not be completed"); 
		}
	}

}
