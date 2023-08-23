package Database.Aircraft;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBViewAllAircraftModel
 * @Description: This page will be used to connect to the Amazon AWS to View All Aircraft Model
 *  All other pages will be linked to this page
 * 
 */
import Database.DBConnection;
import Database.Client.DBViewAllClient;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBViewSelectAircraft extends DBConnection {
	public CallableStatement callable = null; 
	private int acID;
	private int acModelID;
	private String acStatus; 
	private String acMake;
	private String acModel;
	private String acRange;
	private String acRangeClass;
	private Double acPayload;
	private Double acLoadVolume;
	
	
	 //Default Constructor
	 
	public DBViewSelectAircraft() {
		try {
			statement = connect.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBViewAllAircraft"); 
		}
	}
	
	/**
	 * viewSelected - method to view an aircraft's information based on the AircraftID 
	 * @param airID
	 */
	public void viewSelected(int airID) {
		try {
		String method = "{call fastline.dbo.View_Selected_Aircraft(?)}"; 
		callable = connect.prepareCall(method); 
		callable.setInt(1, airID); // call the aircraft ID for searching
		
		//execute the query
		ResultSet rs = callable.executeQuery(); 
		
		/**
		 * Output from View_Selected_Aircraft:
		 * 1 = AircraftID - int
		 * 2 = ACModelID - int
		 * 3 = AircraftStatus - String
		 * 4 = ACMake - Varchar (string)
		 * 5 = ACModel - varchar (string)
		 * 6 = ACRange - varchar (string)
		 * 7 = ACRangeClassification - varchar (string)
		 * 8 = ACPayload - float (int or double?)
		 * 9 = ACLoadVolume - float (int or double?)
		 */
		while(rs.next()) {
			acID = rs.getInt(1); 
			acModelID = rs.getInt(2); 
			acStatus = rs.getString(3); 
			acMake = rs.getString(4); 
			acModel = rs.getString(5); 
			acRange = rs.getString(6); 
			acRangeClass = rs.getString(7); 
			acPayload = rs.getDouble(8); 
			acLoadVolume = rs.getDouble(9); 				
		}
		
	}catch (SQLException ex) {
		Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("view all clients could not be completed"); 
	}
		
	}
	
	
	//multiple getter methods for getting each found value
	 
	
	public int getAircraftID() {
		return acID; 
	}
	public int getModelID() {
		return acModelID; 
	}
	public String getStatus() {
		return acStatus; 
	}
	public String getMake() {
		return acMake; 
	}
	public String getModel() {
		return acModel; 
	}
	public String getRange() {
		return acRange; 
	}
	public String getRangeClass() {
		return acRangeClass; 
	}
	public Double getPayload() {
		return acPayload; 
	}
	public Double getLoadVolume() {
		return acLoadVolume; 
	}

}
