package FastLineCorp.FastLine;

import javafx.scene.control.Alert;

import java.sql.Date;
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
	
	   
	   //String checker
	   public Boolean isString(String str) {//added \\s for spaces
		   return ( 
		           (str != " ") 
		            && (str.matches("^[a-zA-Z\\s]*$"))); 
		    
		  
		}
	   //Address checker
	   public Boolean isAddress(String str) {// range a-zA-Z and 0-9 with \\s for spaces
		   return (
		           (str.matches("^[a-zA-Z0-9\\s]*$"))); 
		    
		  
		}
	   //int checker
	   public int intChecker(String num, String head, String cont) {
		 
		   int intNumber = 0;
		  
		   try {
			   intNumber = Integer.parseInt(num);
		     
		   } catch (NumberFormatException ex) {
		       //Error not number
			   error.setError(head, cont);
			   System.out.print("int error");
		   }
		return intNumber;
		   
	   }
	   // float Checker
	   public float floatChecker(String num, String head, String cont) {
		   
		   float floatNumber = 0;		  
		   try {
			   floatNumber = Float.parseFloat(num);
		     
		   } catch (NumberFormatException ex) {
		       //Error not float
			   error.setError(head, cont);
			   System.out.print("Float error");
		   }
		return floatNumber;
		   
	   }
	   // float boolean used to check float for if statement
	   public boolean floatChecker(String num) {
		   float  floatNumber;
		   try { 
			  floatNumber = Float.parseFloat(num);	 
			  return true;
		   } catch (NumberFormatException ex){
			  return false;
		   }
		   
	   }
	   //phone number
	   public String checkPhoneNumber(String phoneNumber,String head, String cont) {
			//number format
			if (phoneNumber.matches("\\d{10}")) { 
				return phoneNumber;
			}
			// -, . or spaces format
			else if(phoneNumber.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
				return phoneNumber;				
			} 
			//3 to 5 extension format
			else if(phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) {
				return phoneNumber;				
			} 
			//area code in brackets format
			else if(phoneNumber.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) { 
				return phoneNumber;
			}
			//doesn't match
			else {
				  error.setError(head, cont);
				   System.out.print("Error in phone number");
			return "";	
			}
			
		}
	   //zip code 
	   public String zipCodeUS( String zip,String head,String cont ) {
		      if (zip.matches( "\\d{5}" )) {
		    	 return zip;		    	  
		      }else {
		    	  error.setError(head, cont);
				   System.out.print("zip error");
		    	  return "";
		      }
			
		   }	   
	
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
	
	   //string length
	   public int stringLength(String s) {
		   
		   int length=s.length();
		return length;
	   }
	   //compare Date date1 is after date2
	   public boolean afterDate(Date date1, Date date2)  {
		   if (date1.compareTo(date2)>0) {
			   return true;
		   }else {
			   
			   	return false;
		   }
		   
		   
	   }
	 //compare Date date 1 is before date 2
	   public boolean beforeDate(Date date1, Date date2)  {
		   if (date1.compareTo(date2)<0) {
			   return true;
		   }else {
			   
			   	return false;
		   }
		   
		   
	   }
	   //compare Date date1 is equal to date2
	  public boolean datesEqual(Date date1, Date date2)  {
		   if (date1.compareTo(date2)==0) {
			   return true;
		   }else {
			   
			   	return false;
		   }
		   
		   
	   }
	  //compare Stirng date1 is after date2
	   public boolean afterDateString(String date1, String date2)  {
		   if (date1.compareTo(date2)>0) {
			   return true;
		   }else {
			   
			   	return false;
		   }
		   	
	   }
}
