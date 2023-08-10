package Database.Client;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBViewAllClientType
 * @Description: This page will be used to connect to the database and update Client type.
 *  All other pages will be linked to this page
 * 
 */
//importing class
import Database.DBConnection;
import Database.Client.DBViewAllClient;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBViewAllClientType extends DBConnection{
	public CallableStatement callable = null; 
	private ArrayList<Integer> clientTypeId = new ArrayList<>();
	private ArrayList<String> clientType = new ArrayList<>();
	
	public DBViewAllClientType() {
		try {
			statement = connect.createStatement(); 
			viewAll();
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBViewAllClientType"); 
		}
		
	}
	
	public void viewAll() {
		try {
			String method = "{call fastline.dbo.View_All_Client_Type}"; 
			callable = connect.prepareCall(method); 
			
			//execute the query
			ResultSet rs = callable.executeQuery();
			/**
			 * 1 = ClientTypeID
			 * 2 = ClientType
			 */
			
			while(rs.next()) {
				clientTypeId.add(rs.getInt(1)); 
				clientType.add(rs.getString(2)); 			 
			}
			
		}catch (SQLException ex) {
			Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("view all client types could not be completed"); 
		}
	}

	//use just to print all strings to test - this will show in CLI. 
	public void getResults() {
		for(int i = 0; i<clientType.size(); i++) {
			System.out.println(clientTypeId.get(i)+ "-" + clientType.get(i)); 
		}
	}

	public ArrayList<Integer> getID(){
		return clientTypeId; 
	}
	
	public ArrayList<String> getClientType(){
		return clientType; 
	}	

	/**
	 * clearAllClientTypess -clears the arraylists of all the client information. 
	 */
	public void clearAllClientTypes() {
		clientTypeId.clear(); 
		clientType.clear(); 
	}

}
