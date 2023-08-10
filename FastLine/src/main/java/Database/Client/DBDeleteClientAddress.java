package Database.Client;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBDeleteClientAddress
 * @Description: This page will be used to connect to the database and delete Client address.
 *  All other pages will be linked to this page
 * 
 */

import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBDeleteClientAddress extends DBConnection{
	public CallableStatement callable = null;
	private int clientAddressID;
	
	public DBDeleteClientAddress() {
		try {
			statement = connect.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteClientAddress"); 
		}
	}
	
	public void deleteClientAddress(int caID) {
		try {
			String method = "{call fastline.dbo.Delete_Client_Address(?)}"; 
			clientAddressID = caID;
			callable = connect.prepareCall(method); 
			callable.setInt(1, clientAddressID);			
			callable.executeQuery();
						
		}catch (SQLException ex) {
			Logger.getLogger(DBDeleteClientAddress.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete client address could not be completed"); 
		}
	}

}
