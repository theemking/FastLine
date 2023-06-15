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
 * @Description: This page will be the clients page. This page will be accessed once the client button is clicked from the homepage
 * 
 */
public class ClientsPage {
	
	//Declaring textarea
	static TextArea texReaOne = new TextArea();

	//styles class
	Styles clientstyle =new Styles();
	
	//client addtess labels
	static Label lbName = new Label("Client Name");
	static Label lbAddress=new Label("Address");
	static Label lbCity = new Label("City");
	static Label lbState = new Label("State");
	static ComboBox<String> cbState;
	static Label lbZip = new Label("Zip");
	//Text field
	static TextField txName;
	static TextField txAddress;
	static TextField txCity;
	static TextField State;
	static TextField txtZip;	
	
	//adding border pane to allow access from ControlCorp
	BorderPane bPane=new BorderPane();
	
	//button
	Button btonView = new Button("View");
	Button btonAdd = new Button("Add Client");
	Button btonEdit = new Button("Edit Cilent");
	Button btonDelete = new Button("Delete Client");
	Button btonEnter = new Button("Enter");
	Button btonCancel = new Button("Cancel");
	Button btonExit = new Button("Exit");
	
	/**
	 * ControlCorp constructor
	 * This constructor allows the client page to be access from the control page when the user click the client button
	 */
	ClientsPage(BorderPane clientsp){
		bPane=clientsp;
	}
	/**
	 * getPane()
	 * This function allow access to clients data from the ControlCorp page
	 */
	public VBox getPane() {
		VBox vclients = new VBox();
		vclients.getChildren().addAll(clients());
		return vclients;
	}
	/**
	 * clients()
	 * This is the border pane for the client page.
	 * This bPane will be accessed from the ControlCorp page
	 * @return
	 */
	private BorderPane clients() {
		BorderPane boxes = new BorderPane();
		//Setting up the title pane
		VBox titlebox = new VBox();
		titlebox.setAlignment(Pos.CENTER);
		titlebox.setSpacing(10);
		Text title = new Text("Clients");
		Text secondTitle = new Text("View Client Information Below");
		//adding style
		title.setFill(Color.BLUE);
		title.setStrokeWidth(2);
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));
		secondTitle.setFill(Color.RED);
		secondTitle.setStrokeWidth(2);
		secondTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
		titlebox.getChildren().addAll(titlebox,secondTitle);
		
		//adding HBox - button
		HBox hbtonBox =new HBox();
		hbtonBox.setAlignment(Pos.CENTER);
		hbtonBox.setPadding(new Insets(20,0,0,0));
		hbtonBox.setSpacing(20);
		
		//adding style buttons
		Arrays.asList(btonView,btonAdd,btonEdit,btonDelete,btonEnter,btonCancel,btonExit).forEach((bton)->{
			bton.setStyle(clientstyle.clientBton);
			bton.setMinHeight(30);
			bton.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		});
		//Exit button style
		btonExit.setStyle(clientstyle.redExit);
		btonExit.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		btonExit.setMinHeight(0);
		
		//adding buttons to HBox
		hbtonBox.getChildren().addAll(btonView,btonAdd,btonEdit,btonDelete,btonEnter,btonCancel,btonExit);
		
		//adding title box
		boxes.setTop(title);
		boxes.setCenter(getSelectView());
		boxes.setBottom(hbtonBox);
		
		//adding actions so that users can click on buttons on this page and get a response
		btonView.setOnAction(e ->{boxes.setCenter(getLabels());});
		btonAdd.setOnAction(e ->{boxes.setCenter(addClient());});	
		btonEdit.setOnAction(e ->{boxes.setCenter(updateClient());});
		btonDelete.setOnAction(e ->{boxes.setCenter(deleteClient());});
		btonExit.setOnAction(e ->{boxes.setCenter(getSelectView());});
		
		return boxes;
	}
	/**
	 * getLabels()
	 * This function will display the header section and the TextArea for the output.
	 * @return
	 */
	private VBox getLabels() {
		VBox vboxlb =new VBox();
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
		hboxv.setPadding(new Insets(3,20,3,20));
		hboxv.setSpacing(150);
		hboxv.setPrefWidth(700);
		hboxv.setMaxWidth(900);
		hboxv.setStyle(clientstyle.viewLabel);
		Label Name = new Label("Client Name");
		Label Address = new Label("Address");
		Label City = new Label("City");
		Label State = new Label("State");
		Label Zip = new Label("Zip Code");
		hboxv.getChildren().addAll(Name,Address,City,State,Zip);
		return hboxv;
	}
	/**
	 * This section will display the result for the View All shipment information 
	 * This section will only allow users to view a summary of the shipments. Users will not be able to 
	 * change the shipment information from this shipment view.
	 * @return
	 */
	private ScrollPane getTextAreaOne() {
		ScrollPane Areabox = new ScrollPane();
		Areabox.setFitToWidth(true);
		Areabox.setStyle(clientstyle.clientScroll);
		//adding gridpane
		GridPane gPane = new GridPane();
		gPane.setStyle(clientstyle.clientScroll);
		gPane.setPadding(new Insets(2,20,2,20));
		gPane.setAlignment(Pos.TOP_CENTER);
		gPane.setHgap(110);
		gPane.setVgap(5);
		
//		Label gName = new Label("Name");
//		Label gAddress = new Label("Address");
//		Label gCity = new Label("City");
//		Label gState = new Label("State");
//		Label gZip = new Label("Zip");
//		
//		gPane.getChildren().addAll(gName,gAddress,gCity,gState,gZip);
		
		Areabox.setContent(gPane);
		return Areabox;
	}
	/**
	 * getSelectView()
	 * This function will allow users to view a client information. 
	 * The information will be pull from the SQL Database
	 */
	private VBox getSelectView() {
		VBox centerSection =new VBox();
		centerSection.setAlignment(Pos.TOP_CENTER);
		centerSection.setMinHeight(300);
		centerSection.setSpacing(5);
		centerSection.setStyle(clientstyle.bacgroundWHITE);
		//Adding header
		Text header = new Text("View Selected Client");
		//adding direction
		Text subHeader = new Text("Please select a client, then click SEARCH. \n"
				+ "You will be able to view all information for the Client you select");
		//adding combobox for client info
		HBox select = new HBox();
		select.setSpacing(5);
		select.setAlignment(Pos.CENTER);
		ComboBox clientSelect = new ComboBox(FXCollections.observableArrayList());
		clientSelect.setVisibleRowCount(5);
		Button ClSearch = new Button("Search");
		select.getChildren().addAll(clientSelect, ClSearch);
		
		//adding grid information
		GridPane gPane =new GridPane(); 
		gPane.setAlignment(Pos.CENTER);
		gPane.setHgap(11);
		gPane.setVgap(5);
	    Label lbName = new Label("Client Name: "); 
	    gPane.add(lbName, 0, 0);
	    Label lbType = new Label ("Client Type: "); 
	    gPane.add(lbType, 0, 1);
	    Label lbPhone = new Label("Client Phone: "); 
	    gPane.add(lbPhone, 0, 2);
	    Label lbAddress = new Label("Address: "); 
	    gPane.add(lbAddress, 0, 3);
	    Label lbCity = new Label("City: "); 
	    gPane.add(lbCity, 0, 4);
	    Label lbState = new Label("State: "); 
	    gPane.add(lbState, 0, 5);
	    Label lbZip = new Label("Zip: "); 
	    gPane.add(lbZip,  0,  6);		
		
	    Text txSelectName = new Text(); 
	    gPane.add(txSelectName, 1, 0);
		Text txSelectType = new Text(); 
		gPane.add(txSelectType, 1, 1);
		Text txSelectPhone = new Text(); 
		gPane.add(txSelectPhone, 1, 2);
		Text txSelectAddress = new Text(); 
		gPane.add(txSelectAddress, 1, 3);
		Text txSelectCity = new Text(); 
		gPane.add(txSelectCity, 1, 4);
		Text txSelectState = new Text(); 
		gPane.add(txSelectState, 1, 5);
		Text txSelectZip = new Text();
		gPane.add(txSelectZip, 1,  6); 

		   
		   btonCancel.setOnAction(e->{
			   clientSelect.valueProperty().set(null); 
			   txSelectName.setText(""); 
			   txSelectType.setText("");
			   txSelectPhone.setText("");
			   txSelectAddress.setText("");
			   txSelectCity.setText("");
			   txSelectState.setText("");
			   txSelectZip.setText("");
		   });
		   
		centerSection.getChildren().addAll(header,subHeader,select,gPane);
		return centerSection;
	}
	/**
	 * AddClient
	 * This function will be used to add clients. Users will be able to access the add button from the client page
	 * @return
	 */
	private VBox addClient() {
		VBox vbadd = new VBox();
		vbadd.setAlignment(Pos.CENTER);
		vbadd.setSpacing(10);
		vbadd.setPadding(new Insets(23,30,0,20));
		vbadd.setStyle(clientstyle.bacgroundWHITE);
		//adding header
		Text header =new Text("Add a new Client");
		Text subHeader =new Text("Enter Client's information, then press Enter");
		//adding labels
		Label lbName =new Label("Client Name: ");
		Label lbType = new Label("Client Type");
		Label lbPhone = new Label("Phone Number");
		Label lbAddOne = new Label("Address 1: ");
		Label lbAddTwo = new Label("Address 2: ");
		Label lbCity = new Label("City: ");
		Label lbState = new Label("State: ");
		Label lbZip = new Label("Zip Code: ");
		//Adding style to label
		Arrays.asList(lbName,lbType,lbPhone,lbAddOne,lbAddTwo,lbCity,lbState,lbZip).stream().map((col) ->{
			col.setStyle(clientstyle.btonclientbox);
			return col;
		});
		//entry fields
		TextField txtName = new TextField(); 
		ComboBox<String> cbType = new ComboBox(FXCollections.observableArrayList()); 
		TextField txtPhone = new TextField(); 
		TextField txtAdd1 = new TextField(); 
		TextField txtAdd2 = new TextField(); 
		TextField txtCity = new TextField(); 
		ComboBox<String> cbState = new ComboBox();
    	cbState.getItems().addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL",
				"IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH",
				"NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
				"VA", "WA", "WV", "WI", "WY");
    	
    	TextField txtZip = new TextField(); 
    	
    	//add input for address information
    	GridPane gPane = new GridPane(); 
    	gPane.setAlignment(Pos.CENTER);
    	gPane.setHgap(10);
    	gPane.setVgap(4);
    	gPane.add(lbName, 0, 0);
    	gPane.add(lbType, 0, 1);
    	gPane.add(lbPhone, 0, 2);
    	gPane.add(lbAddOne, 0, 3);
    	gPane.add(lbAddTwo, 0, 4);
    	gPane.add(lbCity, 0, 5);
    	gPane.add(lbState, 0, 6);
    	gPane.add(lbZip, 0, 7);
    	
    	gPane.add(txtName, 1, 0);
    	gPane.add(cbType, 1, 1);
    	gPane.add(txtPhone, 1, 2);
    	gPane.add(txtAdd1, 1, 3);
    	gPane.add(txtAdd2, 1, 4);
    	gPane.add(txtCity, 1, 5);
    	gPane.add(cbState, 1, 6);
    	gPane.add(txtZip, 1, 7);
    	
    	vbadd.getChildren().addAll(header,subHeader,gPane); 
    	btonEnter.setOnAction(e->{
    		
    		//client name validation 
    		String head="Name";
    		String subHead="invalid client name entry";    		
    		String nameValid;
    		head="Client type";
    		subHead="invalid client type entry";
    		
    		//get clientTypeID from the client Type value
    		String typeString = cbType.getValue();    		 
    		//validate client type id
    		System.out.println("client type: " + typeString);
    		System.out.println("index: " ); 
    		System.out.println("type id: "); 
    		
    		//phone number validation
    		head="Client Phone Number";
    		subHead="invalid phone number entry";
    		
    		//client Address - must have Address line 1, but dont need add2
    		head="Client Address 1";
    		subHead="Must have a client address";
    		String add1 = txtAdd1.getText(); 
    		//check string length
     	 
    		String add2 = txtAdd2.getText(); 
    		
    		//client City validation 
    		String cityValid;
    		head="Client City";
    		subHead="invalid city entry";
 
    		
    		//client state validation 
    		String Valid = cbState.getValue(); 
    		
    		//client zip code validation
    		head="Client Zip";
    		subHead="Invalid zip code entry";
   
    //clear entry fields
    			cbType.valueProperty().set(null);
        		txtName.clear(); 
        		txtPhone.clear(); 
        		txtAdd1.clear(); 
        		txtAdd2.clear();
        		txtCity.clear();
        		cbState.valueProperty().set(null);
        		txtZip.clear(); 
    		
 	
    	});
    	
    	//cancel button toclears all fields 
    	btonCancel.setOnAction(e->{
    		cbType.valueProperty().set(null);
    		txtName.clear(); 
    		txtPhone.clear(); 
    		txtAdd1.clear(); 
    		txtAdd2.clear();
    		txtCity.clear();
    		cbState.valueProperty().set(null);
    		txtZip.clear(); 
    	});	
		
		
		return vbadd;
	}
	/**
	 * updateClient()
	 * The function will update the database and refresh the client view
	 * @return
	 */
	private VBox updateClient() {
		VBox updatevb = new VBox();
		updatevb.setAlignment(Pos.CENTER);
		updatevb.setSpacing(10);
		updatevb.setPadding(new Insets(23,30,0,20));
		
		//ComboBox to call the client ID from view all
		ComboBox<Integer> VAClientID = new ComboBox(FXCollections.observableArrayList());
		Button btonSelectClient = new Button("Select Client");
		
		//Header
		Text header = new Text("Update Client");
		Text seconHeader = new Text("Enter client information and press Enter");
		//creating labels
		//labels
		Label lbClientID =new Label("Client ID: ");
		Label lbName = new Label ("Client Name: "); 
		Label lbType = new Label ("Client Type: "); 
		Label lbPhone = new Label ("Phone Number: "); 
	
		Label lbAdd1 = new Label("Address 1: "); 
		Label lbAdd2 = new Label("Address 2: "); 
		Label lbCity = new Label("City: "); 
		Label lbState = new Label("State: "); 
		Label lbZip = new Label("Zip Code: "); 
		//Adding style to all labels
		Arrays.asList(lbClientID,lbName, lbType, lbPhone, lbAdd1, lbAdd2, lbCity, lbState, lbZip).stream().map((col)->{
			col.setStyle(clientstyle.btonbox); 
			return col; 
		}); 
	
		//adding entry fields
		
				TextField txName = new TextField(); 	
				ComboBox<String> cbType = new ComboBox(FXCollections.observableArrayList());  
				TextField txPhone = new TextField(); 
				TextField txAdd1 = new TextField(); 
				TextField txAdd2 = new TextField(); 
				TextField txCity = new TextField(); 
				ComboBox<String> cbState = new ComboBox();
		    	cbState.getItems().addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL",
						"IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH",
						"NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
						"VA", "WA", "WV", "WI", "WY");
		    	TextField txtZip = new TextField(); 
		    	    	
		    	//adding input for gridpane
		    	GridPane gPane = new GridPane(); 
		    	gPane.setAlignment(Pos.CENTER);
		    	gPane.setHgap(11);
		    	gPane.setVgap(5);
		    	
		    	gPane.add(lbClientID, 0,0);    	
		    	gPane.add(lbName, 0, 1);
		    	gPane.add(lbType, 0, 2);
		    	gPane.add(lbPhone, 0, 3);
		    	gPane.add(lbAdd1, 0, 4);
		    	gPane.add(lbAdd2, 0, 5);
		    	gPane.add(lbCity, 0, 6);
		    	gPane.add(lbState, 0, 7);
		    	gPane.add(lbZip, 0, 8);
		    	
		    	 //This combobox will allow users to select button
		    	gPane.add(VAClientID, 1,0);
		    	gPane.add(btonSelectClient, 2,0);
		    	gPane.add(txName, 1, 1);
		    	gPane.add(cbType, 1, 2);
		    	gPane.add(txPhone, 1, 3);
		    	gPane.add(txAdd1, 1, 4);
		    	gPane.add(txAdd2, 1, 5);
		    	gPane.add(txCity, 1, 6);
		    	gPane.add(cbState, 1, 7);
		    	gPane.add(txtZip, 1, 8);
		    	    	
		    	updatevb.getChildren().addAll(header,seconHeader,gPane); 
		    	
		    	//set this textfields to match the clientID entered
		    	btonSelectClient.setOnAction(e->{
		    		//clear previous values
		    		txName.setText(""); 
		    		txPhone.setText(""); 
		    		txAdd1.setText(""); 
		    		txAdd2.setText("");
		    		txCity.setText("");
		    		cbState.valueProperty().set(null);
		    		txtZip.setText(""); 
		    		
		    		//get new selection
		    		
		    		//fill with new values
		    		txName.setText("Name");
		    		cbType.valueProperty().set("Type");
		    		txPhone.setText("Phone");
		    		txAdd1.setText("ADDRESS 1");
		    		txAdd2.setText("aDDRESS 2");
		    		txCity.setText("cITY");
		    		cbState.setValue("getState");
		    		txtZip.setText("getZip"); 
		    	});		
		    	btonEnter.setOnAction(e->{
		    		//strings that will validate user data
		    		String head="Name";
		    		String notString="Not a string";
		    		
		    		//variables for SQL stored procedure
		    		String name;		
		    		
		    		head="Client type";
		    		notString="Not a int";
		    		String cType = cbType.getValue().toString();
		    		head="Client Phone Number";
		    		notString="Not Correct";
		    		//add client IF 
		    		head="Client Address 1";
		    		notString="Empty";
		    		String add1 = txAdd1.getText(); 
		    		//check string length
		  
		    		//address 2 can be empty
		    		String add2 = txAdd2.getText(); 
		    		    		
		    		head="Client City";
		    		notString="Not a String";
		    		String city;
		    		    		
		    		String state = cbState.getValue(); 
		    		head="Client Zip";
		    		notString="Not Correct";
		    		int clientID = VAClientID.getValue().intValue();
		   
		        		//clear text fields    		
		    		VAClientID.valueProperty().set(null);
		        		cbType.valueProperty().set(null);
		        		txName.clear(); 
		        		txPhone.clear(); 
		        		txAdd1.clear(); 
		        		txAdd2.clear();
		        		txCity.clear();
		        		cbState.valueProperty().set(null);
		        		txtZip.clear(); 

		    	});
		    	
		    	btonCancel.setOnAction(e->{
		    		VAClientID.valueProperty().set(null);
		    		cbType.valueProperty().set(null);
		    		txName.clear(); 
		    		txPhone.clear(); 
		    		txAdd1.clear(); 
		    		txAdd2.clear();
		    		txCity.clear();
		    		cbState.valueProperty().set(null);
		    		txtZip.clear(); 
		    	});
		return updatevb;
	}
	/**
	 * deleteClient() 
	 * This method will display delete button to allow users to delete a client
	 * @return
	 */
	private VBox deleteClient() {
		VBox vbDel = new VBox(); 
		vbDel.setAlignment(Pos.TOP_CENTER); 
		vbDel.setSpacing(10);
		vbDel.setPadding(new Insets(23,30,0,20));
		vbDel.setMinHeight(300);
		vbDel.setStyle(clientstyle.bacgroundWHITE);
		//add client classes
		//title and instructions 
		Text header = new Text("Delete a Client"); 
		Text subHeader = new Text("Select a client, then click Enter"); 
		
		ComboBox<String> cbClients = new ComboBox(FXCollections.observableArrayList()); 
		GridPane gpane = new GridPane(); 
		gpane.setHgap(11);
    	gpane.setVgap(5);
		gpane.setAlignment(Pos.CENTER);
		gpane.add(cbClients, 0,0);
		
		vbDel.getChildren().addAll(header,subHeader,gpane); 
		btonEnter.setOnAction(e->{
	
					
	
	
			//clear combobox
			cbClients.setItems(FXCollections.observableArrayList());
			cbClients.valueProperty().set(null);
			deleteClient(); 
		});
		
		btonCancel.setOnAction(e->{
			cbClients.valueProperty().set(null);
		});
		return vbDel; 
	}	
	
	
	
	
	
	
	
	
	
	
	
	

}
