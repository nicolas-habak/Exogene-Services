package client;

import dbUtil.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddContactController implements Initializable{

	@FXML BorderPane addContactPane;

	@FXML private TextField textFname;
	@FXML private TextField textLname;
	@FXML private TextField textTitle;
	@FXML private TextField textDepartment;
	@FXML private TextField textLanguage;

	@FXML TextField textPhoneName;
	@FXML TextField textPhoneInput;
	@FXML GridPane gridPhoneNumber;
	int gridPhoneRow = 0;

	HashMap hashPhoneName = new HashMap();
	HashMap hashPhoneInput = new HashMap();

	@FXML TextField textEmailName;
	@FXML TextField textEmailInput;
	@FXML GridPane gridEmail;
	int gridEmailRow = 0;

	HashMap hashEmailName = new HashMap();
	HashMap hashEmail = new HashMap();

	private String contactInsert = "INSERT INTO ClientContact(fname,lname,title,department,language) VALUES (?,?,?,?,?)";
	private String contactInfoInsert = "INSERT INTO ContactInfo(contactID,contactType,infoType,info) VALUES (?,?,?,?)";

	private int clientID = 0;
	private String contactType = "Client";
	private String infoType = "";
	
	public void initialize(URL url, ResourceBundle rb) {
		
	}

	@FXML
	private void saveContact(ActionEvent e){
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(contactInsert);

			stmt.setString(1, textFname.getText());
			stmt.setString(2, textLname.getText());
			stmt.setString(3, textTitle.getText());
			stmt.setString(4, textDepartment.getText());
			stmt.setString(5, textLanguage.getText());

			stmt.execute();
			conn.close();

		}catch (SQLException eSQL){
			System.err.println(eSQL);
		}

		clientID = getClientID();

		if (!gridPhoneNumber.getChildren().isEmpty())
			savePhoneNumbers();

		if (!gridEmail.getChildren().isEmpty())
			saveEmails();

		closePage();
	}

	@FXML
	private void addPhoneNumber(ActionEvent e){
		Label lblPhoneName = new Label();
		Label lblPhoneInput = new Label();

		lblPhoneName.setText(textPhoneName.getText());
		lblPhoneInput.setText(textPhoneInput.getText());

		gridPhoneNumber.add(lblPhoneName, 0, gridPhoneRow);
		gridPhoneNumber.add(lblPhoneInput, 1, gridPhoneRow);

		textPhoneName.setText("");
		textPhoneInput.setText("");

		gridPhoneRow++;
	}

	@FXML
	private void addEmail(ActionEvent e){
		Label lblEmailName = new Label();
		Label lblEmailInput = new Label();

		lblEmailName.setText(textEmailName.getText());
		lblEmailInput.setText(textEmailInput.getText());

		gridEmail.add(lblEmailName, 0, gridEmailRow);
		gridEmail.add(lblEmailInput, 1, gridEmailRow);

		textEmailName.setText("");
		textEmailInput.setText("");

		gridEmailRow++;
	}

	private void savePhoneNumbers(){
		infoType = "PhoneNumber";

		try {

			Connection conn = DBConnection.getConnection();

			for (int i = 0; i < gridPhoneRow*2; i++) {
				PreparedStatement stmt = conn.prepareStatement(contactInfoInsert);

				stmt.setInt(1, clientID);
				stmt.setString(2, contactType);
				stmt.setString(3, infoType);

				try {
					stmt.setString(4, gridPhoneNumber.getChildren().get((i * (gridPhoneRow + 1)) + 2).toString());
				}catch(Exception e){

				}
				stmt.execute();
			}
			conn.close();
		}catch (SQLException eSQL){
			System.err.println(eSQL);
		}
	}

	private void saveEmails(){
		infoType = "Email";

		try {

			Connection conn = DBConnection.getConnection();

			for (int i = 0; i < gridEmailRow*2; i++) {
				PreparedStatement stmt = conn.prepareStatement(contactInfoInsert);

				stmt.setInt(1, clientID);
				stmt.setString(2, contactType);
				stmt.setString(3, infoType);
				stmt.setString(4, gridEmail.getChildren().get((i*(gridEmailRow+1))+2).toString());

				stmt.execute();
			}
			conn.close();

		}catch (SQLException eSQL){
			System.err.println(eSQL);
		}
	}

	private int getClientID(){
		int clientID = 0;

		try{
			Connection conn = DBConnection.getConnection();
			String clientIDQuery = "SELECT id from Clients ORDER BY id DESC";

			ResultSet rs = conn.createStatement().executeQuery(clientIDQuery);
			clientID = rs.getInt(1);

			conn.close();
		}catch(SQLException e){
			System.err.println();
		}
		return clientID;
	}

	private void closePage(){
		Stage stage = (Stage) addContactPane.getScene().getWindow();
		stage.close();
	}
}
