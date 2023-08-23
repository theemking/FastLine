package Database.Shipment;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: CallableStatement
 * @Description: This page will be used to connect to the Amazon AWS to Callable Statement
 *  All other pages will be linked to this page
 *  
 */
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBDeleteShipment extends DBConnection{
	
	public CallableStatement callable = null;
	private int shipmentID;
	
	public DBDeleteShipment() {
		try {
			statement = connect.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteShipment"); 
		}
	}
	
	public void deleteShipment(int sID) {
		try {
			String method = "{call fastline.dbo.Delete_Shipment(?)}"; 
			shipmentID = sID;
			callable = connect.prepareCall(method); 
			callable.setInt(1, shipmentID);			
			callable.executeQuery();
						
		}catch (SQLException ex) {
		} catch(Exception e) {
			System.out.println("delete shipment could not be completed"); 
		}
	}
}
