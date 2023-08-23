package Database.Flight;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBAddAirport
 * @Description: This page will be used to connect to the Amazon AWS to Delete Flight
 *  All other pages will be linked to this page
 *  
 */
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DBDeleteFlight extends DBConnection{
	
	public CallableStatement callable = null;
	private int flightID;
	
	public DBDeleteFlight() {
		try {
			statement = connect.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteFlight"); 
		}
	}
	
	public void deleteFlight(int fID) {
		try {
			String method = "{call fastline.dbo.Delete_Flight(?)}"; 
			flightID = fID;
			callable = connect.prepareCall(method); 
			callable.setInt(1, flightID);			
			callable.executeQuery();
						
		}catch (SQLException ex) {
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete flight could not be completed"); 
		}
	}

}
