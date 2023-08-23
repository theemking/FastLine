package Database.Pilot;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBDeletePilot
 * @Description: This page will be used to connect to the Amazon AWS to Delete Pilot
 *  All other pages will be linked to this page
 *   
 */
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBDeletePilot extends DBConnection{
	
	public CallableStatement callable = null;
	private int pilotID;
	
	public DBDeletePilot() {
		try {
			statement = connect.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeletePilot"); 
		}
	}
	
	public void deletePilot(int pID) {
		try {
			String method = "{call fastline.dbo.Delete_Pilot(?)}"; 
			pilotID = pID;
			callable = connect.prepareCall(method); 
			callable.setInt(1, pilotID);			
			callable.executeQuery();
						
		}catch (SQLException ex) {
			Logger.getLogger(DBDeletePilot.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete pilot could not be completed"); 
		}
	}

}
