package FastLineCorp.FastLine;


import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
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

/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 17, 2023
 * @Class: FlightsPage
 * @Description: This page will be the Flights page. This page will be accessed once the Flights button is clicked from the homepage
 * 
 */
//Begin Subclass FlightsPage
public class FlightsPage {
	
	//TextArea for the view -  Pierre 
	static TextArea texReaOne = new TextArea();
    
	// Client address labels - Pierre
	static Label lbstartairport;
	static Label lbendairport;
	static Label lbflightstarttime;
	static Label lbflightendtime;
	static Label lbShipmentEndDate;

	//Text field -  Pierre
	static TextField txstartairport;
	static TextField txendairport;
	static TextField txflightstarttime;
	static TextField txflightendtime;
	static TextField txShipmentEndDate;
	
    //variables
    BorderPane bPane = new BorderPane();
    //used for validation
    
    //button
    Button btonView = new Button("View");
    Button btonAdd = new Button("Add Client");
    Button btonEdit = new Button("Edit Client");
    Button btonDelete = new Button("Delete Client");
    Button btonEnter = new Button("Enter");
    Button btonCancel = new Button("Cancel");
    Button btonExit = new Button("Exit");
    //classes
    Styles flightstyle = new Styles();
    
	/**
	 * ControlCorp constructor
	 * This constructor allows the Flights page to be access from the control page when the user click the Flights button
	 */
    FlightsPage(BorderPane bp) {
        bPane = bp;
    }

    /**
     * getPane()
	 * This function allow access to Flights data from the ControlCorp page
	 * @return 
     */
    public VBox getPane() {
        VBox vbox = new VBox();
        vbox.getChildren().add(flights());
        return vbox;
    }

	/**
	 * Flights()
	 * This is the border pane for the Flights page.
	 * This bPane will be accessed from the ControlCorp page
	 * @return
	 */
    private BorderPane flights() {
        BorderPane boxes = new BorderPane();

        //create a title pane for the top
        VBox header = new VBox();
        header.setAlignment(Pos.CENTER);
        header.setSpacing(10);
        Text title = new Text("Flights");
        Text instruct = new Text("View Flight Information Below:");
        //style text
        title.setFill(Color.DARKBLUE);
        title.setStrokeWidth(2);
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));

        instruct.setFill(Color.BLUE);
        instruct.setStrokeWidth(2);
        instruct.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
        //add titles to titlebox. 
        header.getChildren().addAll(title, instruct);

        //create button HBox:
        HBox hbtonBox = new HBox();
        hbtonBox.setAlignment(Pos.CENTER);
        hbtonBox.setPadding(new Insets(20,0,0,0));
        hbtonBox.setSpacing(20);


        //adding style buttons
          Arrays.asList(btonView, btonAdd, btonEdit, btonDelete, btonEnter,
                  btonCancel).forEach((bton) -> {
                  	bton.setStyle(flightstyle.btonbox);
                  	bton.setMinHeight(30);
                  	bton.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
                  });
          //Exit button style
          btonExit.setStyle(flightstyle.redExit);
          btonExit.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
          btonExit.setMinHeight(0);

          //add buttons to button HBox
          hbtonBox.getChildren().addAll(btonView, btonAdd, btonEdit, btonDelete, btonEnter,
                  btonCancel, btonExit);

          //adding title box
          boxes.setTop(header);
          boxes.setCenter(getSelectView()); //call a method to show db of clients  
          boxes.setBottom(hbtonBox);

  		//adding actions so that users can click on buttons on this page and get a response
  		btonView.setOnAction(e ->{boxes.setCenter(getLabels());});
		btonAdd.setOnAction(e ->{boxes.setCenter(addFlights());});	
        btonEdit.setOnAction(e -> {boxes.setCenter(editFlight());});
        btonDelete.setOnAction(e -> {boxes.setCenter(deleteFlight());  });
		//clear whatever actions taken and return to just the viewClient page      
		btonExit.setOnAction(e ->{boxes.setCenter(getSelectView());});

        return boxes;
    }

     /**
     * This is a function used to display the header section and the TextArea section for the result.
     * @return
     */
    private VBox getLabels() {
    	VBox vboxlb = new VBox();
    	vboxlb.setAlignment(Pos.CENTER);
    	vboxlb.getChildren().addAll(getViewLabel(), getTextAreaOne());
    	return vboxlb;
    }
    /**
	 * getViewLabel()
	 * This function will show a header section that displays the names of the fields
	 * @return
     */
	private HBox getViewLabel() {
		HBox hboxv = new HBox();
		hboxv.setAlignment(Pos.CENTER);
		hboxv.setPadding(new Insets(2, 5, 2, 20));
		hboxv.setSpacing(155);
		hboxv.setPrefWidth(700);
		hboxv.setMaxWidth(900);
		hboxv.setStyle(flightstyle.viewLabel);
				
		Label lbFlightID = new Label ("Flight"); 
		Label lbACID = new Label("Aircraft"); 
		Label lbPilotID = new Label("Pilot Name  "); 
		Label lbStartLoc = new Label("Start Location"); 
		Label lbStartDate = new Label("  Start Date and Time  "); 
		Label lbEndLoc = new Label ("End Location"); 
		Label lbEndDate = new Label("  End Date and Time  "); 
	 

		hboxv.getChildren().addAll(lbFlightID, lbACID, lbPilotID, lbStartLoc,lbStartDate, lbEndLoc,lbEndDate);
		return hboxv;
	}
	/**
	 * This section will display the result for the View All shipment information 
	 * This section will only allow users to view a summary of the shipments. Users will not be able to 
	 * change the shipment information from this shipment view.
	 * @return
	 */
	private ScrollPane getTextAreaOne() {
		ScrollPane box = new ScrollPane(); 
		box.setStyle(flightstyle.bacgroundBLACK);
		box.setFitToWidth(true);
		
		GridPane gpane = new GridPane();
		gpane.setStyle(flightstyle.BgroundBW);
		gpane.setAlignment(Pos.TOP_CENTER);
		gpane.setPadding(new Insets(2,5,2,5)); 
		gpane.setHgap(50);
		gpane.setVgap(5);
		
		// dynamically add values from the database to the gridpane table 

		box.setContent(gpane); 
		return box;
	}
	/**
	 * editFlight()
	 * The function will update the database and refresh the Flight view
	 * @return
	 */
