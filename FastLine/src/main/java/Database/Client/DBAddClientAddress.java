package Database.Client;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBAddClientAddress
 * @Description: This page will be used to connect to the database and add new Client address.
 *  All other pages will be linked to this page
 * 
 */
//Imports:
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBAddClientAddress extends DBConnection {
	//Declaring Variables
	public CallableStatement callable = null;
	public String clientAdd1;
	public String clientAdd2;
	public String clientCity;
	public String clientState;
	public String clientZip;
	public int clientID;
	
	//Creating Default Constructor
	public DBAddClientAddress(String add1,String add2,String city,String state, String zip, int id) {
		try {
			this.clientAdd1=add1;
			this.clientAdd2=add2;
			this.clientCity=city;
			this.clientState=state;
			this.clientZip=zip;
			this.clientID=id;
			
			statement = connect.createStatement(); 
			insertSQL(clientAdd1,clientAdd2,clientCity,clientState,clientZip,clientID);		
			
		}catch (SQLException ex) {
			Logger.getLogger(DBAddClientAddress.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Problem adding New Client Address"); 
		}	
		
	}//end constructor	
	

	/**
	 * Default Constructor
	 */
	public DBAddClientAddress() {
		//just call this class
	}

/**
 * The method below will insert data from the database into the client address GUI
* insertSQL Method 
* Database structure:
* 1 ClientID int
* 2 ClientAddress1 string
* 3 ClientAddress2 string
* 4 ClientCity string
* 5 ClientState string
* 6 ClientZip int
*
*/
public void insertSQL(String add1,String add2,String city,String state, String zip, int id) {
	try {
		String sql = "{call fastline.dbo.Add_Client_Address(?,?,?,?,?,?)}";
		callable=connect.prepareCall(sql);
		callable.setInt(1,  id);
		callable.setString(2, add1);
		callable.setString(3, add2);
		callable.setString(4, city);
		callable.setString(5, state);
		callable.setString(6, zip);
		//Execute Stored Procedure
		callable.executeQuery();
		callable.close(); 
		
	} catch (SQLException ex) {
        System.out.println("Insert Client Address Problem !");
    }
	
}
		
}
