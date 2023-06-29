package FastLineCorp.FastLine;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

/**
 * @author Name: Dony Pierre
 * @Assignment: FastLineCorp Project
 * @Date: June 17, 2023
 * @Class: AirPlanePage
 * @Description: This page will be the AirPlanePage page. This page will be accessed once the AirPlanePage button is clicked from the homepage
 * 
 */
//Begin Subclass AircraftPage
public class AirPlanePage {
	
	//TextArea for the view -  Pierre 
	static TextArea texReaOne = new TextArea();
    
	// Client address labels - Pierre

	static Label lbcraftMake;
	static Label lbcraftModel;
	static Label lbcraftRang;
	static Label lbcraftRC;
	static Label lbtxPayload ;
	static Label lbtxLoadVolume;

	//Text field -  Pierre
	static TextField txcraftMake;
	static TextField txcraftModel;
	static TextField txcraftRang;
	static TextField txcraftRC;
	static TextField txPayload ;
	static TextField txLoadVolume;
	
	//make buttons
    Button btnView = new Button("View");
    Button btnAdd = new Button("Add Aircraft");
    Button btnEdit = new Button("Edit Aircraft");
    Button btnDelete = new Button("Delete Aircraft");
    Button btnEnter = new Button("Enter");
    Button btnCancel = new Button("Cancel");
    Button btnExit = new Button("Exit");
	
    //variables
    BorderPane bPane = new BorderPane();

    //classes
    Styles airPlanestyle = new Styles();
    

    /**
     * Constructor - pulls the border pane from (main page)
     *
     * @param bp
     */
    AirPlanePage(BorderPane airplanep) {
        bPane = airplanep;
    }

    /**
     * getPane - this will call the Flights pane into the main page.
     *
     * @return
     */
    public VBox getPane() {
        VBox vbox = new VBox();
        vbox.getChildren().add(modelInfo());
        return vbox;
    }

