package Database;

/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBFinder
 * @Description: This page will be used to find different table IDs in the database
 */
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Database.Client.DBViewAllClient;

//Extending class to the Database connection class
public class DBFinder extends DBConnection {
	public CallableStatement callable =null;
	private ArrayList<String> airportNames=new ArrayList<>();
	private ArrayList<Integer> statusIDs =new ArrayList<>();
	private ArrayList<Integer>airportIDs=new ArrayList<>();
	private ArrayList<String> statusVals = new ArrayList<>();
	
	//Creating default constructor
	public DBFinder() {
		try {
			statement=connect.createStatement();
		}catch(SQLException sq) {
			System.out.println("The attempt to connection to DBViewAllAircraft has Failed");
		}
	}
	/**
	 * findClientID - method for finding a clientID after adding a new client to the database
	 * @return
	 */
	public int findClientID(String name, int type, String phone){
		int id = 0; 
		try {
			String query = "USE[fastline] SELECT clientID FROM fastline.dbo.Clients WHERE "
					+ "ClientName = ? AND ClientTypeID=? AND ClientPhoneNumber=?"; 
			PreparedStatement preparedStatement = connect.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, type);
			preparedStatement.setString(3, phone);
			ResultSet rs = preparedStatement.executeQuery(); 
			while(rs.next()) {
				id = rs.getInt(1); 
			}
			rs.close(); 
		}catch(SQLException ex) {
			Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Could not get client ID information"); 
		}
		return id; 
	}
	
	/**
	 * findAirPorts - view the airportIDs and the names of the airports. 
	 */
	public void findAirports() {
		
		try {
			String query = "USE[fastline] SELECT AirportID, AirportName FROM "
					+ "fastline.dbo.Airports"; 
			callable = connect.prepareCall(query); 
				
			ResultSet rs = callable.executeQuery(); 
			while(rs.next()) {
				airportIDs.add(rs.getInt(1)); 
				airportNames.add(rs.getString(2)); 
			}
			rs.close(); 
		}catch(SQLException ex) {
			Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Could not get client ID information"); 
		}
	}
	
	public ArrayList<String> getAirportNames(){
		return airportNames; 
	}
	public ArrayList<Integer> getAirportIDs(){
		return airportIDs; 
	}
	
	/**
	 * findStatusID - viewAll for the LUStatusID table- will allow user to see actual status values, but enter the 
	 * integer statusID
	 */
	public void findStatusID() {
		try {
			String query = "USE[fastline] SELECT * FROM "
					+ "fastline.dbo.LUShipmentStatus"; 
			callable = connect.prepareCall(query); 
				
			ResultSet rs = callable.executeQuery(); 
			while(rs.next()) {
				statusIDs.add(rs.getInt(1)); 
				statusVals.add(rs.getString(2)); 
			}
			rs.close(); 
		}catch(SQLException ex) {
			Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Could not get status ID information"); 
		}
	}
	public ArrayList<Integer> getStatusIDs(){
		return statusIDs; 
	}
	public ArrayList<String> getStatusVals(){
		return statusVals; 
	}
		
	
	
}
