package Database.User;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBAddUser
 * @Description: This page will be used to connect to the Ad dUser
 *  All other pages will be linked to this page
 *  
 */

//Imports:
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBAddUser extends DBConnection{
	//Variables
	public CallableStatement callable = null;
	public String username;
	public String password;
	
	//Default Constructor

public DBAddUser(String user,String pass) {
	try {
		this.username=user;
		this.password=pass;
		
		String storedP = "{call fastline.dbo.Add_Aircraft}"; 
		callable = connect.prepareCall(storedP);
		insertSQL(username, password);			
	}
	catch (SQLException ex) {
		Logger.getLogger(DBAddUser.class.getName()).log(Level.SEVERE, null, ex);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("Problem adding New User"); 
	}
	
	
}//end Constructor
/**
* insertSQL Method 
* Database structure:
* 1 UserName string
* 2 Password string
*  
*/
public void insertSQL(String user, String pass) {
	try {
		String sql = "{call fastline.dbo.Add_User(?,?)}";
		callable=connect.prepareCall(sql);
		callable.setString(1,  user);
		callable.setString(2,  pass);	
		
		
		//Execute Stored Procedure
		callable.executeQuery();
		
	} catch (SQLException ex) {
        System.out.println("Insert User Problem !");
    }
	
	
}
}
