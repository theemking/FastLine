package FastLineCorp.FastLine;

import javafx.scene.control.Alert;
import java.util.Scanner;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 20, 2023
 * @Class: Validation
 * @Description: This class will be used to validate data and field throughout the program 
 */
class ErrorMessage<String>{
	public void setError(String header, String content) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText(header.toString());
		alert.setContentText(content.toString());
		alert.showAndWait();
	}
}
public class Validation {

	//setup erro message
	ErrorMessage<String> error= new ErrorMessage();
	/**
	 * Default Constructor
	 */
	public Validation() {}
	
	//Email validation
	public String validateEmail(String email, String header, String subHeader) {
		String valOne="^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if(email.matches(valOne)) {
			return email;
		}else {
			error.setError(header, subHeader);
			System.out.print("An Error has occured");
		}
		return "";
	}
	//validating sender's first name
	public void senderFirstName() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("First Name Warning");
		alert.setHeaderText("Sender first Name is Missing");
		alert.setContentText("Please enter the first name");
		alert.showAndWait();
	}
	//validating sender's last name
	public void senderLastName() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Last Name Warning");
		alert.setHeaderText("Sender Last Name is Missing");
		alert.setContentText("Please enter the last name");
		alert.showAndWait();
	}
	//validate subject
	public void missingSubject() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning No Subject");
		alert.setHeaderText("You did not enter a subject");
		alert.setContentText("You must enter a subject");
		alert.showAndWait();
	}
		//validate message
	   public void noMessage() {
		   Alert alert = new Alert(Alert.AlertType.WARNING);
		   alert.setTitle("Warning");
		   alert.setHeaderText("No Message Entered");
		   alert.setContentText("Please enter a message");
		   alert.showAndWait();
	   }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
