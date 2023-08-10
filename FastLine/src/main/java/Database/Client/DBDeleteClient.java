package Database.Client;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBDeleteClient
 * @Description: This page will be used to connect to the database and delete Client address.
 *  All other pages will be linked to this page
 * 
 */

//importing clases
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBDeleteClient extends DBConnection{
	public CallableStatement callable = null;
	private int clientID;
	
	public DBDeleteClient() {
		try {
			statement = connect.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteClient"); 
		}
	}
	
	public void deleteClient(int cID) {
		try {
			String method = "{call fastline.dbo.Delete_Client(?)}"; 
			clientID = cID;
			callable = connect.prepareCall(method); 
			callable.setInt(1, clientID);			
			callable.executeQuery();
						
		}catch (SQLException ex) {
			Logger.getLogger(DBDeleteClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete client could not be completed"); 
		}
	}
}
