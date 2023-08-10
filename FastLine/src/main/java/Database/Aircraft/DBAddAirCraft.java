package Database.Aircraft;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: DBAddAirCraft
 * @Description: This page will be used to connect to the Amazon AWS to add new airplane.
 *  All other pages will be linked to this page
 * 
 */
import Database.DBConnection;
import Database.Client.DBViewAllClient;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBAddAirCraft extends DBConnection {
	//declaring veriables
	public CallableStatement callab = null;
	public int model;
	private ArrayList<Integer> acMID = new ArrayList<>();
	private ArrayList<String> acModel = new ArrayList<>();
	private ArrayList<String> acMake = new ArrayList<>();
	
	//creating default constructor
	public DBAddAirCraft() {
		findMakeModel();
	}
	public void insertSQL(int modelID) {
		try {
			String sql = "{call fastline.dbo.Add_AirCraft(?)}";
			callab=connect.prepareCall(sql);
			callab.setInt(1,  modelID);	
			
			//Execute Stored Procedure
			callab.executeQuery();
			
		} catch (SQLException ex) {
	        System.out.println("Insert AirCraft Problem !");
	    }
		
		
	}

	/**
	 * findMakeModelList - method for getting a list of unique make and model of aircraft, to be used for the AddAircraft functionality
	 */
	public void findMakeModel() {
		try {
		String query = "USE[fastline] SELECT ACModelID, ACMake, ACModel FROM FastLine.dbo.AircraftModels"; 
		callab = connect.prepareCall(query); 
		
		ResultSet rs = callab.executeQuery(); 
		while(rs.next()) {
			acMID.add(rs.getInt(1)); 
			acModel.add(rs.getString(2)); 
			acMake.add(rs.getString(3)); 
		}
		} catch(SQLException ex) {
			Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Could not get client ID information"); 
		}
	}

	public ArrayList<Integer> getModelID(){
		return acMID; 
	}
	public ArrayList<String> getMake(){
		return acMake; 
	}
	public ArrayList<String> getModel(){
		return acModel; 
	}	
	
}
