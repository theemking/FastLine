package FastLineCorp.FastLine;

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

public class App extends Application {

	BorderPane bPane = new BorderPane();
	GridPane gPane = new GridPane();

	// Button
	static Button btonhomepage;
	static Button btonclients;
	static Button btonflights;
	static Button btonpilot;
	static Button btonshipment;
	static Button btonhelp;
	static Button btoncontact;
	static Button btonexit;
	static Button btonView;
	static Button btonAdd;
	static Button btonEdit;
	static Button btonDelete;
	static Button btonEnter;
	static Button btonCancel;
	static Button btonExit;

	// Navigation bar Labels
	static Label lbclient;
	static Label lbflights;
	static Label lbpilot;
	static Label lbshipment;
	static Label lbhelp;
	static Label contact;

	static TextArea texReaOne = new TextArea();

	@Override
	public void start(Stage primaryStage) {
		try {
			// setup gridpane properties
			gPane.setAlignment(Pos.CENTER);
			gPane.setStyle("-fx-background-color: white; -fx-border-width: 1px; -fx-border-color: darkblue;");
			gPane.setVgap(5);
			gPane.setStyle("-fx-background-color: #984894k5");

			// Heather and Navigation Bar
			bPane.setTop(getHeader());
			bPane.setCenter(gPane);
			bPane.setBottom(getVBoxBottom());

			gPane.add(getViewLabel(), 0, 0);
			GridPane.setConstraints(getViewLabel(), 0, 1, 1, 1);

			gPane.add(getTextAreaOne(), 0, 1);

			gPane.add(getMidPageButton(), 0, 7);
			GridPane.setConstraints(getMidPageButton(), 0, 3, 1, 1);

			Scene scen = new Scene(bPane, 1000, 900);
			primaryStage.setTitle("Champlain Air-Freight");
			primaryStage.setScene(scen);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	private VBox getHeader() {
		VBox vboxt = new VBox();
		vboxt.setAlignment(Pos.CENTER);
		vboxt.getChildren().addAll(getVBoxTop(), getNavButtons());
		return vboxt;
	}

	private VBox getVBoxTop() {
		VBox vboxt = new VBox();
		vboxt.setAlignment(Pos.CENTER);
		vboxt.setMinHeight(100);
		vboxt.setStyle("-fx-background-color: lightblue");
		Text headertex = new Text("CHAMPLAIN AIR FREIGHT");
		Text bottom = new Text("Clients");
		headertex.setFill(Color.DARKBLUE);
		headertex.setStrokeWidth(2);
		headertex.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
		bottom.setFill(Color.BLUE);
		bottom.setStrokeWidth(2);
		bottom.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));
		vboxt.setSpacing(10);
		vboxt.getChildren().addAll(headertex, bottom);
		return vboxt;
	}
/**
 *  * The method displays the homepage, clients, flights, pilots,shipment, help, contact and exit button
 *  for the heather  section
 * @return
 */
	private HBox getNavButtons() {
		HBox hboxnv = new HBox();
		hboxnv.setStyle("-fx-background-color: lightblue");
		hboxnv.setSpacing(20);
		hboxnv.setPadding(new Insets(10, 10, 10, 10));
		hboxnv.setAlignment(Pos.CENTER);
		btonhomepage = new Button("HOMEPAGE");
		btonclients = new Button("CLIENTS");
		btonflights = new Button("FLIGHTS");
		btonpilot = new Button("PILOT");
		btonshipment = new Button("SHIPMENT");
		btonhelp = new Button("HELP - FAQ PAGE");
		btoncontact = new Button("CONTACT US");
		btonexit = new Button("EXIT");
		// Color + "-fx-text-fill: black" +
		btonhomepage.setStyle("-fx-background-color: lightblue; " + "-fx-text-fill: blue; "
				+ "-fx-border-color: darkblue; " + "-fx-border-radius: 3;" + "-fx-border-style: solid inside ");

		btonclients.setStyle("-fx-background-color: lightblue; " + "-fx-text-fill: blue; "
				+ "-fx-border-color: darkblue; " + "-fx-border-radius: 3;" + "-fx-border-style: solid inside ");
		btonflights.setStyle("-fx-background-color: lightblue; " + "-fx-text-fill: blue; "
				+ "-fx-border-color: darkblue; " + "-fx-border-radius: 3;" + "-fx-border-style: solid inside ");
		btonpilot.setStyle("-fx-background-color: lightblue; " + "-fx-text-fill: blue; "
				+ "-fx-border-color: darkblue; " + "-fx-border-radius: 3;" + "-fx-border-style: solid inside ");
		btonshipment.setStyle("-fx-background-color: lightblue; " + "-fx-text-fill: blue; "
				+ "-fx-border-color: darkblue; " + "-fx-border-radius: 3;" + "-fx-border-style: solid inside ");
		btonhelp.setStyle("-fx-background-color: lightblue; " + "-fx-text-fill: blue; " + "-fx-border-color: darkblue; "
				+ "-fx-border-radius: 3;" + "-fx-border-style: solid inside ");
		btoncontact.setStyle("-fx-background-color: lightblue; " + "-fx-text-fill: blue; "
				+ "-fx-border-color: darkblue; " + "-fx-border-radius: 3;" + "-fx-border-style: solid inside ");
		btonexit.setStyle("-fx-background-color: lightblue; " + "-fx-text-fill: blue; " + "-fx-border-color: darkblue; "
				+ "-fx-border-radius: 3;" + "-fx-border-style: solid inside ");
		btonexit.setStyle("-fx-background-color: lightblue; " + "-fx-text-fill: blue; " + "-fx-border-color: darkblue; "
				+ "-fx-border-radius: 3;" + "-fx-border-style: solid inside ");
		// Height
		btonhomepage.setMinHeight(30);
		btonclients.setMinHeight(30);
		btonflights.setMinHeight(30);
		btonpilot.setMinHeight(30);
		btonshipment.setMinHeight(30);
		btonhelp.setMinHeight(30);
		btoncontact.setMinHeight(30);
		btonexit.setMinHeight(30);
		// Font
		btonhomepage.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		btonclients.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		btonflights.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		btonpilot.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		btonshipment.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		btonhelp.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		btoncontact.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		btonexit.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		hboxnv.getChildren().addAll(btonhomepage, btonclients, btonflights, btonpilot, btonshipment, btonhelp,
				btoncontact, btonexit);
		return hboxnv;
	}
/**
 * The method displays the view, add, edit, delete, enter, cancel and exit buttons in the middle section
 * @return
 */
	private HBox getMidPageButton() {
		HBox hboxm = new HBox();
		hboxm.setAlignment(Pos.CENTER);
		hboxm.setSpacing(20);

		// Set the Style-properties of the VBox
		btonView = new Button("View");
		btonAdd = new Button("Add");
		btonEdit = new Button("Edit");
		btonDelete = new Button("Delete");
		btonEnter = new Button("Enter");
		btonCancel = new Button("Cancel");
		btonExit = new Button("Exit");
		// Height
		btonView.setMinHeight(30);
		btonAdd.setMinHeight(30);
		btonEdit.setMinHeight(30);
		btonDelete.setMinHeight(30);
		btonEnter.setMinHeight(30);
		btonCancel.setMinHeight(30);
		btonExit.setMinHeight(30);
		// Font
		btonView.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		btonAdd.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		btonEdit.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		btonDelete.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		btonEnter.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		btonCancel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		btonExit.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));

		btonView.setStyle("-fx-background-color: white; " + "-fx-border-color: lightblue; " + "-fx-text-fill: black"
				+ "-fx-border-style: solid inside; " + "-fx-border-radius: 5; " + "fx-border-radius: 5");

		btonAdd.setStyle("-fx-background-color: white; " + "-fx-border-color: lightblue; " + "-fx-text-fill: black"
				+ "-fx-border-style: solid inside; " + "-fx-border-radius: 5; " + "fx-border-radius: 5");
		btonEdit.setStyle("-fx-background-color: white; " + "-fx-border-color: lightblue; " + "-fx-text-fill: black"
				+ "-fx-border-style: solid inside; " + "-fx-border-radius: 5; " + "fx-border-radius: 5");
		btonDelete.setStyle("-fx-background-color: white; " + "-fx-border-color: lightblue; " + "-fx-text-fill: black"
				+ "-fx-border-style: solid inside; " + "-fx-border-radius: 5; " + "fx-border-radius: 5");
		btonEnter.setStyle("-fx-background-color: white; " + "-fx-border-color: lightblue; " + "-fx-text-fill: black"
				+ "-fx-border-style: solid inside; " + "-fx-border-radius: 5; " + "fx-border-radius: 5");
		btonCancel.setStyle("-fx-background-color: white; " + "-fx-border-color: lightblue; " + "-fx-text-fill: black"
				+ "-fx-border-style: solid inside; " + "-fx-border-radius: 5; " + "fx-border-radius: 5");
		btonExit.setStyle("-fx-background-color: white; " + "-fx-border-color: lightblue; " + "-fx-text-fill: black"
				+ "-fx-border-style: solid inside; " + "-fx-border-radius: 5; " + "fx-border-radius: 5");
		hboxm.getChildren().addAll(btonView, btonAdd, btonEdit, btonDelete, btonEnter, btonCancel, btonExit);
		return hboxm;
	}
