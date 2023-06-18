package FastLineCorp.FastLine;


import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

//Begin Subclass ShipmentsPage
public class ShipmentPage {
    //classes
    Styles shipmentpage = new Styles();
	static TextArea texReaOne = new TextArea();
    
	// Client address labels
	static Label lbShipmentVolume = new Label("Shipment Volume");
	static Label lbShipmentWeight = new Label("Shipment Weight");
	static Label lbShipmentStartDate = new Label("Shipment Start Date");
	static Label lbShipmentEndDate  = new Label("Shipment End Date");
	static Label lbShipmentNotes = new Label("Shipment Notes ");

	static TextField txShipmentID;
	static TextField txShipmentVolume;
	static TextField txShipmentWeight;
	static TextField txShipmentStartDate;
	static TextField txShipmentEndDate;

	static TextArea texRea;
	
	//make buttons
    Button btnView = new Button("View");
    Button btnAdd = new Button("Add Shipment");
    Button btnEdit = new Button("Edit Shipment");
    Button btnDelete = new Button("Delete Shipment");
    Button btnEnter = new Button("Enter");
    Button btnCancel = new Button("Cancel");
    Button btnExit = new Button("Exit");
	
    //variables
    BorderPane bPane = new BorderPane();
    //used for validation

    /**
     * Constructor - pulls the border pane from (main page)
     *
     * @param bp
     */
    ShipmentPage(BorderPane bp) {
        bPane = bp;
    }

    /**
     * getPane - this will call the shipments pane into the main page.
     *
     * @return
     */
    public VBox getPane() {
        VBox vbox = new VBox();
        vbox.getChildren().add(shipments());
        return vbox;
    }

