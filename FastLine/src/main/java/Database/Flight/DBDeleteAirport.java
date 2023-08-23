package Database.Flight;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBAddAirport
 * @Description: This page will be used to connect to the Amazon AWS to Delete Airport
 *  All other pages will be linked to this page
 *  
 */
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBDeleteAirport extends DBConnection{
	
	public CallableStatement callable = null;
	private int airportID;
	
	public DBDeleteAirport() {
		try {
			statement = connect.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteAirport"); 
		}
	}
	
	public void deleteAirport(int aID) {
		try {
			String method = "{call fastline.dbo.Delete_Airport(?)}"; 
			airportID = aID;
			callable = connect.prepareCall(method); 
			callable.setInt(1, airportID);			
			callable.executeQuery();
						
		}catch (SQLException ex) {
			Logger.getLogger(DBDeleteAirport.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete airport could not be completed"); 
		}
	}

}
