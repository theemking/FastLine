package Database.Flight;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBAddAirport
 * @Description: This page will be used to connect to the Amazon AWS to Update Flight
 *  All other pages will be linked to this page
 */

import Database.DBConnection;
import Database.Client.DBViewAllClient;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Time;
import java.time.LocalDateTime;

public class DBViewAllFlights extends DBConnection{
	//global variables
	private CallableStatement callable = null; 
	private ArrayList<Integer> id = new ArrayList<>(); //flightID
	private ArrayList<Integer> aircraftID = new ArrayList<>(); //aircraftID
	private ArrayList<Integer> pilotID = new ArrayList<>(); 
	private ArrayList<String> start = new ArrayList<>(); //StartAirport
	private ArrayList<String> end = new ArrayList<>(); //EndAirport
	private ArrayList<String> startTime = new ArrayList<>(); //start time
	private ArrayList<String> endTime = new ArrayList<>(); // end time
	private ArrayList<String> pilotName = new ArrayList<>(); //first name and last name together
	
	
	
	 //default constructor - connection to db
	 
	public DBViewAllFlights() {
		try {
		statement = connect.createStatement(); 
		viewAll(); // call the viewAll function. 
		
	} catch(SQLException ex) {
		System.out.println("Database connection failed DBViewAllFlights"); 
	}}
	
	
	/**
	 * viewAll- method that calls the View_All_Flights stored procedure to fill ArrayLists. 
	 */
	public void viewAll() {
		try {
			String method ="{call fastline.dbo.View_All_Flight}"; 
			callable = connect.prepareCall(method); 
			
			//execute the query
			ResultSet rs = callable.executeQuery(); 
			/**
			 * 1 = flightID - int
			 * 2 = AircraftID - int
			 * 3 = PilotID - int
			 * 4 = StartAirport - int
			 * 5 = EndAirport - int
			 * 6 = FlightStartTime - smalldatetime
			 * 7 = FlightEndTime - smalldatetime
			 *** there are more that print out from this SP. 
			 */
			
			while(rs.next()) {
				//fill arraylists
				id.add(rs.getInt(1)); 
				aircraftID.add(rs.getInt(2)); 
				pilotID.add(rs.getInt(3)); 
				start.add(rs.getString(20)); 
				end.add(rs.getString(25)); 
				startTime.add(rs.getString(6)); 
				endTime.add(rs.getString(7)); 
				pilotName.add(rs.getString(12) + " " + rs.getString(13)); //first and last name
			}
			
		}catch (SQLException ex) {
			Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("view all flights could not be completed"); 
		}
		
	}
	
	/**
	 * multiple getter methods to get each variable independently 
	 * @return
	 */
	public ArrayList<Integer> getFlightID(){
		return id; 
	}
	
	public ArrayList<Integer> getAircraftID(){
		return aircraftID; 
	}
	public ArrayList<Integer> getPilotID(){
		return pilotID; 
	}
	public ArrayList<String> getStartLocation(){
		return start; 
	}
	public ArrayList<String> getEndLocation(){
		return end; 
	}
	public ArrayList<String> getStartTime(){
		return startTime; 
	}
	public ArrayList<String> getEndTime(){
		return endTime; 
	}
	public ArrayList<String> getPilotName(){
		return pilotName; 
	}
	
	 // clearFlights - clears all the ArrayLists with flight information 

	public void clearFlights() {
		id.clear(); 
		aircraftID.clear(); 
		pilotID.clear(); 
		start.clear(); 
		end.clear(); 
		startTime.clear(); 
		endTime.clear(); 
	}

}
