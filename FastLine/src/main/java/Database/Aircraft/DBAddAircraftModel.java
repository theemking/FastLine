package Database.Aircraft;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBAddAircraftModel
 * @Description: This page will be used to connect to the Amazon AWS to add new Aircraft Model.
 *  All other pages will be linked to this page
 * 
 */
// Imports:
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBAddAircraftModel extends DBConnection {
	//Variables
		public CallableStatement callable = null;
		public String aMake;
		public String aModel;
		public String aRange;
		public String aRangeClass;
		public float aPayload;
		public float aVolume;
		
//Default Constructor

public DBAddAircraftModel(String make, String model, String range, String rangeClass,float load, float vol) {
	try {
		this.aMake=make;
		this.aModel=model;
		this.aRange=range;
		this.aRangeClass=rangeClass;
		this.aPayload=load;
		this.aVolume=vol;
		
		String storedP = "{call fastline.dbo.Add_Aircraft_Model}"; 
		callable = connect.prepareCall(storedP);
		insertSQL(aMake,aModel,aRange,aRangeClass,aPayload,aVolume);	
	}
	catch (SQLException ex) {
		Logger.getLogger(DBAddAircraftModel.class.getName()).log(Level.SEVERE, null, ex);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("Problem adding New AirCraft Model"); 
	}
	
}//end constructor
/**
* insertSQL Method 
* Database structure:
* 1 Make
* 2 Model
* 3 Range
* 4 Range CLass
* 5 Payload
* 6 Volume
*
*/
public void insertSQL(String make, String model, String range, String rangeClass,float load, float vol) {
	try {
		String sql = "{call fastline.dbo.Add_Aircraft_Model(?,?,?,?,?,?)}";
		callable=connect.prepareCall(sql);
		callable.setString(1,  make);
		callable.setString(2, model);
		callable.setString(3, range);
		callable.setString(4, rangeClass);
		callable.setFloat(5, load);
		callable.setFloat(6, vol);
		
		//Execute Stored Procedure
		callable.executeQuery();
		
	} catch (SQLException ex) {
        System.out.println("Insert  AirCraft Model Problem !");
    }
	
	
}
}
