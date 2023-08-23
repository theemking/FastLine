package Database.Flight;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBViewSelectFlight
 * @Description: This page will be used to connect to the Amazon AWS to View Select Flight
 *  All other pages will be linked to this page
 *   
 */

import Database.DBConnection;
import Database.Client.DBViewAllClient;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBViewSelectFlight extends DBConnection {
	//variables
	public CallableStatement callable = null; 
	private int flightID; 
	private int aircraftID; 
	private int pilotID; 
	private int startAirport; 
	private int endAirport; 
	private String flightStartTime; 
	private String flightEndTime; 
	private String pilotName; 
	private String startName; 
	private String startLoc; 
	private String endName; 
	private String endLoc; 
	private String hubDist; 
	
	
/**
 * Default Constructor
 */
	public DBViewSelectFlight() {
		try {
			statement = connect.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBViewAllAircraft"); 
		}
	}
	
	public void viewSelected(int fID) {
		try {
			String method = "{call fastline.dbo.View_Selected_Flight(?)}"; 
			callable = connect.prepareCall(method); 
			callable.setInt(1, fID); // call the flight ID for searching
			
			//execute the query
			ResultSet rs = callable.executeQuery(); 
			
			/**
			 * output from View_Selected_Flight
			 * 1 = flightID - int
			 * 2 = AircraftID - int
			 * 3 = PilotID - int
			 * 4 = StartAirport - int
			 * 5 = EndAirport - int
			 * 6 = FlightStartTime - smalldatetime
			 * 7 = FlightEndTime - smalldatetime
			 * 8 = AircraftID
			 * 9 = ACModelID
			 * 10 = AircraftStatusID
			 * 11 = PilotID
			 * 12 = First Name
			 * 13 = Last Name
			 * 14 = DateOfBirth
			 * 15 = EmployeeNumber
			 * 16 = Date of Hire
			 * 17 = DateLeft
			 * 18 = AirportID
			 * 19 = Airport Name (start)
			 * 20 = Airport Location (start)
			 * 21 = Airport Hub
			 * 23 = Airport Distance from hub
			 * 24 = Airport Name (end)
			 * 25 = Airport Location (end)
			 * 26 = Airport Hub 
			 * 27 = Airport Distance From Hub
			 */
			while(rs.next()) {
				flightID = rs.getInt(1); 
				aircraftID = rs.getInt(2); 
				pilotID = rs.getInt(3); 
				startAirport = rs.getInt(4); 
				endAirport = rs.getInt(5); 
				flightStartTime = rs.getString(6);
				flightEndTime = rs.getString(7); 
				pilotName = rs.getString(13) + ", " + rs.getString(12); 
				startName = rs.getString(19); 
				startLoc = rs.getString(20); 
				endName = rs.getString(24); 
				endLoc = rs.getString(25); 
				hubDist = rs.getString(27); 
				 
			}
			
			}catch (SQLException ex) {
				Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("view all clients could not be completed"); 
			}	
	}
	
	
	 //multiple getter methods to get each variable independently. 
	 
	public int getFlightID() {
		return flightID; 
	}
	public int getAircraftID() {
		return aircraftID; 
	}
	public int getPilotID() {
		return pilotID; 
	}
	public int getStartAirport() {
		return startAirport; 
	}
	public int getEndAirport() {
		return endAirport; 
	}
	public String getStartTime() {
		return flightStartTime; 
	}
	public String getEndTime() {
		return flightEndTime; 
	}
	public String getPilotName() {
		return pilotName; 
	}
	public String getStartName() {
		return startName; 
	}
	public String getStartLoc() {
		return startLoc; 
	}
	public String getEndName() {
		return endName; 
	}
	public String getEndLoc() {
		return endLoc; 
	}
	public String getHubDist() {
		return hubDist; 
	}
}
