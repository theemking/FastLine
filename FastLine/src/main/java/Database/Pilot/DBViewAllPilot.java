package Database.Pilot;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBViewAllPilot
 * @Description: This page will be used to connect to the Amazon AWS to View All Pilot
 *  All other pages will be linked to this page
 *  
 */
import Database.DBConnection;
import Database.Client.DBViewAllClient;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBViewAllPilot extends DBConnection{
	
	//variables
	public CallableStatement callable = null; 
	private ArrayList<Integer> pilotID = new ArrayList<>(); 
	private ArrayList<String> firstName = new ArrayList<>(); 
	private ArrayList<String> lastName = new ArrayList<>(); 
	private ArrayList<Date> dob = new ArrayList<>(); 
	private ArrayList<String> employeeNumber = new ArrayList<>(); 
	private ArrayList<Date> dateOfHire = new ArrayList<>(); 
	private ArrayList<Date> dateLeft = new ArrayList<>(); 
	
	
	
	
	 //Default Constructor

	public DBViewAllPilot() {
		try {
		statement = connect.createStatement(); 
		viewAll(); //call the viewAll function; 
		
	}catch(SQLException ex) {
		System.out.println("Database connection failed DBViewAllAircraft"); 
	}
}
	
	public void viewAll() {
		try {
			String method = "{call fastline.dbo.View_All_Pilot}"; 
			callable = connect.prepareCall(method); 
			
			//execute the query
			ResultSet rs = callable.executeQuery(); 
			
			/**
			 * Output from View_All_Pilot:
			 * 1 = PilotID - int
			 * 2 = FirstName - varchar (string)
			 * 3 = LastName - varchar (string)
			 * 4 = DateOfBirth - date
			 * 5 = EmployeeNumber - varchar (string)
			 * 6 = DateOfHire - date 
			 * 7 = DateLeft - date
			 */
			while(rs.next()) {
				//append to arraylists
				pilotID.add(rs.getInt(1)); 
				firstName.add(rs.getString(2)); 
				lastName.add(rs.getString(3)); 
				dob.add(rs.getDate(4));
				employeeNumber.add(rs.getString(5)); 
				dateOfHire.add(rs.getDate(6)); 
				dateLeft.add(rs.getDate(7)); 			
								
			}
			
		}catch (SQLException ex) {
			Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("view all clients could not be completed"); 
		}
	
	}
	
	 //multiple get methods to access the ArrayLists individually
	 
	public ArrayList<Integer> getPilotID(){
		return pilotID; 
	}
	
	public ArrayList<String> getFirstName(){
		return firstName; 
	}
	
	public ArrayList<String> getLastName(){
		return lastName; 
	}
	
	public ArrayList<Date> getDateOfBirth(){
		return dob; 
	}
	
	public ArrayList<String> getEmployeeNumber(){
		return employeeNumber; 
	}
	
	public ArrayList<Date> getDateOfHire(){
		return dateOfHire; 
	}
	
	public ArrayList<Date> getDateLeftCAF(){
		return dateLeft; 
	}
	
	//appends first and last name into an arrayList
	public ArrayList<String> getFullName(){
		ArrayList<String> fullName = new ArrayList<>(); 
		
		for(int i =0; i<firstName.size(); i++) {
			fullName.add(firstName.get(i) + " " + lastName.get(i)); 
		}
		
		return fullName; 		
	}

	 // clearAllPilot - method to clear all the ArrayLists with pilot information

	public void clearAllPilot() {
		pilotID.clear(); 
		firstName.clear(); 
		lastName.clear(); 
		dob.clear(); 
		employeeNumber.clear(); 
		dateOfHire.clear(); 
		dateLeft.clear(); 
	}

}
