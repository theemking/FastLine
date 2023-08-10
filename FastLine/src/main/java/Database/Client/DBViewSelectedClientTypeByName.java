package Database.Client;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBViewSelectedClientTypeByName
 * @Description: This page will be used to connect to the database and select Client type by name.
 *  All other pages will be linked to this page
 * 
 */
//Import clases
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBViewSelectedClientTypeByName extends DBConnection{
	public CallableStatement callable = null; 
	private int clientTypeId;
	private String clientType;
	
	/**
	 * Default Constructor
	 */
	public DBViewSelectedClientTypeByName() {
		try {
			statement = connect.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBViewSelectClientTypeByName"); 
		}
		
	}
	
	/**
	 * viewSelected - method to view a clients information based on a ClientID
	 * @param clientTypeID
	 */
	public void viewSelected(String cT) {
		try {
		String method = "{call fastline.dbo.View_Selected_Client_Type_By_Name(?)}"; 
		callable = connect.prepareCall(method); 
		callable.setString(1, cT); // call the client ID for searching
		
		//execute the query
		ResultSet rs = callable.executeQuery(); 
		
		/**
		 * output from View_Selected_Client_Type
		 * 1 = Client Type ID
		 * 2 = Client Type		 
		 */
		while(rs.next()) {
			clientTypeId = rs.getInt(1); 
			clientType = rs.getString(2);			 
		}
		rs.close(); 
		
		}catch (SQLException ex) {
			//Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			//e.printStackTrace();
			System.out.println("view selected type by name could not be completed"); 
		}		
		
	}
	
	public int getID(){
		return clientTypeId; 
	}
	
	public String getClientType(){
		return clientType; 
	}	
}
