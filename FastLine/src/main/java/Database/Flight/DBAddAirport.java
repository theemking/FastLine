package Database.Flight;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBAddAirport
 * @Description: This page will be used to connect to the Amazon AWS to Add Airport
 *  All other pages will be linked to this page
 * 
 */
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBAddAirport extends DBConnection {
	//Variables
		public CallableStatement callable = null;
		public String portName;
		public String portLocation;
		public Boolean hubBit;
		public float portDistance;
		
	// Constructor

public DBAddAirport(String name, String location, Boolean hub, float distance) {
	try {
		this.portName=name;
		this.portLocation=location;
		this.hubBit=hub;
		this.portDistance=distance;
		String storedP = "{call fastline.dbo.Add_Airport}"; 
		callable = connect.prepareCall(storedP);
		
	}
	catch (SQLException ex) {
		Logger.getLogger(DBAddAirport.class.getName()).log(Level.SEVERE, null, ex);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("Problem adding New AirPort"); 
	}
}//end DBAddAirport
/**
* Database structure:
* 1. AirportID int
* 2. AirportName string
* 3. AirportLocation string
* 4. AirportHub bit
* 5. AirportDistance float 
*/
public void insertSQL(String name, String location, Boolean hub, float distance) {
	try {
		String sql = "{call fastline.dbo.Add_Pilot(?,?,?,?)}";
		callable=connect.prepareCall(sql);
		callable.setString(1,  name);
		callable.setString(2, location);
		callable.setBoolean(3, hub);
		callable.setFloat(4, distance);
		
		//Execute Stored Procedure
		callable.executeQuery();
		
	} catch (SQLException ex) {
        System.out.println("Insert AirPort Problem !");
    }
	
	
}//end insertSQL
}
