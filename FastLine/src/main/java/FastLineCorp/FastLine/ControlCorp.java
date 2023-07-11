package FastLineCorp.FastLine;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: ControlCorp
 * @Description: This page will be the main GUI control page. All other pages will be linked to this page
 * 
 */

import javafx.stage.Stage;
import javafx.scene.Parent; 
import java.util.Arrays;
import javafx.fxml.FXMLLoader; 
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ControlCorp extends Application{
	//declaring border
	BorderPane bPane=new BorderPane();
	
	
	//Buttons
	static Button btonhomepage=new Button("HOMEPAGE");
	static Button btonclients=new Button("CLIENTS");
	static Button btonflights= new Button("FLIGHTS");
	static Button btonpilot = new Button("PILOT");
	static Button btonshipment = new Button("SHIPMENT");
	static Button btonairplane = new Button("AIRPLANE");
	static Button btoncontact = new Button("CONTACT");
	static Button btonexit = new Button ("EXIT");
	
	static TextArea texReaOne = new TextArea();
	
	//Styles for the control page
	Styles controlstyle = new Styles();
	//StyleCSS styleCSS = new StyleCSS();
	
	//Classes
	HomePage homepage = new HomePage(bPane);
	ClientsPage clientsp = new ClientsPage(bPane);
	FlightsPage flightp = new FlightsPage(bPane);
	PilotPage pilot = new PilotPage(bPane);
	ShipmentPage shipmentp = new ShipmentPage(bPane);
	AirPlanePage airplanep = new AirPlanePage(bPane);
	ContactPage contactp = new ContactPage(bPane);
	
	
	/**
	 * This section will be the main page GUI. There will be buttons on this page to access the other pages
	 * 
	 * @param
	 * 
	 */

  @Override
  public void start(Stage primaryStage) {
  //Setting up top navigation bar
   bPane.setTop(topNavigation());
   bPane.setCenter(homepage.getPane());
   bPane.setBottom(bottomSection());
   
   //Buttons that users will be able to click on to navigate to that other pages
  // btonhomepage.set
   btonhomepage.setOnAction(e ->{bPane.setCenter(homepage.getPane()); });
   btonclients.setOnAction(e->{bPane.setCenter(clientsp.getPane());});
   btonflights.setOnAction(e->{bPane.setCenter(flightp.getPane());});
   btonpilot.setOnAction(e->{bPane.setCenter(pilot.getPane());});
   btonshipment.setOnAction(e->{bPane.setCenter(shipmentp.getPane());});
   btonairplane.setOnAction(e->{bPane.setCenter(airplanep.getPane());});
   btoncontact.setOnAction(e->{bPane.setCenter(contactp.getPane());});
   btonexit.setOnAction(e->{Platform.exit();});
   
   //setting up the login page to logon to the program
   LoginPage login = new LoginPage();
   
	  
  primaryStage.setTitle("FastLine Corp");
  primaryStage.setScene(login.loginStage());
  primaryStage.show();
  
  System.out.println("This is the control page");
  //Scene scene = new Scene(bPane, 1000, 700);
  //This code causes the app to not run
  //scene.getStylesheets().add(getClass().getResource("StyleCSS.css").toExternalForm());
 // String cssFile = this.getClass().getResource("StyleCSS.css").toExternalForm();
  //scene.getStylesheets().add(cssFile);
  //primaryStage.setScene(scene);
  primaryStage.show();
  
   /**
    * This button will call from the login page to activate the action button to switch the scene in order
    * for the login page to be shown.
    */
  login.buttonLog().setOnAction(e->{
	  login.getUserName(); login.getPassword();{
		  System.out.println("Loading the Login Page");
		  Scene scen =new Scene(bPane, 1000, 700);
		  primaryStage.setScene(scen);
		  //LoginPage.tfUsername.requestFocus();
	  }
  });
   
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
	  vboxt.setMinHeight(90);
	  vboxt.setStyle("-fx-background-color: DARKSEAGREEN");
	  vboxt.setPadding(new Insets(20,0,0,0));
	  Text headertex = new Text("FASTLINE CORP");
	  headertex.setFill(Color.GREEN);
	  headertex.setStrokeWidth(2);
	  headertex.setFont(Font.font("Times New Roman", FontWeight.BOLD,
			  FontPosture.REGULAR, 30));
	  vboxt.getChildren().addAll(headertex);
	  vboxt.getChildren().add(getNavButtons());
	  
	  return vboxt;
  }
  /**
   * getNavButtons
   * This section below with display the a navigation buttons that users can click on to go to different pages.
   * 
   */
  private HBox getNavButtons() {
	  HBox hboxnv = new HBox();
	  hboxnv.setStyle("-fx-background-color: DARKSEAGREEN");
	  hboxnv.setSpacing(20);;
	  hboxnv.setPadding(new Insets(10,10,10,10));
	  hboxnv.setPrefSize(100, 60);
	  hboxnv.setAlignment(Pos.CENTER);
	  //Adding color, height and fonts to all buttons.
	  Arrays.asList(btonhomepage,btonclients,btonflights,btonpilot,btonshipment,
			  btonairplane,btoncontact).stream().map((btonColor)->{
				  btonColor.setStyle(controlstyle.buttonNAV);
				  return btonColor;				  
			  }).map((btonColor)->{
				  btonColor.setMinHeight(30);
				return btonColor;
			  }).forEachOrdered((btonColor)->{
				  btonColor.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
			  });
	  //Setting up exit button differently because of different functions
	  btonexit.setStyle(controlstyle.buttonNAV);
	  btonexit.setMinHeight(30);
	  btonexit.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
	  hboxnv.getChildren().addAll(btonhomepage,btonclients,btonflights,btonpilot,btonshipment,
			  btonairplane,btoncontact, btonexit);
	  return hboxnv;
	  
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

}
 
