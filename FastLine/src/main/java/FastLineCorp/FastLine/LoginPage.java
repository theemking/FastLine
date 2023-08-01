package FastLineCorp.FastLine;

import java.util.Arrays;
import java.sql.*;
//import FastLineCorp.FastLine.Database.User
import FastLineCorp.FastLine.Styles;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: LoginPage
 * @Description: This page will be the main GUI control page. All other pages will be linked to this page
 * 
 */
public class LoginPage {
	public boolean logSuccess = false; 

	Styles loginstyle = new Styles();

	//labels for login fields
	static Label lbUsername = new Label("User Name: ");
	static Label lbPassword = new Label("Password: ");

	//login fields
	static TextField tfUsername = new TextField();
	static PasswordField pfPassword = new PasswordField();

	//login buttons
	Button btnLogin = new Button("Login");
	Button btnExit = new Button("Exit");

	//passed borderpane from control page
	BorderPane bPane = new BorderPane();

	//default constructor
	LoginPage(){
		//does nothing just used to call the class
	}

	// getPane - what gets called in control page to view loginPage data 
    // @return 
	public VBox getPane() {
		VBox vlogin = new VBox();
		vlogin.setAlignment(Pos.CENTER);
		vlogin.getChildren().addAll(login());
        return vlogin;
	}

	/**
	 * login - border pane for login information 
	 * @return
	 */
	protected BorderPane login() {
		BorderPane loginBPane = new BorderPane();

        //add title, center, and buttons to clients pane:
        loginBPane.setTop(topNavigation());
        loginBPane.setCenter(centerSection()); //call a method to show db of clients  
        loginBPane.setBottom(bottomSection());

		return loginBPane;
	}
	  /**
	   * topNavigation
	   * This section below with display the a navigation bar that shows the buttons and names for different pages.
	   * 
	   * @return
	   * 
	   */
	  private VBox topNavigation() {
		  VBox vboxt = new VBox();
		  vboxt.setAlignment(Pos.CENTER);
		  vboxt.setMinHeight(100);
		  vboxt.setStyle("-fx-background-color: DARKSEAGREEN");
		  vboxt.setPadding(new Insets(20,0,0,0));
		  Text headertex = new Text("FASTLINE CORP");
		  headertex.setFill(Color.GREEN);
		  headertex.setStrokeWidth(2);
		  headertex.setFont(Font.font("Times New Roman", FontWeight.BOLD,
				  FontPosture.REGULAR, 30));
		  vboxt.getChildren().addAll(headertex);
		//  vboxt.getChildren().add(bottomSection());
		  
		  return vboxt;
	  }
	  /**
	   * This function adds the center section of the login page
	   * @return
	   */
	  private VBox centerSection() {
		  VBox vboxc = new VBox();
		 // vboxc.setSpacing(20);
		  vboxc.setMinHeight(210);
		  vboxc.setStyle(loginstyle.bacgroundWHITE);
		  vboxc.setAlignment(Pos.CENTER);
	        Text title = new Text("Login");
	        Text instruct = new Text("Please Enter Your Login Credentials Below:");
	        //style text
	        title.setFill(Color.DARKBLUE);
	        title.setStrokeWidth(2);
	        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));
	        
			VBox titleBox = new VBox();
	        titleBox.setAlignment(Pos.CENTER);
	        titleBox.setSpacing(10);
	       

	        //add titles to titlebox. 
	       
	        instruct.setFill(Color.BLUE);
	        instruct.setStrokeWidth(2);
	        
	        instruct.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
	      //add input values into a gridpane   
	    	GridPane gPane = new GridPane(); 
	    	gPane.setAlignment(Pos.CENTER);
	    	gPane.setMinSize(700, 100);
	    	gPane.setHgap(10);
	    	gPane.setVgap(4);
	    	gPane.add(lbUsername, 0, 0);
	    	gPane.add(lbPassword, 0, 1);    	
	    	gPane.add(tfUsername, 1, 0);
	    	gPane.add(pfPassword, 1, 1);
		  
	        titleBox.getChildren().addAll(title, instruct);
	        vboxc.getChildren().addAll(titleBox,gPane,loginExit());
	             
		  return vboxc;
	  }
	  /**
	   * This function adds the login and exit button to the login page
	   * @btnLogin
	   * @btnExit
	   * @return
	   */
	  private HBox loginExit() {
		  HBox hboln = new HBox();
		  hboln.setSpacing(40);
		  hboln.setAlignment(Pos.CENTER);
	      //style buttons
	        Arrays.asList(btnLogin, btnExit).forEach((b) -> {
	            b.setStyle(loginstyle.button);
	            b.setMinHeight(30);
	            b.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
	        });

            btnExit.setOnAction(e -> {
        	Platform.exit(); 
        });
	        //Exit button style
	        btnExit.setStyle(loginstyle.redbton);
	        btnExit.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
	        btnExit.setMinHeight(0);
	        hboln.getChildren().addAll(btnLogin, btnExit);	
	        
		  return hboln;
	  }
	
	  /**
	   * bottomSection
	   * This section contain the following information at the bottom of the page
	   * Copyright © 2023 · All Rights Reserved
	   * Program name
	   * Author name
	   */
	  private VBox bottomSection() {
		  VBox vboxb = new VBox();
		  vboxb.setAlignment(Pos.CENTER);
		  vboxb.setMinSize(1000, 150);
		  vboxb.setStyle("-fx-background-color: TEAL");
		  Text toptext = new Text("Copyright © 2023 · All Rights Reserved: " + "FastLine Corp");
		  toptext.setFill(Color.WHITE);
		  Text btomtext = new Text("Designed by: Dony Pierre");
		  toptext.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.REGULAR, 15));
		  btomtext.setFont(Font.font("Times New Roman", FontWeight.NORMAL,FontPosture.REGULAR, 15));
		  btomtext.setFont(Font.font("Times New Roman", FontWeight.NORMAL,FontPosture.REGULAR, 15));
		  btomtext.setFill(Color.WHITE);
		  vboxb.setAlignment(Pos.CENTER);
		  VBox.setMargin(toptext, new Insets(0,0,20,0));
		  vboxb.getChildren().addAll(toptext, btomtext);	  
		  return vboxb;
	  }

	
	/**
	 * returns a boolean if the user successfully logs in
	 * @return
	 */
	public boolean isSuccess() {
		return logSuccess; 
	}
	
	/**
	 * Scene for login page
	 * @return
	 */
	public Scene loginStage() {
		Scene logScene = new Scene(getPane(), 700, 460); 
		return logScene; 
	}
	
	
	//CALL THE BUTTON, USERNAME AND PASSWORD IN control page to change the scene if validated
	public Button buttonLog() {
		btnLogin.setOnAction(e -> {
	    	Platform.exit(); 
			});
		return btnLogin; 
	}
	
	public String getUserName() {
		String username = tfUsername.getText(); 
		return username; 
	}
	
	public String getPassword() {
		String pw = pfPassword.getText();
		return pw; 
	}
}