public VBox editFlight() {
	//DateTimePicker dtStart = new DateTimePicker();  
	//DateTimePicker dtEnd = new DateTimePicker(); 

    VBox centerBox = new VBox();
    centerBox.setAlignment(Pos.TOP_CENTER);
    centerBox.setMinHeight(300);
    centerBox.setStyle("-fx-background-color: white");
    
    /**
     * Add title and subtitle for instructions
     */
    Text title = new Text("Update a Flight"); 
    Text instructions = new Text("Use Drop Down Box to select a flight, then click Search. Edit any field(s), and then click Enter"); 
    
    // add a combobox and fill with all client names
    HBox selection = new HBox(); 
    selection.setAlignment(Pos.CENTER);
    selection.setSpacing(4); 
    ComboBox flightSelect = new ComboBox(FXCollections.observableArrayList()); 
    flightSelect.setVisibleRowCount(5); 
    Button flightSearch = new Button("Select Flight"); 
    selection.getChildren().addAll(flightSelect, flightSearch); 
    
    //comboboxes
    ComboBox<Integer> cbAirID = new ComboBox(); 
	cbAirID.getItems().addAll(); 
	
	ComboBox<String> cbPilotID = new ComboBox(); 
	cbPilotID.getItems().addAll();
	
	ComboBox<String> cbStartAirport = new ComboBox(); 
	cbStartAirport.getItems().addAll(); 
	
	ComboBox<String> cbEndAirport = new ComboBox(); 
	cbEndAirport.getItems().addAll();

        //grid of information: 
    GridPane grid = new GridPane(); 
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(4);
    grid.setVgap(4);
    Label lbID = new Label("Flight ID: "); 
	TextField txtID = new TextField();
	txtID.setEditable(false);
	
	Label lbAirID = new Label("Aircraft ID: "); 	
    Label lblStartTime = new Label("Start Time: ");    
    Label lblEndTime = new Label("End Time: ");  
    Label lblPilotID = new Label("Pilot Name: "); 
    Label lblStartAirport = new Label("Start Airport: ");        
    Label lblEndAirport = new Label("End Airport: "); 
     
    
    grid.add(lbID, 0,  0);
    grid.add(txtID, 1,  0);
    grid.add(lbAirID, 0, 1);
    grid.add(cbAirID, 1,  1);
    grid.add(lblStartTime, 0,  2);
    grid.add(lblEndTime, 0, 3);
    grid.add(lblPilotID, 0, 4);
    grid.add(cbPilotID,1 ,4);
    grid.add(lblStartAirport, 0, 5);
    grid.add(cbStartAirport, 1, 5);
    grid.add(lblEndAirport, 0, 6);
    grid.add(cbEndAirport, 1, 6);
 
    
   // fill text with selected flight information
    flightSearch.setOnAction(e->{
	   
    });
    centerBox.getChildren().addAll(title, instructions, selection, grid);
    
    btonEnter.setOnAction(e->{
    	// variables head for header and cont for content
    	String head="Flight ID";
		String cont="Not a int";		
		// check flightID
    	
    	// check aircraftId
    	head ="Aircraft ID";
    	int airCraftID=cbAirID.getValue();
    	
    	
    	//time
		
		
    	head="Pilot ID";

    	String startLoc = cbStartAirport.getValue(); 
		
		String endLoc = cbEndAirport.getValue(); 
    	
    
    	//for start end time
    	head="Time";
    	cont="Not Blank";
    	
    	
    });
    
    btonCancel.setOnAction(e->{
    	flightSelect.valueProperty().set(null);
    	txtID.clear();
    	cbAirID.valueProperty().set(null);
        cbPilotID.valueProperty().set(null);
	    cbStartAirport.valueProperty().set(null);
	    cbEndAirport.valueProperty().set(null);
    });
   
    return centerBox; 
	}
