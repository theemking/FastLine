package Database.Aircraft;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBViewAllAircraft
 * @Description: This page will be used to connect to the Amazon AWS to View All Aircraft
 *  All other pages will be linked to this page
 * 
 */

import Database.DBConnection;
import Database.Client.DBViewAllClient;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBViewAllAircraft extends DBConnection {
	//variables
	public CallableStatement callable = null; 
	private ArrayList<Integer> acID = new ArrayList<>();
	private ArrayList<Integer> acModelID = new ArrayList<>(); 
	private ArrayList<String> acStatus = new ArrayList<>(); 
	private ArrayList<String> acMake = new ArrayList<>();
	private ArrayList<String> acModel = new ArrayList<>(); 
	private ArrayList<String> acRange = new ArrayList<>(); 
	private ArrayList<String> acRangeClass = new ArrayList<>(); 
	private ArrayList<Double> acPayload = new ArrayList<>(); 
	private ArrayList<Double> acLoadVolume = new ArrayList<>(); 	
	
	 //Default Constructor

	public DBViewAllAircraft() {
		try {
			statement = connect.createStatement(); 
			viewAll(); //call the viewAll function; 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBViewAllAircraft"); 
		}
	}


	public void viewAll() {
		try {
			String method = "{call fastline.dbo.View_All_Aircraft}"; 
			callable = connect.prepareCall(method); 
			
			//execute the query
			ResultSet rs = callable.executeQuery(); 
			
			/**
			 * Output from View_All_Aircraft:
			 * 1 = AircraftID - int
			 * 2 = ACModelID - int
			 * 3 = AircraftStatus - (string)
			 * 4 = ACMake - Varchar (string)
			 * 5 = ACModel - varchar (string)
			 * 6 = ACRange - varchar (string)
			 * 7 = ACRangeClassification - varchar (string)
			 * 8 = ACPayload - float (int or double?)
			 * 9 = ACLoadVolume - float (int or double?)
			 */
			while(rs.next()) {
				acID.add(rs.getInt(1)); 
				acModelID.add(rs.getInt(2)); 
				acStatus.add(rs.getString(3)); 
				acMake.add(rs.getString(4)); 
				acModel.add(rs.getString(5)); 
				acRange.add(rs.getString(6)); 
				acRangeClass.add(rs.getString(7)); 
				acPayload.add(rs.getDouble(8)); 
				acLoadVolume.add(rs.getDouble(9)); 				
			}
			
		}catch (SQLException ex) {
			Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("view all clients could not be completed"); 
		}
	}
	
	/**
	 * multiple get methods to access each ArrayList individually
	 */
	public ArrayList<Integer> getAircraftID(){
		return acID; 
	}
	
	public ArrayList<Integer> getAircraftModelID(){
		return acModelID; 
	}
	
	public ArrayList<String> getAircraftStatusID(){
		return acStatus; 
	}
	
	public ArrayList<String> getAircraftMake(){
		return acMake; 
	}
	
	public ArrayList<String> getAircraftModel(){
		return acModel; 
	}
	
	public ArrayList<String> getAircraftRange(){
		return acRange; 
	}
	
	public ArrayList<String> getAircraftRangeClass(){
		return acRangeClass; 
	}
	
	public ArrayList<Double> getAircraftPayload(){
		return acPayload; 
	}
	
	public ArrayList<Double> getAircraftLoadVolume(){
		return acLoadVolume; 
	}
	
	public void clearAllAircraft() {
		acID.clear(); 
		acModelID.clear(); 
		acStatus.clear(); 
		acMake.clear(); 
		acModel.clear(); 
		acRange.clear(); 
		acRangeClass.clear(); 
		acPayload.clear(); 
		acLoadVolume.clear(); 
	}
} //end class DBViewAllAircraft
