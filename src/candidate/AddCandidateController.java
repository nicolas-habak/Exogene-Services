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

public class AddCandidateController implements Initializable{
	
	@FXML private TextField fname;
	@FXML private TextField mname;
	@FXML private TextField lname;
	@FXML private TextField salutation;
	@FXML private TextField status;
	@FXML private TextField available;
	@FXML private TextField address;
	@FXML private TextField emailPersonal;
	@FXML private TextField emailWork;
	@FXML private TextField notes;
	@FXML private TextField currentSalary;
	@FXML private TextField desiredSalary;
	@FXML private TextField placement;
	@FXML private TextField rotation;
	@FXML private TextField progress;

	private DBConnection DBConn;
	private ObservableList<CandidateInfo> data;
	private FilteredList filter;
	
	private String candidateInsert = "INSERT INTO Candidates(fname,mname,lname,salutation,status,addressNo,addressStreet,"
			+ "addressPostalCode,addressProvince,addressCountry,currentSalary,desiredSalary,rotation,employType) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public void initialize(URL url, ResourceBundle rb) {
		
	}
	
	@FXML
	private void saveCandidate(ActionEvent event) throws SQLException {
		if (confirmSave()) {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(candidateInsert); 
			
			stmt.setString(1, this.fname.getText());
			stmt.setString(2, this.mname.getText());
			stmt.setString(3, this.lname.getText());
			stmt.setString(4, this.salutation.getText());
			stmt.setString(5, this.status.getText());
			stmt.setString(6, this.available.getText());
			stmt.setString(7, this.address.getText());
			stmt.setString(8, this.emailWork.getText());
			stmt.setString(9, this.emailPersonal.getText());
			stmt.setString(10, this.notes.getText());
			stmt.setString(11, this.currentSalary.getText());
			stmt.setString(12, this.desiredSalary.getText());
			stmt.setString(13, this.placement.getText());
			stmt.setString(14, this.rotation.getText());
			stmt.setString(15, this.progress.getText());
			
			stmt.execute();
			conn.close();
			
			this.fname.setText("");
			this.mname.setText("");
			this.lname.setText("");
			this.salutation.setText("");
			this.status.setText("");
			this.available.setText("");
			this.address.setText("");
			this.emailWork.setText("");
			this.emailPersonal.setText("");
			this.notes.setText("");
			this.currentSalary.setText("");
			this.desiredSalary.setText("");
			this.placement.setText("");
			this.rotation.setText("");
			this.progress.setText("");	
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
