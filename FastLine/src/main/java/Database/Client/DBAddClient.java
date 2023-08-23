package Database.Client;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBAddClient
 * @Description: This page will be used to connect to the database and add new Clients.
 *  All other pages will be linked to this page
 */
//Imports:
import Database.DBConnection; 
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBAddClient extends DBConnection {
	//Variables
	public CallableStatement callable = null;
	public String name;
	public int type;
	public String phone;

	// Default Constructor
	public DBAddClient() {
		//nothing just call this class. 
	}
	/**
	 * This method will insert the field below
	 * @param clientName
	 * @param clientType
	 * @param clientPhone
	 */
	public void insertSQL(String clientName, int clientType, String clientPhone) {
		try {
			String sql = "{call fastline.dbo.Add_Client(?,?,?)}";
			callable=connect.prepareCall(sql);
			callable.setString(1,  clientName);
			callable.setInt(2, clientType);
			callable.setString(3, clientPhone);
			//Execute Stored Procedure
			callable.executeQuery();
			
		} catch (SQLException ex) {
	        System.out.println("Insert Client Problem !");
	    }
		
	}//end insertSQL

}