/**
 * The Method below displays the name, address, city, state, zip as label for the heather of the result area
 * @return
 */
	private HBox getViewLabel() {
		HBox hboxv = new HBox();
		hboxv.setPadding(new Insets(3, 20, 3, 20));
		hboxv.setSpacing(150);
		// hboxv.setAlignment(Pos.CENTER);
		hboxv.setPrefSize(895, 25);
		hboxv.setStyle("-fx-background-color: white; -fx-border-color: black");
		Label name = new Label("Client Name");
		Label Address = new Label("Address");
		Label City = new Label("City");
		Label State = new Label("State");
		Label Zip = new Label("Zip");
		name.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
		Address.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
		City.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
		State.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
		Zip.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
		hboxv.getChildren().addAll(name, Address, City, State, Zip);
		return hboxv;
	}
	/**
	 * The methode below displays the TextArea section
	 * @return
	 */
	private HBox getTextAreaOne() {
		HBox hboxt = new HBox();
		texReaOne = new TextArea();
		texReaOne.setStyle("-fx-border-color: black");
		texReaOne.setFont(new Font("Time New Roman", 10));
		texReaOne.setEditable(false);
		texReaOne.setWrapText(true);
		texReaOne.setPrefSize(895, 500);
		hboxt.getChildren().addAll(texReaOne);
		return hboxt;
	}
 /**
  * The method below is the footer for the page
  * @return
  */
	private VBox getVBoxBottom() {
		VBox vboxb = new VBox();
		vboxb.setMinSize(1000, 150);
		vboxb.setStyle("-fx-background-color: #483D8B");
		Text toptext = new Text("Copyright   2020   All Rights Reserved:  " + "Champlain Air-Freight");
		toptext.setFill(Color.WHITE);
		Text bottext = new Text("Designed by: Group 1");
		toptext.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.REGULAR, 15));
		bottext.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.REGULAR, 15));
		bottext.setFill(Color.WHITE);
		vboxb.setAlignment(Pos.CENTER);
		VBox.setMargin(toptext, new Insets(0, 0, 20, 0));
		vboxb.getChildren().addAll(toptext, bottext);
		return vboxb;
	}
}