/**
 * getSelectView()
 * This function will allow users to view a client information. 
 * The information will be pull from the SQL Database
 */
private VBox getSelectView() {
    VBox centerSection = new VBox();
    centerSection.setAlignment(Pos.TOP_CENTER);
    centerSection.setMinHeight(300);
    centerSection.setSpacing(5);
    centerSection.setStyle(flightstyle.bacgroundWHITE);
    
    //Adding header
    Text title = new Text("View Selected Flight"); 
    //adding direction
    Text subHeader = new Text("Use the scroll bar to select a flight ID, then click SEARCH. \n"
    		+ "This will allow you to view all flight information for selected flight."); 
    
    // add a combobox and fill with all client names
    HBox select = new HBox(); 
    select.setAlignment(Pos.CENTER);
    ComboBox flightSelect = new ComboBox(FXCollections.observableArrayList()); 
    flightSelect.setVisibleRowCount(5); 
    Button flightSearch = new Button("Search"); 
    select.getChildren().addAll(flightSelect, flightSearch); 
    
    //grid of information: 
    GridPane gPane = new GridPane(); 
    gPane.setHgap(4);
    gPane.setVgap(4);
    gPane.setAlignment(Pos.CENTER);
    Label lbID = new Label("Flight ID: "); 
	Text txtID = new Text(); 
	Label lbAirID = new Label("Aircraft ID: "); 
	Text txtAirID = new Text(); 
    Label lblStartTime = new Label("Start Time: "); 
    Text txtStartTime = new Text();  
    Label lblEndTime = new Label("End Time: "); 
    Text txtEndTime = new Text();  
    Label lblPilotName = new Label("Pilot Name: "); 
    Text txtPilotName = new Text();  
    Label lblStartAirportName = new Label("Start Airport Name: "); 
    Text txtStartAirportName = new Text(); 
    Label lblStartAirportLoc = new Label("Start Airport Location: "); 
    Text txtStartAirportLoc = new Text();  
    Label lblEndAirportName = new Label("Destination Airport Name: "); 
    Text txtEndAirportName = new Text(); 
    Label lblEndAirportLoc = new Label("Destination Airport Location: "); 
    Text txtEndAirportLoc = new Text(); 
    Label lblDistanceHub = new Label("Distance from Hub: "); 
    Text txtDistanceHub = new Text(); 
    
    gPane.add(lbID, 0,  0);
    gPane.add(txtID, 1,  0);
    gPane.add(lbAirID, 0, 1);
    gPane.add(txtAirID, 1,  1);
    gPane.add(lblStartTime, 0,  2);
    gPane.add(txtStartTime, 1,  2);
    gPane.add(lblEndTime, 0, 3);
    gPane.add(txtEndTime, 1, 3);
    gPane.add(lblPilotName, 0, 4);
    gPane.add(txtPilotName,1 ,4);
    gPane.add(lblStartAirportName, 0, 5);
    gPane.add(txtStartAirportName, 1, 5);
    gPane.add(lblStartAirportLoc, 0, 6);
    gPane.add(txtStartAirportLoc, 1, 6);
    gPane.add(lblEndAirportName, 0, 7);
    gPane.add(txtEndAirportName, 1, 7);
    gPane.add(lblEndAirportLoc,  0,  8);
    gPane.add(txtEndAirportLoc, 1, 8);
    gPane.add(lblDistanceHub, 0,  9);
    gPane.add(txtDistanceHub, 1, 9);
    
   // fill text with selected flight information
    flightSearch.setOnAction(e->{

	   
    });
    centerSection.getChildren().addAll(title, subHeader, select, gPane);
    
    btonCancel.setOnAction(e->{
    	flightSelect.valueProperty().set(null);
    	txtID.setText("");
    	txtAirID.setText(""); 
    	txtStartTime.setText("");
    	txtEndTime.setText(""); 
    	txtPilotName.setText("");
    	txtStartAirportName.setText("");
    	txtStartAirportLoc.setText("");
    	txtEndAirportName.setText("");
    	txtEndAirportLoc.setText("");
    	txtDistanceHub.setText("");
    	
    });
    return centerSection; 
}
/**
 * AddFlights()
 * This function will be used to add clients. Users will be able to access the add button from the client page
 * @return
 */
