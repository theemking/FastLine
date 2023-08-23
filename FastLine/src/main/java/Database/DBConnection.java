package Database;

/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBConnection
 * @Description: This page will be used to connect to the Amazon AWS. All other pages will be linked to this page
 * 
 *  * Amazon DB
 * fastlinedb.cmxwsxmzozis.us-east-1.rds.amazonaws.com
 * Account: fastlinecorp
 * database name: fastlineDB
 * user name: fastadmin
 * Password: fastproject10
 * Account
401184766759
KMS key ID
9f109eeb-655b-4547-b35d-1500b1a857d7
 C:/Users/botone/.p2/pool/plugins/org.eclipse.m2e.maven.runtime_3.8.701.20230209-1606/jars/maven-slf4j-provider-3.8.7.jar!/org/slf4j/impl/StaticLoggerBinder.class]
 */

import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.*;

public class DBConnection {

	  final String DRIVER = "com.sql.jdbc.Driver"; 
	  final static String URL="jdbc:sqlserver://fastline.cmxwsxmzozis.us-east-1.rds.amazonaws.com";
	  final static String USER="fastadmin"; 
	  final static String PASSWORD="fastproject10"; 
	  protected Connection connect; 
	  protected Statement statement;
	 


	//For local database connection
/**	
	
	 final String DRIVER = "com.sql.jdbc.Driver"; 
	 final static String URL="jdbc:sqlserver://localhost:1433/fastline"; 
	 final static String USER="root"; 
	 final static String PASSWORD=""; 
	 protected Connection connect; 
	 protected Statement statement;
**/	 

	//Default constructor for connecting the database
	public DBConnection() {
		try {
			System.out.println("Starting the connection");
			connect = DriverManager.getConnection(URL,USER,PASSWORD);
			System.out.println("Establishing a connection to the AWS Database server");
			}catch(SQLException dbx) {
				Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, dbx);
				System.out.println("Could not connect to the Database");
			}
	}
	//closing the connection to the AWS Database
	public void closeConnection() {
		try {
			connect.close();
		}catch(SQLException dbx) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, dbx);
			System.out.println("Database cannot be disconnected at this time");
		}
	}
	

}//end class








