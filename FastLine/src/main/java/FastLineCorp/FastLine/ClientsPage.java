package FastLineCorp.FastLine;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.TextArea;
import java.awt.TextField;
import javafx.collections.FXCollections;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
//test
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
	ClientsPage(BorderPane clientps){
		bPane=clientps;
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
		btonExit.setMinHeight(10);
		
		//adding buttons to HBox
		hbtonBox.getChildren().addAll(btonView,btonAdd,btonEdit,btonDelete,btonEnter,btonCancel,btonExit);
		
		//adding title box
		boxes.setTop(title);
		boxes.setCenter(getSelectView());
		boxes.setBottom(hbtonBox);
		
		//adding actions so that users can click on buttons on this page and get a response
		btonView.setOnAction(e ->{boxes.setCenter(getLabels());});
		btonAdd.setOnAction(e ->{boxes.setCenter(addClient());});	
	//	btonEdit.setOnAction(e ->{boxes.setCenter(updateClient());});
	//	btonDelete.setOnAction(e ->{boxes.setCenter(deleteClient());});
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
	//	DBViewAllClients viewAllClient = new DBViewAllClient();
		ScrollPane box = new ScrollPane();
		//Commit Tuesday 13, 2023 at 12:44 AM
		box.setFitToWidth(true);
		box.setStyle(clientstyle.clientScroll);
		//adding gridpane
		GridPane gPane = new GridPane();
		gPane.setStyle(clientstyle.clientScroll);
		gPane.setPadding(new Insets(2,20,2,20));
		gPane.setAlignment(Pos.TOP_CENTER);
		gPane.setHgap(110);
		gPane.setVgap(5);
		
		Label gName = new Label();
		Label gAddress = new Label();
		Label gCity = new Label();
		Label gState = new Label();
		Label gZip = new Label();
		
		gPane.getChildren().addAll(gName,gAddress,gCity,gState,gZip);
		
//		gPane.addRow(gName,  0);
//		gPane.add(gAddress, 1); 
//		gPane.add(gCity,  2);
//		gPane.add(gState,  3);
//		gPane.add(gZip,  4);		
		
		//box.setContent(gPane);
		return box;
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
		Text notes = new Text("Please select a client, then click SEARCH. \n"
				+ "You will be able to view all information for the Client you select");
		//adding combobox for client info
		HBox select = new HBox();
		select.setSpacing(5);
		select.setAlignment(Pos.CENTER);
		ComboBox clientSelect = new ComboBox();
		clientSelect.setVisibleRowCount(5);
		Button ClSearch = new Button("Search");
		select.getChildren().addAll(clientSelect, ClSearch);
		
		//adding grid information
		GridPane grid =new GridPane(); 
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
		
		ClSearch.setOnAction(e->{
			   try {
				//DBViewSelectClient view = new DBViewSelectClient(); //to view a select Client
			   	String entry = clientSelect.getValue().toString();
		    	//int id = all.getName().indexOf(entry); //get the index of arraylist where = entry 
		    //	view.viewSelected(all.getID().get(id));

		    	txtSelectName.setText("Name");
		    	txtSelectType.setText("Type");
		    	txtSelectPhone.setText("Phone");
		    	txtSelectAddress.setText("Address");
		    	txtSelectCity.setText("City");
		    	txtSelectState.setText("State");
		    	txtSelectZip.setText("Zip");
		    	
			   } catch(Exception ex) {
				   clientSelect.requestFocus(); 
			   }
			   
		    });
		   
		   btonCancel.setOnAction(e->{
			   clientSelect.valueProperty().set(null); 
			   txtSelectName.setText(""); 
			   txtSelectType.setText("");
			   txtSelectPhone.setText("");
			   txtSelectAddress.setText("");
			   txtSelectCity.setText("");
			   txtSelectState.setText("");
			   txtSelectZip.setText("");
		   });
		   
		centerSection.getChildren().addAll(select,header,notes,select,grid,clientSelect);
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
		Label lbPhone = new Label("Client Type");
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
			
		return vbadd;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
