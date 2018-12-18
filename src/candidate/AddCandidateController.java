package candidate;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import dbUtil.DBConnection;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddCandidateController implements Initializable{
	
	@FXML private TextField salutation;
	@FXML private TextField fname;
	@FXML private TextField mname;
	@FXML private TextField lname;
	@FXML private TextField addressStreet;
	@FXML private TextField addressCity;
	@FXML private TextField addressProvince;
	@FXML private TextField addressPostalCode;
	@FXML private TextField addressCountry;
	@FXML private TextField currentSalary;
	@FXML private TextField desiredSalary;
	@FXML private TextField rotation;
	@FXML private TextField employType;

	private DBConnection DBConn;
	private ObservableList<CandidateInfo> data;
	private FilteredList filter;
	
	private String candidateInsert = "INSERT INTO Candidates(salutation, fname, mname, lname, addressStreet, addressCity, "
			+ "addressProvince, addressPostalCode, addressCountry, currentSalary, desiredSalary, rotation, employType) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public void initialize(URL url, ResourceBundle rb) {
		
	}
	
	@FXML
	private void saveCandidate(ActionEvent event) throws SQLException {
		if (confirmSave()) {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(candidateInsert); 
			
			stmt.setString(1, this.salutation.getText());
			stmt.setString(2, this.fname.getText());
			stmt.setString(3, this.mname.getText());
			stmt.setString(4, this.lname.getText());
			stmt.setString(5, this.addressStreet.getText());
			stmt.setString(6, this.addressCity.getText());
			stmt.setString(7, this.addressProvince.getText());
			stmt.setString(8, this.addressPostalCode.getText());
			stmt.setString(9, this.addressCountry.getText());
			stmt.setString(10, this.currentSalary.getText());
			stmt.setString(11, this.desiredSalary.getText());
			stmt.setString(12, this.rotation.getText());
			stmt.setString(13, this.employType.getText());
			
			stmt.execute();
			conn.close();

			this.salutation.setText("");
			this.fname.setText("");
			this.mname.setText("");
			this.lname.setText("");
			this.addressStreet.setText("");
			this.addressCity.setText("");
			this.addressProvince.setText("");
			this.addressPostalCode.setText("");
			this.addressCountry.setText("");
			this.currentSalary.setText("");
			this.desiredSalary.setText("");
			this.rotation.setText("");
			this.employType.setText("");

			((Stage) this.salutation.getScene().getWindow()).close();

		}		
	}
	
	private boolean confirmSave() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure?");

		ButtonType btnConfirm = new ButtonType("Confirm");
		ButtonType btnCancel = new ButtonType("Cancel");

		alert.getButtonTypes().setAll(btnConfirm, btnCancel);
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == btnCancel) {
			
			return false;
		}
		    
		return true;
	}
}
