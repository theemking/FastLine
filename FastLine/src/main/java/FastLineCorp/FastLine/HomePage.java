package FastLineCorp.FastLine;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;


/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 10, 2023
 * @Class: HomePage
 * @Description: This page will be the main GUI control page. All other pages will be linked to this page
 * 
 */
public class HomePage {
	//calling Style class from the controlCorp page
	Styles homestyle =new Styles();
	
	//calling border pane from the controlCorp page
	BorderPane bPane = new BorderPane();
	
	//calling of other pages for users to navigate to
	ClientsPage clientsp = new ClientsPage(bPane);
	ShipmentPage shipmentp = new ShipmentPage(bPane);
	FlightsPage flightp = new FlightsPage(bPane);
	PilotPage pilotp = new PilotPage(bPane);
	AirPlanePage airplanep = new AirPlanePage(bPane);
	ContactPage contactp = new ContactPage(bPane);
	
	//BorderPane constructore
	HomePage(BorderPane homepage){
		bPane =homepage;
	}
	
	/**
	 * getPane()
	 * When logged into the program a user will see this page first. This is the main page that contain 
	 * navigation information. This information will allow the ControlCorp page to access the HomePage
	 * @return
	 */
	public VBox getPane() {
		VBox homep = new VBox();
		homep.setStyle("-fx-background-color: white");
		homep.getChildren().add(getAllButton());		
		return homep;
	}
	/**
	 * getAllButton
	 * This method will get all the buttons for the homepage class
	 * @return
	 * 
	 */
	private VBox getAllButton() {
		VBox vboxAll=new VBox();
		vboxAll.getChildren().addAll(getBtons());
		vboxAll.setAlignment(Pos.CENTER);		
		return vboxAll;
	}
	/**
	 * getBtons
	 * This method will get the buttons for the homepage. These buttons will be access from the ControlCorp page
	 * @return
	 */
	private HBox getBtons() {
		HBox hboxtons =new HBox();
		hboxtons.getChildren().addAll(getCenterLeftButton(),getCenterMidBton(), getCenterRightBton());
		hboxtons.setAlignment(Pos.CENTER);
		return hboxtons;
	}
	/**
	 * getCenterLeftButton
	 * This methode will display a button for the shipping details on the home page
	 * @return
	 */
	private VBox getCenterLeftButton() {
		VBox vboxclb =new VBox();
		vboxclb.setMaxSize(300,220);
		Text citext = new Text("Client Information");
		Text shiptext = new Text("Shipping Details");
		VBox.setMargin(citext,  new Insets(20,0,20,20));
		VBox.setMargin(shiptext,  new Insets(20,0,20,20));
		Button btonclients=new Button("CLIENT");
		Button btonshipment=new Button("SHIPMENT");
		btonclients.setFont(Font.font("Time New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
		btonshipment.setFont(Font.font("Time New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
		btonclients.setStyle(homestyle.hpcenterBton);// + controlstyle.transparentBtonbox
		btonshipment.setStyle(homestyle.hpcenterBton);
		btonclients.setMinSize(250, 150);
		btonshipment.setMinSize(250, 150);
		vboxclb.getChildren().addAll(citext,btonclients,shiptext,btonshipment);
	
		btonclients.setOnAction(e ->{bPane.setCenter(clientsp.getPane()); });
		btonshipment.setOnAction(e ->{bPane.setCenter(shipmentp.getPane()); });

		
		return vboxclb;
	}
	/**
	 * getCenterMidBton
	 * This function contain buttons that will be linked top the control corp page.
	 * @return
	 * 
	 */
	private VBox getCenterMidBton() {
		VBox vboxcm =new VBox();
		vboxcm.setAlignment(Pos.CENTER);
		Text fltext = new Text("Flight Records");
		VBox.setMargin(fltext, new Insets(20,0,20,20));
		Button btonflights =new Button("FLIGHTS");
		btonflights.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
		btonflights.setStyle(homestyle.hpcenterBton);
		VBox.setMargin(btonflights, new Insets(0,0,0,20));
		btonflights.setMinSize(250, 150);
		vboxcm.getChildren().addAll(fltext,btonflights, getAirPlane());
	
		btonflights.setOnAction(e ->{bPane.setCenter(flightp.getPane()); });
	
		return vboxcm;
	}
	/**
	 * getCenterRightBton()
	 * The function will display on the main control page and will display the pilot button to allow users to 
	 * access the pilot page and class
	 * @return
	 */
	private VBox getCenterRightBton() {
		VBox vboxcr=new VBox();
		Text aptext=new Text("AirPlane Operator Info");
		Text contactText=new Text("How you can reach us");
		VBox.setMargin(aptext, new Insets(20,0,20,20));
		VBox.setMargin(contactText, new Insets(20,0,20,20));
		Button btonpilot =new Button("PILOT");
		Button btoncontact =new Button("CONTACT US");
		btonpilot.setFont(Font.font("Time New Roman", FontWeight.BOLD,FontPosture.REGULAR, 30));
		btoncontact.setFont(Font.font("Time New Roman", FontWeight.BOLD,FontPosture.REGULAR, 30));
		btonpilot.setStyle(homestyle.hpcenterBton);
		btoncontact.setStyle(homestyle.hpcenterBton);
		VBox.setMargin(btonpilot, new Insets(0,0,0,20));
		VBox.setMargin(btoncontact, new Insets(0,0,0,20));
		btonpilot.setMinSize(250, 150);
		btoncontact.setMinSize(250, 150);
		vboxcr.getChildren().addAll(aptext,btonpilot,contactText,btoncontact);
		 
		 btonpilot.setOnAction(e ->{bPane.setCenter(pilotp.getPane()) ;});
			
		 btoncontact.setOnAction(e ->{bPane.setCenter(contactp.getPane()) ;});
	
		return vboxcr;
	}
	/**
	 * getAirPlane()
	 * This function will display the airplane button on the homepage allowing users to access airplane information
	 * @return
	 */
	private VBox getAirPlane() {
		VBox vboxap =new VBox();
		vboxap.setMaxSize(850, 200);
		Text aptext =new Text("Access AirPlane & Model");
		VBox.setMargin(aptext, new Insets(20,0,20,20));
		Button apbton =new Button("AIRPLANE");
		apbton.setFont(Font.font("Time New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
		apbton.setStyle(homestyle.hpcenterBton);
		VBox.setMargin(apbton, new Insets(0,0,0,20));
		apbton.setMinSize(250, 150);
		vboxap.getChildren().addAll(aptext, apbton);
		
		 apbton.setOnAction(e ->{bPane.setCenter(airplanep.getPane()); });

		return vboxap;
	}
	
}
