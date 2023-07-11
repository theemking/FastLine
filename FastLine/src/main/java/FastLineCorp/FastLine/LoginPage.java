package FastLineCorp.FastLine;

import java.util.Arrays;

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
		vlogin.getChildren().addAll(login());
        return vlogin;
	}

	/**
	 * login - border pane for login information 
	 * @return
	 */
	protected BorderPane login() {
		BorderPane loginBPane = new BorderPane();

		VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setSpacing(10);
        Text title = new Text("Login");
        Text instruct = new Text("Please Enter Your Login Credentials Below:");
        //style text
        title.setFill(Color.DARKBLUE);
        title.setStrokeWidth(2);
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));

        instruct.setFill(Color.BLUE);
        instruct.setStrokeWidth(2);
        instruct.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
        //add titles to titlebox. 
        titleBox.getChildren().addAll(title, instruct);

      //add input values into a gridpane
    	GridPane grid = new GridPane(); 
    	grid.setAlignment(Pos.CENTER);
    	grid.setHgap(10);
    	grid.setVgap(4);
    	grid.add(lbUsername, 0, 0);
    	grid.add(lbPassword, 0, 1);    	
    	grid.add(tfUsername, 1, 0);
    	grid.add(pfPassword, 1, 1);

        //create button HBox:
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20,0,0,0));
        buttonBox.setSpacing(20);

      //style buttons
        Arrays.asList(btnLogin, btnExit).forEach((b) -> {
            b.setStyle(loginstyle.button);
            b.setMinHeight(30);
            b.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
        });
        //Exit button style
        btnExit.setStyle(loginstyle.redbton);
        btnExit.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
        btnExit.setMinHeight(0);

        //add buttons to button HBox
        buttonBox.getChildren().addAll(btnLogin, btnExit);

        //add title, center, and buttons to clients pane:
        loginBPane.setTop(titleBox);
        loginBPane.setCenter(grid); //call a method to show db of clients  
        loginBPane.setBottom(buttonBox);

            btnExit.setOnAction(e -> {
        	Platform.exit(); 
        });

		return loginBPane;
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
		Scene logScene = new Scene(getPane(), 700, 400); 
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