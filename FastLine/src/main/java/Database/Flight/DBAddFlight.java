package Database.Flight;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBAddAirport
 * @Description: This page will be used to connect to the Amazon AWS to Add Flight
 *  All other pages will be linked to this page
 * 
 */

// Imports:
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DBAddFlight extends DBConnection {
	//Variables
		public CallableStatement callable = null;
		public int aircraftID;
		public int pilotID;
		public int startAirportID;
		public int endAirportID;
		public String start;
		public String end;
	
public DBAddFlight(int aID,int pID,int startAirport, int endAirport, String start, String end ) {
	try {
		this.aircraftID=aID;
		this.pilotID=pID;
		this.startAirportID=startAirport;
		this.endAirportID=endAirport;
		this.start=start;
		this.end=end;
		
		//String storedP = "{call CAFDB.dbo.Add_Flight}"; 
		//callable = connection.prepareCall(storedP);
		statement = connect.createStatement(); 
		insertSQL(aircraftID,pilotID,startAirportID,endAirportID, start,end);		
	}
	catch (SQLException ex) {
		Logger.getLogger(DBAddFlight.class.getName()).log(Level.SEVERE, null, ex);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("Problem adding New Client"); 
	}
	
}//end constructor

/**
* Database structure:
 * 1 AircraftID int
 * 2 PilotID int
 * 3 StartAirport int
 * 4 EndAirport int
 * 5 FlightStartTime smalldatetime
 * 6 FlightEndTime smalldatetime
* 
*
*/
public void insertSQL(int aID,int pID,int startAirport, int endAirport, String start, String end ) {
	try {
		String sql = "{call fastline.dbo.Add_Flight(?,?,?,?,?,?)}";
		callable=connect.prepareCall(sql);
		callable.setInt(1,  aID);
		callable.setInt(2,  pID);
		callable.setInt(3,  startAirport);
		callable.setInt(4,  endAirport);
		callable.setString(5, start);
		callable.setString(6, end);
			
		//Execute Stored Procedure
		callable.executeQuery();
		
	} catch (SQLException ex) {
		ex.printStackTrace(); 
        System.out.println("Insert Flight Problem !");
    }
	
	
}
}
