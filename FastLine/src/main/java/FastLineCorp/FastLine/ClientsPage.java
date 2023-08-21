package FastLineCorp.FastLine;

//Imports
import Database.Client.*;
import Database.DBConnection;
import Database.DBFinder;
import Database.Client.DBViewAllClient;
import Database.Client.DBAddClient;
import Database.Client.DBDeleteClient;
import Database.Client.DBAddClientAddress;
import Database.Client.DBUpdateClient;
import Database.Client.DBDeleteClientAddress;
import Database.Client.DBUpdateClientAddress;
import Database.Client.DBViewAllClientType;
import Database.Client.DBViewSelectClient;
import Database.Client.DBViewSelectedClientTypeByName;
import FastLineCorp.FastLine.Styles;
import FastLineCorp.FastLine.Validation;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
 * @Date: June 12, 2023
 * @Class: ClientsPage
 * @Description: This page will be the clients page. This page will be accessed
 *               once the client button is clicked from the homepage
 * 
 */

public class ClientsPage {
	static TextArea texReaOne = new TextArea();

    // classes 
    Styles clientstyle = new Styles();
        
	// Client address labels
	static Label lbName = new Label("Client Name");
	static Label lbAddress = new Label("Address");
	static Label lbCity = new Label("City");
	static Label lbState = new Label("State");
	static ComboBox<String> cbState;
	static Label lbZip = new Label("Zip");

	static TextField txName;
	static TextField txAddress;
	static TextField txCity;
	static TextField txState;
	static TextField txZip;

    //passed border pane from CAF. 
    BorderPane bPane = new BorderPane();
    
    //used for validation
    Validation valid=new Validation();
  //make buttons
    Button btnView = new Button("View");
    Button btnAdd = new Button("Add Client");
    Button btnEdit = new Button("Edit Client");
    Button btnDelete = new Button("Delete Client");
    Button btnEnter = new Button("Enter");
    Button btnCancel = new Button("Cancel");
    Button btnExit = new Button("Exit");

    

    /**
     * constructor pulls the border pane from CAF
     */
    ClientsPage(BorderPane bp) {
        bPane = bp;
		
    }

    /**
     * getPane - what gets called in caf to view clientsPage data 
     * @return 
     */
    public VBox getPane() {
        VBox vclients = new VBox();
        vclients.getChildren().addAll(clients());
        return vclients;
    }

