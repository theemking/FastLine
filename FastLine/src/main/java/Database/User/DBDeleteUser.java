package Database.User;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBDeleteUser
 * @Description: This page will be used to connect to Delete User
 *  All other pages will be linked to this page
 * 
 */
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
 

public class DBDeleteUser extends DBConnection{
	
	public CallableStatement callable = null;
	private int userID;
	
	public DBDeleteUser() {
		try {
			statement = connect.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteUser"); 
		}
	}
	
	public void deleteUser(int uID) {
		try {
			String method = "{call fastline.dbo.Delete_User(?)}"; 
			userID = uID;
			callable = connect.prepareCall(method); 
			callable.setInt(1, userID);			
			callable.executeQuery();
						
		}catch (SQLException ex) {
			Logger.getLogger(DBDeleteUser.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete user could not be completed"); 
		}
	}

}
