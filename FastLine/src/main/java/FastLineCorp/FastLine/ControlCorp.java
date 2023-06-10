package FastLineCorp.FastLine;
/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Description: This page will be the main GUI control page. All other pages will be linked to this page
 */

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
	static Button btonmodel = new Button ("MODEL");
	static Button btoncontact = new Button("CONTACT");
	static Button btonexit = new Button ("EXIT");
	
	static TextArea texReaOne = new TextArea();
	
	//Styles for the control page
	Styles controlstyle = new Styles();
	
	//Classes
	//HomePage homepage = new HomePage(bPane);
	
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
	  
  primaryStage.setTitle("FastLine Corp");
 // primaryStage.setScen()
  System.out.println("This is the control page");
  Scene scene = new Scene(bPane, 1000, 700);
  primaryStage.setScene(scene);
  primaryStage.show();
  }
  /**
   * topNavigation
   * This code below with display the a navigation bar that shows the buttons and names for different pages.
   * 
   * @return
   * 
   */
  private VBox topNavigation() {
	  VBox vboxt = new VBox();
	  vboxt.setAlignment(Pos.CENTER);
	  vboxt.setMinHeight(90);
	  
	  
	  return vboxt;
  }
  
	
}
 