    /**
     * clients - this is the main border pane for the clients page. 
     * @return 
     */
    private BorderPane clients() {
        BorderPane box = new BorderPane();
        
        //create a title pane for the top
        VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setSpacing(10);
        Text title = new Text("Clients");
        Text instruct = new Text("View Client Information Below:");
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
                    b.setStyle(clientstyle.btonbox);
                    b.setMinHeight(30);
                    b.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
                });
        //Exit button style
        btnExit.setStyle(clientstyle.redbton);
        btnExit.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
        btnExit.setMinHeight(0);

        //add buttons to button HBox
        buttonBox.getChildren().addAll(btnView, btnAdd, btnEdit, btnDelete, btnEnter,
                btnCancel, btnExit);

        //add title, center, and buttons to clients pane:
        box.setTop(titleBox);
        box.setCenter(getViewSelected()); //call a method to show db of clients  
        box.setBottom(buttonBox);

        //add actionables to change the setCenter based on button responses:
        btnView.setOnAction(e -> {
        	 box.setCenter(getViewLBs());     
        });
        btnAdd.setOnAction(e -> { 
        	box.setCenter(addPane());
           
         });
        btnEdit.setOnAction(e -> { 	
        	box.setCenter(addPaneUpdate());
        
        });
        btnDelete.setOnAction(e -> {
        	box.setCenter(deletePane()); 
        });
       
        btnExit.setOnAction(e -> {
            //clear whatever actions doing
            //return to just the viewClient page
            box.setCenter(getViewSelected());
        });

        return box;
    } 
 
    //getClientInfortxZip, getClientInfotx, getClientInfolb, getClientLBs all incorporated into addPane
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
		hboxv.setPadding(new Insets(3, 20, 3, 20));
		hboxv.setSpacing(150);
		hboxv.setPrefWidth(700);
		hboxv.setMaxWidth(900);
		hboxv.setStyle("-fx-background-color: white; -fx-border-color: black");
		Label name = new Label("Client Name");
		Label Address = new Label("Address");
		Label City = new Label("City");
		Label State = new Label("State");
		Label Zip = new Label("Zip Code");
		hboxv.getChildren().addAll(name, Address, City, State,Zip);
		return hboxv;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// VIEW ALL
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * This TextArea will display the output or result for the View All shipment information 
	 * it will allow users to view a summary of the shipments. Users will not be able to 
	 * change the shipment information from this shipment view.
	 * @return
	 */
	private ScrollPane getTextAreaOne() {
		DBViewAllClient viewAllClient = new DBViewAllClient();  //view all client information 
		ScrollPane box = new ScrollPane();
		box.setFitToWidth(true);
		box.setStyle("-fx-background-color: white; -fx-border-color: black"); 
				
		 GridPane gpane = new GridPane(); 
		 gpane.setStyle("-fx-background-color: white; -fx-border-color: black"); 
		 gpane.setPadding(new Insets(2,20,2,20));
		 gpane.setAlignment(Pos.TOP_CENTER); 
		 gpane.setHgap(110);
		 gpane.setVgap(5);
		 		 
		 int row = 1; 
		 int i =0; 
		 while(i < viewAllClient.getName().size()) {
			 Label gridName = new Label (String.valueOf(viewAllClient.getName().get(i))); 
			 Label gridAdress = new Label (String.valueOf(viewAllClient.getAddress1().get(i)) + " " +
			 String.valueOf(viewAllClient.getAddress2().get(i))); 
			 Label gridCity = new Label(String.valueOf(viewAllClient.getCity().get(i))); 
			 Label gridState = new Label(String.valueOf(viewAllClient.getState().get(i))); 
			 Label gridZip = new Label(String.valueOf(viewAllClient.getZip().get(i))); 
			 
			 gpane.add(gridName,  0,  row);
			 gpane.add(gridAdress, 1, row); 
			 gpane.add(gridCity,  2,  row);
			 gpane.add(gridState,  3,  row);
			 gpane.add(gridZip,  4,  row);
			 
			 row++; 
			 i++; 
		 }
		
		 box.setContent(gpane);
		return box;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// VIEW SELECTED
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * getViewSelected - the initial pane for the ViewClients page. Will hold a feature 
	 * to view an individual client with the DBViewSelectClient class 
	 * @return
	 */
	private VBox getViewSelected() {
	DBViewAllClient all = new DBViewAllClient(); // for filling the combo box
		
    VBox centerBox = new VBox();
    centerBox.setAlignment(Pos.TOP_CENTER);
    centerBox.setMinHeight(300);
    centerBox.setSpacing(5);
    centerBox.setStyle("-fx-background-color: white");
    
    // add title and subtitle instructions 
    Text title = new Text("View Selected Client"); 
    Text instructions = new Text("Use the scroll bar to select a client, then click SEARCH. \n"
    		+ "This will allow you to view all client information for selected client."); 
    
    // add a combobox and fill with all client names
    HBox selection = new HBox(); 
    selection.setSpacing(5); 
    selection.setAlignment(Pos.CENTER);
	ComboBox<String> clientSelect = new ComboBox<String>(FXCollections.observableArrayList(all.getName())); 
    clientSelect.setVisibleRowCount(5); 
    Button clientSearch = new Button("Search"); 
    selection.getChildren().addAll(clientSelect, clientSearch); 
    
    //grid of information: 
    GridPane grid = new GridPane(); 
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(11);
	grid.setVgap(5);
    Label lbName = new Label("Client Name: "); 
    grid.add(lbName, 0, 0);
    Label lbType = new Label ("Client Type: "); 
    grid.add(lbType, 0, 1);
    Label lbPhone = new Label("Client Phone: "); 
    grid.add(lbPhone, 0, 2);
    Label lbAddress = new Label("Address: "); 
    grid.add(lbAddress, 0, 3);
    Label lbCity = new Label("City: "); 
    grid.add(lbCity, 0, 4);
    Label lbState = new Label("State: "); 
    grid.add(lbState, 0, 5);
    Label lbZip = new Label("Zip: "); 
    grid.add(lbZip,  0,  6);
    
    Text txtSelectName = new Text(); 
	grid.add(txtSelectName, 1, 0);
	Text txtSelectType = new Text(); 
	grid.add(txtSelectType, 1, 1);
	Text txtSelectPhone = new Text(); 
	grid.add(txtSelectPhone, 1, 2);
	Text txtSelectAddress = new Text(); 
	grid.add(txtSelectAddress, 1, 3);
	Text txtSelectCity = new Text(); 
	grid.add(txtSelectCity, 1, 4);
	Text txtSelectState = new Text(); 
	grid.add(txtSelectState, 1, 5);
	Text txtSelectZip = new Text();
	grid.add(txtSelectZip, 1,  6); 
	
	 
   clientSearch.setOnAction(e->{
	   try {
		DBViewSelectClient view = new DBViewSelectClient(); //to view a select Client
	   	String entry = clientSelect.getValue().toString();
    	int id = all.getName().indexOf(entry); //get the index of arraylist where = entry 
    	view.viewSelected(all.getID().get(id));

    	txtSelectName.setText(view.getClientName());
    	txtSelectType.setText(view.getClientType());
    	txtSelectPhone.setText(view.getPhone());
    	txtSelectAddress.setText(view.getAddress1()+ " " + view.getAddress2());
    	txtSelectCity.setText(view.getCity());
    	txtSelectState.setText(view.getState());
    	txtSelectZip.setText(view.getZip());
    	
	   } catch(Exception ex) {
		   clientSelect.requestFocus(); 
	   }
	   
    });
   
   btnCancel.setOnAction(e->{
	   clientSelect.valueProperty().set(null); 
	   txtSelectName.setText(""); 
	   txtSelectType.setText("");
	   txtSelectPhone.setText("");
	   txtSelectAddress.setText("");
	   txtSelectCity.setText("");
	   txtSelectState.setText("");
	   txtSelectZip.setText("");
   });
    centerBox.getChildren().addAll(title, instructions, selection, grid);
    
    return centerBox; 
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ADD
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * addPane - pane for adding a client in the database. 
	 */
	private VBox addPane() {
		VBox box = new VBox(); 
		box.setAlignment(Pos.CENTER); 
		box.setSpacing(10);
		box.setPadding(new Insets(23,30,0,20));
		box.setStyle("-fx-background-color: white");		
		DBViewAllClientType dbtype = new DBViewAllClientType();
		//title and instructions 
		Text title = new Text("Add a new Client"); 
		Text instructions = new Text("Enter valid information for a client, and then press Enter"); 
		//labels
		Label lblName = new Label ("Client Name: "); 
		Label lblType = new Label ("Client Type: "); 
		Label lblPhone = new Label ("Client Phone Number: "); 
		Label lblAdd1 = new Label("Address 1: "); 
		Label lblAdd2 = new Label("Address 2: "); 
		Label lblCity = new Label("City: "); 
		Label lblState = new Label("State: "); 
		Label lblZip = new Label("Zip Code: "); 
		
		//style labels
		Arrays.asList(lblName, lblType, lblPhone, lblAdd1, lblAdd2, lblCity, lblState, lblZip).stream().map((b)->{
			b.setStyle(clientstyle.btonbox); 
			return b; 
		}); 
				
		//entry fields
		TextField txtName = new TextField(); 
		ComboBox<String> cbType = new ComboBox<String>(FXCollections.observableArrayList(dbtype.getClientType())); //combobox with type names 
		TextField txtPhone = new TextField(); 
		TextField txtAdd1 = new TextField(); 
		TextField txtAdd2 = new TextField(); 
		TextField txtCity = new TextField(); 
		ComboBox<String> cbState = new ComboBox<String>();
    	cbState.getItems().addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL",
				"IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH",
				"NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
				"VA", "WA", "WV", "WI", "WY");
    	TextField txtZip = new TextField(); 
    	
    	//add input values into a gridpane
    	GridPane grid = new GridPane(); 
    	grid.setAlignment(Pos.CENTER);
    	grid.setHgap(10);
    	grid.setVgap(4);
    	grid.add(lblName, 0, 0);
    	grid.add(lblType, 0, 1);
    	grid.add(lblPhone, 0, 2);
    	grid.add(lblAdd1, 0, 3);
    	grid.add(lblAdd2, 0, 4);
    	grid.add(lblCity, 0, 5);
    	grid.add(lblState, 0, 6);
    	grid.add(lblZip, 0, 7);
    	
    	grid.add(txtName, 1, 0);
    	grid.add(cbType, 1, 1);
    	grid.add(txtPhone, 1, 2);
    	grid.add(txtAdd1, 1, 3);
    	grid.add(txtAdd2, 1, 4);
    	grid.add(txtCity, 1, 5);
    	grid.add(cbState, 1, 6);
    	grid.add(txtZip, 1, 7);
    	
    	box.getChildren().addAll(title,instructions,grid); 
    	btnEnter.setOnAction(e->{
    		
    		//client name validation 
    		String head="Name";
    		String cont="invalid client name entry";    		
    		String nameValid;
    				if(valid.isString(txtName.getText().toString())) {
    				nameValid =txtName.getText();	
    				} else {
    					valid.error.setError(head, cont);
    					nameValid ="";
    				}
    		head="Client type";
    		cont="invalid client type entry";
    		
    		//get clientTypeID from the client Type value
    		String typeString = cbType.getValue();    		 
    		int typeIndex = dbtype.getClientType().indexOf(typeString);
    		int type = valid.intChecker(dbtype.getID().get(typeIndex).toString(), head, cont);
    		//validate client type id
    		int clientTypeValid = valid.intChecker(String.valueOf(type), head, cont); 
    		System.out.println("client type: " + typeString);
    		System.out.println("index: " + typeIndex); 
    		System.out.println("type id: " + type); 
    		
    		//phone number validation
    		head="Client Phone Number";
    		cont="invalid phone number entry";
    		String phoneValid = valid.checkPhoneNumber(txtPhone.getText(), head, cont); 
    		
    		//client Address - must have Address line 1, but dont need add2
    		head="Client Address 1";
    		cont="Must have a client address";
    		String add1 = txtAdd1.getText(); 
    		//check string length
    		int add1L=valid.stringLength(add1);
    		if (add1L==0) {
    			valid.error.setError(head, cont);
    		}
    		String add2 = txtAdd2.getText(); 
    		
    		//client City validation 
    		String cityValid;
    		head="Client City";
    		cont="invalid city entry";
    		if(valid.isString(txtCity.getText().toString())) {
				cityValid =txtCity.getText();	
				} else {
					valid.error.setError(head, cont);
			     cityValid ="";
				}
    		
    		//client state validation 
    		String stateValid = cbState.getValue(); 
    		
    		//client zip code validation
    		head="Client Zip";
    		cont="Invalid zip code entry";
    		String zipValid = valid.zipCodeUS(txtZip.getText(), head, cont);
    		if (nameValid=="") {
    			
    			txtName.clear();    			
    		}else if(clientTypeValid==0) {
    			valid.error.setError("Type", "Error");
    		}else if(phoneValid=="") {
    			txtPhone.clear();
    		}else if(add1L==0) {
    			txtAdd1.clear();			
    		}else if(cityValid=="") {
    			txtCity.clear();
    		}else if(zipValid=="") {
    			txtZip.clear();
    		}
    		else {//good send to DB
    			DBAddClient add = new DBAddClient();
    			add.insertSQL(nameValid, clientTypeValid, phoneValid);
    			//find new clientID after adding the new client so you can use the ID to add clientAddress
        		DBFinder find = new DBFinder(); 
        		int id = find.findClientID(nameValid, clientTypeValid, phoneValid);
    			DBAddClientAddress ca = new DBAddClientAddress(add1, add2, cityValid, stateValid, zipValid, id);
    			//clear entry fields
    			cbType.valueProperty().set(null);
        		txtName.clear(); 
        		txtPhone.clear(); 
        		txtAdd1.clear(); 
        		txtAdd2.clear();
        		txtCity.clear();
        		cbState.valueProperty().set(null);
        		txtZip.clear(); 
    		}
 	
    	});
    	
    	//cancel button clears all fields 
    	btnCancel.setOnAction(e->{
    		cbType.valueProperty().set(null);
    		txtName.clear(); 
    		txtPhone.clear(); 
    		txtAdd1.clear(); 
    		txtAdd2.clear();
    		txtCity.clear();
    		cbState.valueProperty().set(null);
    		txtZip.clear(); 
    	});
    	    	
		return box; 
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// UPDATE
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
 * addPaneUpdate - pane for updating a client in the database 
 */
	private VBox addPaneUpdate() {
		VBox box = new VBox(); 
		box.setAlignment(Pos.CENTER); 
		box.setSpacing(10);
		box.setPadding(new Insets(23,30,0,20));
		box.setStyle("-fx-background-color: white");
		//update client classes
		DBViewAllClient viewAll = new DBViewAllClient(); //used to get arraylist		 
		DBUpdateClient updateClient = new DBUpdateClient();
		DBViewAllClientType viewType = new DBViewAllClientType();
		DBViewSelectClient select = new DBViewSelectClient();
		DBViewSelectedClientTypeByName selectClientType = new DBViewSelectedClientTypeByName();
		
		//call the get client id from viewAll 
		ComboBox<Integer> cbClientID = new ComboBox<Integer>(FXCollections.observableArrayList(viewAll.getID()));
		Button btSelectClient = new Button("Select Client"); 
		
		
		//title and instructions 
		Text title = new Text("Update Client"); 
		Text instructions = new Text("Enter Valid Client information and press Enter."); 
		//labels
		Label lblClientID =new Label("Client ID: ");
		Label lblName = new Label ("Client Name: "); 
		Label lblType = new Label ("Client Type: "); 
		Label lblPhone = new Label ("Client Phone Number: "); 
	
		Label lblAdd1 = new Label("Address 1: "); 
		Label lblAdd2 = new Label("Address 2: "); 
		Label lblCity = new Label("City: "); 
		Label lblState = new Label("State: "); 
		Label lblZip = new Label("Zip Code: "); 
		//style labels
		Arrays.asList(lblClientID,lblName, lblType, lblPhone, lblAdd1, lblAdd2, lblCity, lblState, lblZip).stream().map((b)->{
			b.setStyle(clientstyle.btonclientbox); 
			return b; 
		}); 
				
		//entry fields
		
		TextField txtName = new TextField(); 	
		ComboBox<String> cbType = new ComboBox<String>(FXCollections.observableArrayList(viewType.getClientType())); //combobox with type names 
		TextField txtPhone = new TextField(); 
		TextField txtAdd1 = new TextField(); 
		TextField txtAdd2 = new TextField(); 
		TextField txtCity = new TextField(); 
		ComboBox<String> cbState = new ComboBox<String>();
    	cbState.getItems().addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL",
				"IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH",
				"NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
				"VA", "WA", "WV", "WI", "WY");
    	TextField txtZip = new TextField(); 
    	    	
    	//add input values into a gridpane
    	GridPane grid = new GridPane(); 
    	grid.setAlignment(Pos.CENTER);
    	grid.setHgap(11);
    	grid.setVgap(5);
    	
    	grid.add(lblClientID, 0,0);    	
    	grid.add(lblName, 0, 1);
    	grid.add(lblType, 0, 2);
    	grid.add(lblPhone, 0, 3);
    	grid.add(lblAdd1, 0, 4);
    	grid.add(lblAdd2, 0, 5);
    	grid.add(lblCity, 0, 6);
    	grid.add(lblState, 0, 7);
    	grid.add(lblZip, 0, 8);
    	
    	grid.add(cbClientID, 1,0); //add combobox and select button
    	grid.add(btSelectClient, 2,0);
    	grid.add(txtName, 1, 1);
    	grid.add(cbType, 1, 2);
    	grid.add(txtPhone, 1, 3);
    	grid.add(txtAdd1, 1, 4);
    	grid.add(txtAdd2, 1, 5);
    	grid.add(txtCity, 1, 6);
    	grid.add(cbState, 1, 7);
    	grid.add(txtZip, 1, 8);
    	    	
    	box.getChildren().addAll(title,instructions,grid); 
    	
    	//set the other textfields to whatever clientID is entered
    	btSelectClient.setOnAction(e->{
    		DBViewSelectClient vsc = new DBViewSelectClient(); 
    		//clear previous values
    		txtName.setText(""); 
    		txtPhone.setText(""); 
    		txtAdd1.setText(""); 
    		txtAdd2.setText("");
    		txtCity.setText("");
    		cbState.valueProperty().set(null);
    		txtZip.setText(""); 
    		
    		//get new selection
    		vsc.viewSelected(cbClientID.getValue());
    		
    		//fill with new values
    		txtName.setText(vsc.getClientName());
    		cbType.valueProperty().set(vsc.getClientType());
    		txtPhone.setText(vsc.getPhone());
    		txtAdd1.setText(vsc.getAddress1());
    		txtAdd2.setText(vsc.getAddress2());
    		txtCity.setText(vsc.getCity());
    		cbState.setValue(vsc.getState());
    		txtZip.setText(vsc.getZip()); 
    	});
    	
    	
    	btnEnter.setOnAction(e->{
    		//strings for validation
    		String head="Name";
    		String cont="Not a string";
    		
    		//variables for SQL stored procedure
    		String name;
			if(valid.isString(txtName.getText().toString())) {
			name =txtName.getText();	
			} else {
				valid.error.setError(head, cont);
				name ="";
			}
    		
    		head="Client type";
    		cont="Not a int";
    		String cType = cbType.getValue().toString();
    		selectClientType.viewSelected(cType);
    		int type = valid.intChecker(Integer.toString(selectClientType.getID()), head, cont); 
    		head="Client Phone Number";
    		cont="Not Correct";
    		String phone = valid.checkPhoneNumber(txtPhone.getText(), head, cont); 
    		//add client IF 
    		head="Client Address 1";
    		cont="Empty";
    		String add1 = txtAdd1.getText(); 
    		//check string length
    		int add1L=valid.stringLength(add1);
    		if (add1L==0) {
    			valid.error.setError(head, cont);
    		}
    		//address 2 can be empty
    		String add2 = txtAdd2.getText(); 
    		    		
    		head="Client City";
    		cont="Not a String";
    		String city;
			if(valid.isString(txtCity.getText().toString())) {
				city =txtCity.getText();	
			} else {
				valid.error.setError(head, cont);
				city ="";
			}
    		    		
    		String state = cbState.getValue(); 
    		head="Client Zip";
    		cont="Not Correct";
    		String zip = valid.zipCodeUS(txtZip.getText(), head, cont);
    		int clientID = cbClientID.getValue().intValue();
    		
    		if (name=="") {
    			//error
    			txtName.clear();    			
    		}else if(type==0) {
    			valid.error.setError("Type", "Error");
    			
    		}else if(phone=="") {
    			txtPhone.clear();
    		}else if(add1L==0) {
    			txtAdd1.clear();
    		}else if(city=="") {
    			txtCity.clear();    			
    		}else if(zip=="") {
    			txtZip.clear();
    		}else {//good send to DB
    		updateClient.updateC(clientID, name, type, phone);	
    		//get index of clientID  
    		select.viewSelected(clientID);
    		int clientAddressID =select.getAddressID();	
    			if(clientAddressID == 0) {
    				DBAddClientAddress addAddress = new DBAddClientAddress(add1, add2, city, state, zip, clientID); 
    			}
    			else {
    				System.out.println("address ID = " + clientAddressID); 
    		    		
    				DBUpdateClientAddress updateClientAddress = new DBUpdateClientAddress();
    				//update client address Class
    				updateClientAddress.updateClientA(clientAddressID, clientID, add1, add2, city, state, zip);
    			}
    			 
    	    	
        		//clear text fields    		
        		cbClientID.valueProperty().set(null);
        		cbType.valueProperty().set(null);
        		txtName.clear(); 
        		txtPhone.clear(); 
        		txtAdd1.clear(); 
        		txtAdd2.clear();
        		txtCity.clear();
        		cbState.valueProperty().set(null);
        		txtZip.clear(); 
    		}
    	    	   
    	
    	});
    	
    	btnCancel.setOnAction(e->{
    		cbClientID.valueProperty().set(null);
    		cbType.valueProperty().set(null);
    		txtName.clear(); 
    		txtPhone.clear(); 
    		txtAdd1.clear(); 
    		txtAdd2.clear();
    		txtCity.clear();
    		cbState.valueProperty().set(null);
    		txtZip.clear(); 
    	});
    	    	
		return box; 
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// DELETE
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * deletePane - method for deleting a client using the DBDeleteClient class
	 * @return
	 */
	private VBox deletePane() {
		DBViewAllClient view = new DBViewAllClient(); //for arraylist of clients
		VBox box = new VBox(); 
		box.setAlignment(Pos.TOP_CENTER); 
		box.setSpacing(10);
		box.setPadding(new Insets(23,30,0,20));
		box.setMinHeight(300);
		box.setStyle("-fx-background-color: white");
		//add client classes
		DBAddClient add = new DBAddClient();
		//title and instructions 
		Text title = new Text("Delete a Client"); 
		Text instructions = new Text("Select a client, then click Enter"); 
		
		ComboBox<String> cbClients = new ComboBox<String>(FXCollections.observableArrayList(view.getName())); 
		GridPane gpane = new GridPane(); 
		gpane.setHgap(11);
    	gpane.setVgap(5);
		gpane.setAlignment(Pos.CENTER);
		gpane.add(cbClients, 0,0);
		
		box.getChildren().addAll(title,instructions,gpane); 
		btnEnter.setOnAction(e->{
			DBDeleteClient delete = new DBDeleteClient(); 
			DBDeleteClientAddress deleteAdd = new DBDeleteClientAddress(); 
			
			//get client ID based on name, delete client
			int index = view.getName().indexOf(cbClients.getValue()); 
			int id = view.getID().get(index); 
			
			//get addressID to delete clientAddress
			int addressIndex = view.getID().indexOf(id); 
			int addressID = view.getAddressID().get(addressIndex); 
			
			//delete address, then delete client
			deleteAdd.deleteClientAddress(addressID); 	
			System.out.println("id - " + id); 
			delete.deleteClient(id);
					
	
	
			//clear combobox
			DBViewAllClient viewAgain = new DBViewAllClient();  
			cbClients.setItems(FXCollections.observableArrayList(viewAgain.getName()));
			cbClients.valueProperty().set(null);
			deletePane(); 
		});
		
		btnCancel.setOnAction(e->{
			cbClients.valueProperty().set(null);
		});
		return box; 
	}
  
} //End Subclass ClientsPage


