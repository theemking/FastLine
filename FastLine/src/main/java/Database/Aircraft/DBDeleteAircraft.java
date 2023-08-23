package Database.Aircraft;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBDeleteAircraft
 * @Description: This page will be used to connect to the Amazon AWS to DB Delete Aircraft.
 *  All other pages will be linked to this page
 * 
 */
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBDeleteAircraft extends DBConnection{
	public CallableStatement callable = null;
	private int aircraftID;
	
	public DBDeleteAircraft() {
		try {
			statement = connect.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteAircraft"); 
		}
	}
	
	public void deleteAircraft(int aID) {
		try {
			String method = "{call fastline.dbo.Delete_Aircraft(?)}"; 
			aircraftID = aID;
			callable = connect.prepareCall(method); 
			callable.setInt(1, aircraftID);			
			callable.executeQuery();
						
		}catch (SQLException ex) {
			Logger.getLogger(DBDeleteAircraft.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete aircraft could not be completed"); 
		}
	}
}
