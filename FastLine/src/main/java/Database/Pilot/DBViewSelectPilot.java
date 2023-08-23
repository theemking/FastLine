package Database.Pilot;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBViewSelectPilot
 * @Description: This page will be used to connect to the Amazon AWS to View Select Pilot
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

public class DBViewSelectPilot extends DBConnection {
	//variables
	public CallableStatement callable = null; 
	private int pilotID; 
	private String pilotFirst; 
	private String pilotLast;
	private Date dob; 
	private String employeeNum; 
	private Date dateOfHire; 
	private Date dateLeft; 
	
	/**
	 * Default Constructor
	 */
	public DBViewSelectPilot() {
		try {
			statement = connect.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBViewAllAircraft"); 
		}
	}

	
	 //viewSelected - method to view a pilot based on the id

	public void viewSelected(int pID) {
		try {
		String method = "{call fastline.dbo.View_Selected_Pilot_By_ID(?)}"; 
		callable = connect.prepareCall(method); 
		callable.setInt(1, pID); // call the flight ID for searching
		
		//execute the query
		ResultSet rs = callable.executeQuery(); 
		
		/**
		 * output from View_Selected_Pilot_By_ID:
		 * 1 - pilotID - int
		 * 2 - FirstName - String
		 * 3 - LastName - String
		 * 4 - DateOfBirth - Date
		 * 5 - EmployeeNumber - String
		 * 6 - DateOfHire - Date
		 * 7 - DateLeft - Date
		 * 
		 */
		while(rs.next()) {
			pilotID = rs.getInt(1); 
			pilotFirst = rs.getString(2); 
			pilotLast = rs.getString(3); 
			dob = rs.getDate(4); 
			employeeNum = rs.getString(5); 
			dateOfHire = rs.getDate(6); 
			dateLeft = rs.getDate(7); 		 
			}
		
		}catch (SQLException ex) {
			Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("view all clients could not be completed"); 
		}
	}
	
	
	  //viewSelected - override method to view a pilot based on first name and last name of employee

	public void viewSelected(String firstName, String lastName) {
		try {
			String method = "{call fastline.dbo.View_Selected_Pilot_By_Name(?,?)}"; 
			callable = connect.prepareCall(method); 
			callable.setString(1, firstName); // call the flight ID for searching
			callable.setString(2, lastName);
			//execute the query
			ResultSet rs = callable.executeQuery(); 
			
			/**
			 * output from View_Selected_Pilot_By_Name:
			 * 1 - pilotID - int
			 * 2 - FirstName - String
			 * 3 - LastName - String
			 * 4 - DateOfBirth - Date
			 * 5 - EmployeeNumber - String
			 * 6 - DateOfHire - Date
			 * 7 - DateLeft - Date
			 */
			while(rs.next()) {
				pilotID = rs.getInt(1); 
				pilotFirst = rs.getString(2); 
				pilotLast = rs.getString(3); 
				dob = rs.getDate(4); 
				employeeNum = rs.getString(5); 
				dateOfHire = rs.getDate(6); 
				dateLeft = rs.getDate(7);				 
			}
			
			}catch (SQLException ex) {
				Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("view all clients could not be completed"); 
			}
	}
	
	/**
	 * multiple getter methods to access each variable independently
	 */
	public int getPilotID() {
		return pilotID; 
	}
	public String getPilotFirstName() {
		return pilotFirst; 
	}
	public String getPilotLastName() {
		return pilotLast; 
	}
	public Date getDateOfBirth() {
		return dob; 
	}
	public String getEmployeeNum() {
		return employeeNum; 
	}
	public Date getDateOfHire() {
		return dateOfHire; 
	}
	public Date getDateLeftCAF() {
		return dateLeft; 
	}
} //end of class
