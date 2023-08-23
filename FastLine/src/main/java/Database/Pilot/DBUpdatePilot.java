package Database.Pilot;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBDeletePilot
 * @Description: This page will be used to connect to the Amazon AWS to Update Pilot
 *  All other pages will be linked to this page
 *   
 */

//Imports:
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUpdatePilot extends DBConnection {
	//Variables
		public CallableStatement callable = null;
		public String fName;
		public String lName;
		public Date dob;
		public String eNum;
		public Date stDate;
		public Date eDate;
		public int pID;

/**
 * DataBase Structure:
 * 1. PilotID int
 * 2. FirstName String
 * 3. LastName String
 * 4. DateOfBirth Date
 * 5. EmployeeNumber String
 * 6. DateOfHire Date
 * 7. DateLeft Date
*/
		//Default Constructor
public DBUpdatePilot() {
			
		//blank
	}//end default constructor	
/** 
* updatePilot
* @param pID
* @param fName
* @param lName
* @param dob
* @param empNum
* @param pStartDate
* @param pEndDate
*/
public void updatePilot(int pID, String fName, String lName,Date dob, String eNum, Date sDate,Date eDate) {
    try {
    	String storedP = "{call fastline.dbo.Update_Pilot(?,?,?,?,?,?,?)}"; 
        callable = connect.prepareCall(storedP);
        callable.setInt(1, pID);
        callable.setString(2,fName);
        callable.setString(3, lName);
        callable.setDate(4,dob);
        callable.setString(5, eNum);
        callable.setDate(6, sDate);
        callable.setDate(7, eDate);

        ResultSet rs = callable.executeQuery(); 

    } catch (SQLException ex) {
        System.out.println("Update Pilot Problem!");
    }
}//end updatePilot

public String getfName() {
	return fName;
}
public String getlName() {
	return lName;
}
public Date getDob() {
	return dob;
}
public String geteNum() {
	return eNum;
}
public Date getStDate() {
	return stDate;
}
public Date geteDate() {
	return eDate;
}
public int getpID() {
	return pID;
}

}