private VBox addFlights() {
	VBox box = new VBox(); 
	box.setAlignment(Pos.CENTER); 
	box.setSpacing(10);
	box.setPadding(new Insets(2,20,2,20));
	box.setStyle(flightstyle.bacgroundWHITE);
	//adding header
	Text header = new Text("Add a new Flight"); 
	Text subHeader = new Text("Enter valid information for a Flight, and then press Enter"); 
	//labels
	Label lblAirID = new Label ("AircraftID: "); 
	Label lblPilot = new Label ("Pilot: "); 
	Label lblStartLoc = new Label ("Start Airport: "); 
	Label lblEndLoc = new Label("End Airport: "); 
	Label lblStartTime = new Label("Start Time: "); 
	Label lblEndTime = new Label("End Time: "); 
	
	//Adding style to label
	Arrays.asList(lblAirID, lblPilot, lblStartLoc, lblEndLoc, lblStartTime, lblEndTime).stream().map((b)->{
		b.setStyle(flightstyle.button); 
		return b; 
	}); 
			
	//entry fields
	ComboBox<Integer> cbAirID = new ComboBox(FXCollections.observableArrayList()); 
	cbAirID.getItems().addAll(); 
	
	ComboBox<String> cbPilotID = new ComboBox(FXCollections.observableArrayList()); 
	cbPilotID.getItems().addAll();
	
	ComboBox<String> cbStartLoc = new ComboBox(FXCollections.observableArrayList()); 
	cbStartLoc.getItems().addAll(); 
	
	ComboBox<String> cbEndLoc = new ComboBox(FXCollections.observableArrayList()); 
	cbEndLoc.getItems().addAll(); 
	
	//need to incorporate a time function to these... 
//	 DateTimePicker dtStart = new DateTimePicker();  
//	 DateTimePicker dtEnd = new DateTimePicker(); 
	
	//add input values into a gridpane
	GridPane gPane = new GridPane(); 
	gPane.setAlignment(Pos.CENTER);
	gPane.setHgap(10);
	gPane.setVgap(4);
	gPane.add(lblAirID, 0, 0);
	gPane.add(lblPilot, 0, 1);
	gPane.add(lblStartLoc, 0, 2);
	gPane.add(lblEndLoc, 0, 3);
	gPane.add(lblStartTime, 0, 4);
	gPane.add(lblEndTime, 0, 5);
	
	gPane.add(cbAirID, 1, 0);
	gPane.add(cbPilotID, 1, 1);
	gPane.add(cbStartLoc, 1, 2);
	gPane.add(cbEndLoc, 1, 3);
		
	box.getChildren().addAll(header,subHeader,gPane); 
	btonEnter.setOnAction(e->{
		int airID =0;
		airID = cbAirID.getValue(); 
		int pilotID =0;
		
		//grab the location id from the lookup table
		String startLoc = cbStartLoc.getValue(); 
		
		String endLoc = cbEndLoc.getValue(); 


		String head="AirCraft ID";
		String cont="Cant be unselected";	
		
		    		
	});
	
	btonCancel.setOnAction(e->{
		cbAirID.valueProperty().set(null);
		cbPilotID.valueProperty().set(null);
		cbStartLoc.valueProperty().set(null);
		cbEndLoc.valueProperty().set(null); 

	});
	    	
	return box; 
}
/**
 * deleteFlight() 
 * This method will display delete button to allow users to delete a client
 * @return
 */
public VBox deleteFlight() {

	VBox box = new VBox(); 
	box.setAlignment(Pos.CENTER); 
	box.setSpacing(10);
	box.setPadding(new Insets(2,20,2,20));
	box.setStyle("-fx-background-color: white");
	
	//adding header
	Text title = new Text("Delete Flight"); 
	Text subHeader = new Text("Select the flightID you wish to delete, then press Enter"); 
	
	//add input values into a gridpane
	GridPane gpane = new GridPane(); 
	gpane.setAlignment(Pos.CENTER);
	gpane.setHgap(10);
	gpane.setVgap(4);
	Text txtFlightID = new Text("Flight ID: "); 
	ComboBox cbFlightID = new ComboBox(FXCollections.observableArrayList());
	gpane.add(txtFlightID, 0, 0);
	gpane.add(cbFlightID, 1, 0);
	
	box.getChildren().addAll(title, subHeader, gpane); 
	
	//actionables:
	btonEnter.setOnAction(e->{
		int flightID = Integer.parseInt(cbFlightID.getValue().toString()); //get flight ID
		cbFlightID.valueProperty().set(null); //clear combobox
		cbFlightID.setItems(FXCollections.observableArrayList());
	});
	
	btonCancel.setOnAction(e->{
		cbFlightID.valueProperty().set(null); //clear combobox
	});
	
	return box; 
}

} //End Subclass FlightsPage