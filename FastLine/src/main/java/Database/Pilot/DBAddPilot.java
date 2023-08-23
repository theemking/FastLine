package Database.Pilot;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBAddPilot
 * @Description: This page will be used to connect to the Amazon AWS to Add Pilot
 *  All other pages will be linked to this page
 *   
 */

// Imports:
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBAddPilot extends DBConnection{
	//Variables
	public CallableStatement callable = null;
	public String pFirstName;
	public String pLastName;
	public Date pDOB;
	public String pNumber;
	public Date pHireDate;

 //Default Constructor

public DBAddPilot(String fName, String lName, Date dob, String eNumber,Date hire) {
			try {
				this.pFirstName=fName;
				this.pLastName=lName;
				this.pDOB=dob;
				this.pNumber=eNumber;
				this.pHireDate=hire;
				
				//String storedP = "{call CAFDB.dbo.Add_Pilot}"; 
				//callable = connection.prepareCall(storedP);
				statement = connect.createStatement();
				insertSQL(pFirstName, pLastName, pDOB, pNumber, pHireDate);			
			}
			catch (SQLException ex) {
				Logger.getLogger(DBAddPilot.class.getName()).log(Level.SEVERE, null, ex);
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("Problem adding New Pilot"); 
			}
			
}//End Default Constructor
	
/**
* Database structure:
 * 1 PilotID int
* 2 first name string
* 3 last name string
* 4 DOB date
* 5 employee number string
* 6 date of hire date
* 7 date left date
*
*/
public void insertSQL(String fName, String lName, Date dob, String eNumber,Date hire){
		try {
			String sql = "{call fastline.dbo.Add_Pilot(?,?,?,?,?)}";
			callable=connect.prepareCall(sql);
			callable.setString(1,  fName);
			callable.setString(2, lName);
			callable.setDate(3, dob);
			callable.setString(4, eNumber);
			callable.setDate(5,hire);
			
			//Execute Stored Procedure
			callable.executeQuery();
			
		} catch (SQLException ex) {
	        System.out.println("Insert Client Problem !");
	    }
		
	}//end insertSQL


}
