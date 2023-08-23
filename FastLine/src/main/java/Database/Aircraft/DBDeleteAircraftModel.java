package Database.Aircraft;

/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBDeleteAircraftModel
 * @Description: This page will be used to connect to the Amazon AWS to DB Delete Aircraf tModel
 *  All other pages will be linked to this page
 * 
 */
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBDeleteAircraftModel extends DBConnection{
	
	public CallableStatement callable = null;
	private int aircraftModelID;
	
	public DBDeleteAircraftModel() {
		try {
			statement = connect.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteAircraftModel"); 
		}
	}
	
	public void deleteAircraftModel(int amID) {
		try {
			String method = "{call fastline.dbo.Delete_Aircraft_Model(?)}"; 
			aircraftModelID = amID;
			callable = connect.prepareCall(method); 
			callable.setInt(1, aircraftModelID);			
			callable.executeQuery();
						
		}catch (SQLException ex) {
			Logger.getLogger(DBDeleteAircraftModel.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete aircraft model could not be completed"); 
		}
	}
	}