package Database.User;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBValidateUserPassword
 * @Description: This page will be used to connect to Validate User Password
 *  All other pages will be linked to this page
 * 
 */
import Database.DBConnection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class DBValidateUserPassword extends DBConnection {
	public CallableStatement callable = null;
	private String userName;
	private String userPassword;
	private int answer;
	
	public DBValidateUserPassword() {
		try {
			statement = connect.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteUser"); 
		}
	}
	
	public boolean validateUser(String uName, String uPass) {
		
		try {
			String method = "{call fastline.dbo.Validate_User_Password(?,?,?)}"; 
			userName = uName;
			userPassword = uPass;
			callable = connect.prepareCall(method); 
			callable.setString(1, userName);	
			callable.setString(2, userPassword);
			callable.registerOutParameter(3, Types.NUMERIC);
			callable.execute();
			answer = callable.getInt(3);
			
			if(answer == 1) {
				return true;
			}else {
				return false;
			}
						
		}catch (SQLException ex) {
			Logger.getLogger(DBValidateUserPassword.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("validate user could not be completed"); 
			return false;
		}
	}
}