    /**
     * Shipments- main pane for shipment information
     *
     * @return
     */
    private BorderPane shipments() {
        BorderPane box = new BorderPane();

        //create a title pane for the top
        VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setSpacing(10);
        Text title = new Text("Shipments");
        Text instruct = new Text("View Shipment Information Below:");
        //style text
        title.setFill(Color.DARKBLUE);
        title.setStrokeWidth(2);
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));

        instruct.setFill(Color.BLUE);
        instruct.setStrokeWidth(2);
        instruct.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
        //add titles to titlebox. 
        titleBox.getChildren().addAll(title, instruct);
        
        //create button HBox:
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20,0,0,0));
        buttonBox.setSpacing(20);
    
        //style buttons
        Arrays.asList(btnView, btnAdd, btnEdit, btnDelete, btnEnter,
                btnCancel).forEach((b) -> {
                    b.setStyle(shipmentpage.btonbox);
                    b.setMinHeight(30);
                    b.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
                });
        //Exit button style
        btnExit.setStyle(shipmentpage.btonbox);
        btnExit.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
        btnExit.setMinHeight(30);

        //add buttons to button HBox
        buttonBox.getChildren().addAll(btnView, btnAdd, btnEdit, btnDelete, btnEnter,
                btnCancel, btnExit);

        //add title, center, and buttons to shipment pane:
        box.setTop(titleBox);
        box.setCenter(getViewSelected()); //call a method to show db of shipments  
        box.setBottom(buttonBox);

        //add actionables to change the setCenter based on button responses:
        btnView.setOnAction(e -> {
        	box.setCenter(getViewLBs());
        });
        btnAdd.setOnAction(e -> {              	 
        	 box.setCenter(addShipmentPane());
        });
        btnEdit.setOnAction(e -> {
        	 box.setCenter(editShipmentPane() );
        });
        btnDelete.setOnAction(e -> {
        	box.setCenter(deleteShipmentPane());
        });
        btnExit.setOnAction(e -> {
            //clear whatever actions doing
            //return to just the viewShipments page
            box.setCenter(getViewSelected());
        });

        return box;
    }

    /**
     * This is a function used to display the header section and the TextArea section for the result.
     * @return
     */
    private VBox getViewLBs() {
    	VBox vboxlb = new VBox();
    	vboxlb.setAlignment(Pos.CENTER);
    	vboxlb.getChildren().addAll(getViewLabel(), getTextAreaOne());
    	return vboxlb;
    }
    /**
     * This is a header section for the shipment view page. This header displays the names of the fields
     * @return
     */
	private HBox getViewLabel() {
		HBox hboxv = new HBox();
		hboxv.setAlignment(Pos.CENTER);
		hboxv.setPadding(new Insets(2,20,2,20));
		hboxv.setSpacing(100);
		hboxv.setPrefWidth(700);
		hboxv.setMaxWidth(900);
		hboxv.setStyle("-fx-background-color: white; -fx-border-color: black");
		Label id = new Label("ID"); 
		Label volume = new Label("Volume");
		Label weight = new Label("Weight"); 
		Label startDate = new Label("Start Date");
		Label endDate = new Label("End Date");
		Label notes = new Label("Notes");

		hboxv.getChildren().addAll(id, volume, weight, startDate, endDate, notes);
		return hboxv;
	}
	

	private ScrollPane getTextAreaOne() {
		ScrollPane box = new ScrollPane();
		box.setFitToWidth(true);
		box.setStyle("-fx-background-color: white; -fx-border-color: black"); 
		
		
		 GridPane gpane = new GridPane(); 		 
		 gpane.setStyle("-fx-background-color: white; -fx-border-color: black");
		 gpane.setPadding(new Insets(10,0,10,20));
		 gpane.setAlignment(Pos.TOP_CENTER); 
		 gpane.setHgap(90);
		 gpane.setVgap(5);
		
		 box.setContent(gpane);
		return box;
	}

	private VBox getViewSelected() {		
	    VBox centerBox = new VBox();
	    centerBox.setAlignment(Pos.TOP_CENTER);
	    centerBox.setMinHeight(300);
	    centerBox.setStyle("-fx-background-color: white");	    
	    
	    // Add title and subtitle for instructions
	    Text title = new Text("View Selected Shipment"); 
	    Text instructions = new Text("Use the scroll bar to select a Shipment ID, then click SEARCH. \n"
	    		+ "This will allow you to view all Shipment information for selected shipment."); 
	    
	    // add a combobox and fill with all client names
	    HBox selection = new HBox(); 
	    selection.setAlignment(Pos.CENTER);
	   
	    ComboBox shipSelect = new ComboBox(FXCollections.observableArrayList()); 
	   shipSelect.setVisibleRowCount(5); 
	    
	    Button shipSearch = new Button("Search"); 
	    selection.getChildren().addAll(shipSelect, shipSearch); 
	    
	    //grid of information: 
	    GridPane grid = new GridPane(); 
	    grid.setAlignment(Pos.CENTER);
	    grid.setHgap(4);
	    grid.setVgap(4);
	    grid.setPadding(new Insets(4,4,4,4));
	    
	    Label lbID = new Label("Shipment ID: "); 
		Text txtID = new Text(); 
		Label lbVolume = new Label("Shipment Volume: "); 
		Text txtVolume = new Text(); 
	    Label lblWeight = new Label("Shipment Weight: "); 
	    Text txtWeight = new Text();  
	    Label lblStatus = new Label("Shipment Status: "); 
	    Text txtStatus = new Text();  
	    Label lblStart = new Label("Start Date: "); 
	    Text txtStart = new Text();  
	    Label lblEnd = new Label("End Date: "); 
	    Text txtEnd = new Text(); 
	    Label lblNotes = new Label("Notes: "); 
	    Text txtNotes = new Text();  
	     	    
	    grid.add(lbID, 0,  0);
	    grid.add(txtID, 1,  0);
	    grid.add(lbVolume, 0, 1);
	    grid.add(txtVolume, 1,  1);
	    grid.add(lblWeight, 0,  2);
	    grid.add(txtWeight, 1,  2);
	    grid.add(lblStatus, 0, 3);
	    grid.add(txtStatus, 1, 3);
	    grid.add(lblStart, 0, 4);
	    grid.add(txtStart,1 ,4);
	    grid.add(lblEnd, 0, 5);
	    grid.add(txtEnd, 1, 5);
	    grid.add(lblNotes, 0, 6);
	    grid.add(txtNotes, 1, 6);
	   
	   // fill text with selected flight information
	    shipSearch.setOnAction(e->{
		   
	    });
	    
	    btnCancel.setOnAction(e->{
	    	shipSelect.valueProperty().set(null);
	    	txtID.setText("");
	    	txtVolume.setText("");
	    	txtWeight.setText("");
	    	txtStatus.setText("");
	    	txtStart.setText("");
	    	txtEnd.setText("");
	    	txtNotes.setText(""); 
	    });
	    centerBox.getChildren().addAll(title, instructions, selection, grid);
	    
	    return centerBox; 
	}

	
	private VBox addShipmentPane() {
		//addshipment class imports
		
		VBox centerBox = new VBox();
	    centerBox.setAlignment(Pos.TOP_CENTER);
	    centerBox.setMinHeight(300);
	    centerBox.setStyle("-fx-background-color: white");
	    
	    // Add title and subtitle for instructions
	    Text title = new Text("Add a Shipment"); 
	    Text instructions = new Text("Add shipment information into the fields below, then press Enter");
	    
	    //gridpane for information
	    GridPane grid = new GridPane(); 
	    grid.setAlignment(Pos.CENTER);
	    grid.setHgap(4);
	    grid.setVgap(4);
	    grid.setPadding(new Insets(4,4,4,4));
	    
	    Label lbID = new Label("Client Name: "); 
		ComboBox<String> cbID = new ComboBox(FXCollections.observableArrayList()); //get usable client IDs
		Label lbVolume = new Label("Shipment Volume: "); 
		TextField txtVolume = new TextField(); 
	    Label lblWeight = new Label("Shipment Weight: "); 
	    TextField txtWeight = new TextField();  
	    Label lblStatus = new Label("Shipment Status: "); 
	    ComboBox<String> cbStatus = new ComboBox(FXCollections.observableArrayList());   //add status values
	    Label lblStart = new Label("Start Date: "); 
	    DatePicker dpStart = new DatePicker();   
	    Label lblEnd = new Label("End Date: "); 
	    DatePicker dpEnd = new DatePicker(); 
	    Label lblNotes = new Label("Notes: "); 
	    TextField txtNotes = new TextField();  
	    
	    grid.add(lbID, 0,  0);
	    grid.add(cbID, 1,  0);
	    grid.add(lbVolume, 0, 1);
	    grid.add(txtVolume, 1,  1);
	    grid.add(lblWeight, 0,  2);
	    grid.add(txtWeight, 1,  2);
	    grid.add(lblStatus, 0, 3);
	    grid.add(cbStatus, 1, 3);
	    grid.add(lblStart, 0, 4);
	    grid.add(dpStart,1 ,4);
	    grid.add(lblEnd, 0, 5);
	    grid.add(dpEnd, 1, 5);
	    grid.add(lblNotes, 0, 6);
	    grid.add(txtNotes, 1, 6);
	    
	    //actionables
	    btnEnter.setOnAction(e->{
	    	//variables for sql stored procedure
	    	int clientID=0;
	    	int clientIndex=0;
	    	int statusIndex=0;
	    	int statusID=0;
	    	
	    	Date startDate = Date.valueOf(dpStart.getValue());
	    	
	    	//get text for volume and weight
	    	Float weight = null;
	    	Float volume = null;	    	   
	    	
	    	String notes = txtNotes.getText().toString();
	    	
	    	
	    	//validation check and if error don't sent to DB
	    	
		    		//add shipment with no endDate
			    	//clear entry fields
			    	cbID.valueProperty().set(null);
			    	txtVolume.clear(); 
			    	txtWeight.clear(); 
			    	cbStatus.valueProperty().set(null);
			    	dpStart.valueProperty().set(null);
			    	dpEnd.valueProperty().set(null);
			    	txtNotes.clear(); 
		 
	      	
	    
	    });
	    
	    btnCancel.setOnAction(e->{
	    	cbID.valueProperty().set(null);
	    	txtVolume.clear(); 
	    	txtWeight.clear(); 
	    	cbStatus.valueProperty().set(null);
	    	dpStart.valueProperty().set(null);
	    	dpEnd.valueProperty().set(null);
	    	txtNotes.clear(); 
	    });
	    centerBox.getChildren().addAll(title, instructions, grid); 
	    return centerBox; 
	}

	private VBox editShipmentPane() {
		
	    VBox centerBox = new VBox();
	    centerBox.setAlignment(Pos.TOP_CENTER);
	    centerBox.setMinHeight(300);
	    centerBox.setStyle("-fx-background-color: white");	    
	    
	    // Add title and subtitle for instructions
	    Text title = new Text("View Selected Shipment to EDIT"); 
	    Text instructions = new Text("Select a ShipmentID and hit find shipment."); 
	    
	    // add a combobox and fill with all client names
	    HBox selection = new HBox(); 
	    selection.setAlignment(Pos.CENTER);
	   
	    ComboBox<Integer> shipSelect = new ComboBox(FXCollections.observableArrayList()); 
	    shipSelect.setVisibleRowCount(5); 
	    
	    Button shipSearch = new Button("Find Shipment"); 
	    selection.getChildren().addAll(shipSelect, shipSearch); 
	    
	    //grid of information: 
	    GridPane grid = new GridPane(); 
	    grid.setAlignment(Pos.CENTER);
	    grid.setHgap(4);
	    grid.setVgap(4);
	    grid.setPadding(new Insets(4,4,4,4));
	    
	    Label lbID = new Label("Shipment ID: "); 
		TextField txtID = new TextField();
		txtID.setEditable(false);
			
		Label lbVolume = new Label("Shipment Volume: "); 
		TextField txtVolume = new TextField(); 
	    Label lblWeight = new Label("Shipment Weight: "); 
	    TextField txtWeight = new TextField();  
	    
	    Label lblStatus = new Label("Shipment Status: "); 
	    ComboBox<String> cbStatus = new ComboBox(FXCollections.observableArrayList());   //add status values
	   
	    Label lblStart = new Label("Start Date: "); 
	    DatePicker dpStart = new DatePicker(); 
	    Label lblEnd = new Label("End Date: "); 
	    DatePicker dpEnd = new DatePicker(); 
	    Label lblNotes = new Label("Notes: "); 
	    TextField txtNotes = new TextField();  
	    Label lblClientID =new Label("Client Name: ");	 
	    ComboBox<String> cbClientID = new ComboBox(FXCollections.observableArrayList()); //puts name in box. 
	     
	    grid.add(lbID, 0,  0);
	    grid.add(txtID, 1,  0);
	    grid.add(lblClientID,0,1);
	    grid.add(cbClientID, 1,1);
	    grid.add(lbVolume, 0, 2);
	    grid.add(txtVolume, 1,  2);
	    grid.add(lblWeight, 0,  3);
	    grid.add(txtWeight, 1,  3);
	    grid.add(lblStatus, 0, 4);
	    grid.add(cbStatus, 1, 4);
	    grid.add(lblStart, 0, 5);
	    grid.add(dpStart,1 ,5);
	    grid.add(lblEnd, 0, 6);
	    grid.add(dpEnd, 1, 6);
	    grid.add(lblNotes, 0, 7);
	    grid.add(txtNotes, 1, 7);
	     
	   // fill text with selected flight information
	    shipSearch.setOnAction(e->{
		   try {
			   //clear fields
				txtID.clear(); 
		    	txtVolume.clear();
		    	txtWeight.clear();
		    	cbClientID.valueProperty().set(null);
		    	cbStatus.valueProperty().set(null);
		    	dpStart.valueProperty().set(null);
		    	dpEnd.valueProperty().set(null);
		    	txtNotes.clear();
		    	//Get selected shipment
			   int selectedShipment; 
			   selectedShipment = shipSelect.getValue(); 
 
		   } catch(Exception ex) {
			   shipSelect.requestFocus(); 
		   }
		   
	    });
	    centerBox.getChildren().addAll(title, instructions, selection, grid);
	    //enter for update
	    btnEnter.setOnAction(e->{    	
 	
	    	String head="Status ID";
			String cont="status ID not integer";	
			
			//shipment ID
			head = "Shipment ID"; 
			cont = "shipment ID not integer"; 
			String stringShipmentID = txtID.getText(); 
						
			//Volume and Weight to string
			Float sWeight = null;
	    	Float sVolume = null;	    	   
	    	
	    	String sNote = txtNotes.getText().toString();
	    	
	    			
			Date sDate = Date.valueOf(dpStart.getValue());
		     

		    	txtID.setText("");
		    	txtVolume.clear();
		    	txtWeight.clear();
		    	cbClientID.valueProperty().set(null);
		    	cbStatus.valueProperty().set(null);
		    	dpStart.valueProperty().set(null);
		    	dpEnd.valueProperty().set(null);
		    	txtNotes.clear();  
		    	shipSelect.valueProperty().set(null); 

		    		//clear text fields
		    	txtID.setText("");
		    	txtVolume.clear();
		    	txtWeight.clear();
		    	cbClientID.valueProperty().set(null);
		    	cbStatus.valueProperty().set(null);
		    	dpStart.valueProperty().set(null);
		    	dpEnd.valueProperty().set(null);
		    	txtNotes.clear();  
		    	shipSelect.valueProperty().set(null); 

	    });
	    
	    btnCancel.setOnAction(e->{
	    	shipSelect.valueProperty().set(null);
	    	txtID.setText("");	    	
	    	txtVolume.setText(""); 
	    	txtWeight.setText("");	    	
	    	txtNotes.setText("");  
	    	cbClientID.valueProperty().set(null);
	    	cbStatus.valueProperty().set(null);
	    	dpStart.valueProperty().set(null);
	    	dpEnd.valueProperty().set(null);
	    });
	     
	    return centerBox; 
	}

	private VBox deleteShipmentPane() {
		VBox centerBox = new VBox();
	    centerBox.setAlignment(Pos.TOP_CENTER);
	    centerBox.setMinHeight(300);
	    centerBox.setStyle("-fx-background-color: white");
	    
	    // Add title and subtitle for instructions
	    Text title = new Text("Delete a Shipment"); 
	    Text instructions = new Text("select shipment id you wish to delete, then press Enter");
	    
	    GridPane grid = new GridPane();
	    grid.setAlignment(Pos.CENTER);
	    grid.setHgap(4);
	    grid.setVgap(4);
	    grid.setPadding(new Insets(4,4,4,4));
	    Label lblID = new Label("Shipment ID: "); 
	    ComboBox<Integer> cbShipID = new ComboBox(FXCollections.observableArrayList());
	    grid.add(lblID, 0,0);
	    grid.add(cbShipID, 1, 0);
	    
	    centerBox.getChildren().addAll(title,instructions, grid); 
	    
	    btnEnter.setOnAction(e->{
	    	int shipID = Integer.parseInt(cbShipID.getValue().toString());
	    	cbShipID.valueProperty().set(null);
	    	cbShipID.setItems(FXCollections.observableArrayList());	    	
	    }); 
	    
	    btnCancel.setOnAction(e->{
	    	cbShipID.valueProperty().set(null);
	    });
	    
	    return centerBox; 
	}
	
} //End Subclass ShipmentsPage