    /**
     * flights - this is the main border pane for the flights page.
     *
     * @return
     */
    private BorderPane modelInfo() {
        BorderPane box = new BorderPane();

        //create a title pane for the top
        VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setSpacing(10);
        Text title = new Text("airplane");
        Text instruct = new Text("View Aircraft Information Below:");
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
                    b.setStyle(airPlanestyle.button);
                    b.setMinHeight(30);
                    b.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
                });
        //Exit button style
        btnExit.setStyle(airPlanestyle.btonbox);
        btnExit.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
        btnExit.setMinHeight(30);

        //add buttons to button HBox
        buttonBox.getChildren().addAll(btnView, btnAdd, btnEdit, btnDelete, btnEnter,
                btnCancel, btnExit);

        //add title, center, and buttons to airplane pane:
        box.setTop(titleBox);
        box.setCenter(getViewSelected()); //call a method to show db of flights  
        box.setBottom(buttonBox);

        //add actionables to change the setCenter based on button responses:
        btnView.setOnAction(e -> {
            box.setCenter(getCraftLBs());
        });
        btnAdd.setOnAction(e -> {
            box.setCenter(getAdd()); 
        });
        btnEdit.setOnAction(e -> {
        	box.setCenter(editAircraft()); 
        });
        btnDelete.setOnAction(e -> {
        	box.setCenter(getDelete());
        });
       btnExit.setOnAction(e -> {
            //clear whatever actions doing
            //return to just the viewFlights page
            box.setCenter(getViewSelected());
        });

        return box;
    }

      
   /**
     * This is a function used to display the header section and the TextArea section for the result.
     * @return
     */
    private VBox getCraftLBs() {
    	VBox vboxlb = new VBox();
    	vboxlb.setAlignment(Pos.CENTER);
    	vboxlb.getChildren().addAll(getAircraftLabel(), getTextAreaOne());
    	return vboxlb;
    }
    /**
     * This is a header section for the airplane view page. This header displays the names of the fields
     * @return
     */
	private HBox getAircraftLabel() {
		HBox hboxv = new HBox();
		hboxv.setAlignment(Pos.CENTER);
		hboxv.setPadding(new Insets(2, 20, 2, 20));
		hboxv.setSpacing(70);
		hboxv.setPrefWidth(700);
		hboxv.setMaxWidth(900);
		hboxv.setStyle("-fx-background-color: white; -fx-border-color: black");
		Label lbcraftID = new Label("airplane ID"); 
		Label lbStatus = new Label("Status"); 
		 lbcraftMake = new Label("airplane Make");
		 lbcraftModel = new Label("airplane Model");
		 lbcraftRang = new Label("airplane Range");
		 lbcraftRC = new Label("Range Clasification");
		hboxv.getChildren().addAll(lbcraftID, lbStatus, lbcraftMake, lbcraftModel, lbcraftRang, lbcraftRC);
		return hboxv;
	}

	/**
	 * This TextArea will display the output or result for the airplane information 
	 * it will allow users to view a summary of the airplane information. Users will not be able to 
	 * change the airplane information from this airplane view.
	 * @return
	 */
	private ScrollPane getTextAreaOne() {
		ScrollPane box = new ScrollPane(); 
		box.setStyle("-fx-border-color: black");
		box.setFitToWidth(true);
		
		GridPane gpane = new GridPane();
		gpane.setStyle("-fx-background-color: white; -fx-border-color: black");
		gpane.setAlignment(Pos.TOP_CENTER);
		gpane.setPadding(new Insets(2,20,2,20)); 
		gpane.setHgap(90);
		gpane.setVgap(5);
				
				box.setContent(gpane); 
				return box;
	}
	/**
	 * getViewSelected - the initial pane for the viewairplane Page, 
	 * will house a feature to view a selected aircraft based on 
	 * airplane ID
	 * @return
	 */
	private VBox getViewSelected(){
		VBox centerBox = new VBox(); 
		 centerBox.setAlignment(Pos.TOP_CENTER);
		 centerBox.setMinHeight(300);
		 centerBox.setStyle("-fx-background-color: white");
		 
		// add title and subtitle instructions 
		    Text title = new Text("View Selected airplane"); 
		    Text instructions = new Text("Use the scroll bar to select an airplaneID, then click SEARCH. \n"
		    		+ "This will allow you to view all client information for selected plane.");
		    HBox selection = new HBox(); 
		    selection.setAlignment(Pos.CENTER);
		    ComboBox airSelect = new ComboBox(FXCollections.observableArrayList()); 
		    airSelect.setVisibleRowCount(5);
		    Button airSearch = new Button("Search"); 
		    selection.getChildren().addAll(airSelect, airSearch); 
		
		    
		  //grid of information
		    GridPane grid = new GridPane(); 
		    grid.setAlignment(Pos.CENTER); 
		    grid.setPadding(new Insets(2,20,2,20)); 
			grid.setHgap(5);
			grid.setVgap(5);
			
		    Label lbID = new Label("Aircraft ID: "); 
		    Label lbStatus = new Label("Status: "); 
		    Label lbMake = new Label("Make: "); 
		    Label lbModel = new Label("Model: "); 
		    Label lbRange = new Label("Range: "); 
		    Label lbRangeClass = new Label("Range Classification: "); 
		    Label lbPayload = new Label("Payload: "); 
		    Label lbLoadVol = new Label("Load Volume: "); 
		    
		    Text txtID = new Text(); 
		    Text txtStatus = new Text(); 
		    Text txtMake = new Text(); 
		    Text txtModel = new Text(); 
		    Text txtRange = new Text(); 
		    Text txtRangeClass = new Text(); 
		    Text txtPayload = new Text(); 
		    Text txtLoadVol = new Text(); 
		    
		    grid.add(lbID, 0, 0);
		    grid.add(lbStatus, 0, 1);
		    grid.add(lbMake, 0, 2);
		    grid.add(lbModel, 0, 3);
		    grid.add(lbRange,0,4); 
		    grid.add(lbRangeClass,0,5);
		    grid.add(lbPayload, 0, 6);
		    grid.add(lbLoadVol, 0, 7);
		    grid.add(txtID, 1, 0);
		    grid.add(txtStatus, 1, 1);
		    grid.add(txtMake, 1, 2);
		    grid.add(txtModel, 1, 3);
		    grid.add(txtRange,1,4); 
		    grid.add(txtRangeClass,1,5);
		    grid.add(txtPayload, 1, 6);
		    grid.add(txtLoadVol, 1, 7);
		    
		    airSearch.setOnAction(e->{
		    	int entry = 0; 
		    	entry = Integer.parseInt(airSelect.getValue().toString());		    	
		    	txtID.setText("");
		    	txtStatus.setText("");
		    	txtMake.setText("");
		    	txtModel.setText("");
		    	txtRange.setText("");
		    	txtRangeClass.setText("");
		    	txtPayload.setText("");
		    	txtLoadVol.setText("");
		    });
		    
		    btnCancel.setOnAction(e->{
		    	airSelect.valueProperty().set(null);
		    	txtID.setText("");
		    	txtStatus.setText(""); 
		    	txtMake.setText("");
		    	txtModel.setText("");
		    	txtRange.setText("");
		    	txtRangeClass.setText("");
		    	txtPayload.setText("");
		    	txtLoadVol.setText("");
		    }); 
		    centerBox.getChildren().addAll(title,instructions, selection, grid); 
		    
		return centerBox; 
	}


	/**
	 * getAdd - method to house the pane for adding airplane into the database. 
	 */
	private VBox getAdd() {
		VBox centerBox = new VBox(); 
		 centerBox.setAlignment(Pos.TOP_CENTER);
		 centerBox.setMinHeight(300);
		 centerBox.setStyle("-fx-background-color: white");
		 
		// add title and subtitle instructions 
		    Text title = new Text("Add New airplane"); 
		    Text instructions = new Text("Enter The model for a new airplane, then click Enter");
		    		    
		// add entry fields:
		    GridPane gpane = new GridPane(); 
		    gpane.setAlignment(Pos.CENTER); 
		    gpane.setPadding(new Insets(2,20,2,20)); 
			gpane.setHgap(5);
			gpane.setVgap(5);
		    //create an arraylist with the make and model together
		    ArrayList<String> modelMake = new ArrayList<>(); 
		    ComboBox<String> cbModels = new ComboBox(FXCollections.observableArrayList(modelMake)); //call a list of current models to choose from 
		    gpane.add(cbModels, 0,  0);
		    		    
		    centerBox.getChildren().addAll(title, instructions, gpane);  
		    
		    btnEnter.setOnAction(e->{
		    	//find model id based on chosen index in cbModels
		    	int index = modelMake.indexOf(cbModels.getValue()); 
		    	//clear selection
		    	cbModels.valueProperty().set(null);
		    });
		    
		    btnCancel.setOnAction(e->{
		    	cbModels.valueProperty().set(null); //set combobox back to null
		    });
		
		
		return centerBox; 
	}


	private VBox editAircraft() {
		
		VBox centerBox = new VBox(); 
		 centerBox.setAlignment(Pos.TOP_CENTER);
		 centerBox.setMinHeight(300);
		 centerBox.setStyle("-fx-background-color: white");
		 
		// add title and subtitle instructions 
		    Text title = new Text("View Selected airplane"); 
		    Text instructions = new Text("Select an airplane and hit find airplane.");
		    HBox selection = new HBox(); 
		    selection.setAlignment(Pos.CENTER);
		    ComboBox airSelect = new ComboBox(FXCollections.observableArrayList()); 
		    airSelect.setVisibleRowCount(5);
		    Button airSearch = new Button("Find airplane"); 
		    selection.getChildren().addAll(airSelect, airSearch); 
		
		    
		  //grid of information
		    GridPane grid = new GridPane(); 
		    grid.setAlignment(Pos.CENTER); 
		    grid.setPadding(new Insets(2,20,2,20)); 
			grid.setHgap(5);
			grid.setVgap(5);
		    Label lbID = new Label("Aircraft ID: "); 
		  //  Label lbStatusID = new Label("Status ID: "); 
		    
		    Label lbModel = new Label("Model: "); 
		   
		    
		    TextField txtID = new TextField(); 
		 //   TextField txtStatusID = new TextField(); 		 
		    ComboBox<String> cbModel = new ComboBox(FXCollections.observableArrayList()); 
		  
		    
		    grid.add(lbID, 0, 0);
		    grid.add(lbModel, 0, 1);
		   // grid.add(lbStatusID, 0, 2);
		    grid.add(txtID, 1, 0);
		    grid.add(cbModel, 1, 1);
		  //  grid.add(txtStatusID, 1, 2);
		
		    
		    airSearch.setOnAction(e->{
		    	int entry = 0; 
		    	entry = Integer.parseInt(airSelect.getValue().toString());		    	
		    	txtID.setText("");
		    	txtID.setEditable(false);
		    	//txtStatusID.setText(Integer.toString(view.getStatusID()));
		    	cbModel.valueProperty().set("");
		    	
		    });
		    
		    centerBox.getChildren().addAll(title,instructions, selection, grid); 
		    btnEnter.setOnAction(e->{
		    	//Check for int if not get Message PopUp
		    	String head="airplane ID";
		    	String cont="Not and Int";
		    	head ="Model ID";
		    	String eModel = cbModel.getSelectionModel().getSelectedItem().toString();
		    	//if zero for aID and or modelID call error message
		    	
		    });
		    
		    //clear fields to cancel 
		    btnCancel.setOnAction(e->{
		    	txtID.clear(); 
		    	cbModel.valueProperty().set(null); 
		    	airSelect.valueProperty().set(null);
		    });
		    //clear
		    txtID.clear();
		   // txtStatusID.clear();
		    cbModel.valueProperty().set(null);
		    
		return centerBox; 
		
	}

	/**
	 * getDelete - method for deleting an airplane from the database
	 * @return
	 */
	private VBox getDelete() {
		VBox centerBox = new VBox(); 
		 centerBox.setAlignment(Pos.TOP_CENTER);
		 centerBox.setMinHeight(300);
		 centerBox.setStyle("-fx-background-color: white");
		 
		// add title and subtitle instructions 
		    Text title = new Text("Delete airplane"); 
		    Text instructions = new Text("Select the airplaneID for the airplane you wish to delete, then click Enter");
		
		//add selection fields
		    GridPane gpane = new GridPane(); 
		    gpane.setAlignment(Pos.CENTER);
		    gpane.setPadding(new Insets(2,20,2,20)); 
			gpane.setHgap(5);
			gpane.setVgap(5);
		    ComboBox<Integer> cbAirID = new ComboBox(FXCollections.observableArrayList()); 
		    gpane.add(cbAirID,  0,  0);
	
		    centerBox.getChildren().addAll(title, instructions, gpane); 
		    
		   btnEnter.setOnAction(e->{
		   //clear combobox
			   cbAirID.valueProperty().set(null);
			   cbAirID.setItems(FXCollections.observableArrayList());
		   });
		   
		   btnCancel.setOnAction(e->{
			   cbAirID.valueProperty().set(null);
		   });
	
		    
	return centerBox; 
	}

} //End Subclass Aircraft