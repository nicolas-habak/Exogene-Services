package client;

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

public class AddClientController implements Initializable{
	
	@FXML private TextField name;
	@FXML private TextField industryType;
	@FXML private TextField parentCompany;
	@FXML private TextField fee;

	private DBConnection DBConn;
	private ObservableList<ClientInfo> data;
	private FilteredList filter;
	
	private String clientInsert = "INSERT INTO Clients(name,industry,parentCompany,fee) VALUES (?,?,?,?)";
	
	public void initialize(URL url, ResourceBundle rb) {
		
	}
	
	@FXML
	private void saveClient(ActionEvent event) throws SQLException {
		if (confirmSave()) {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(clientInsert); 
			
			stmt.setString(1, this.name.getText());
			stmt.setString(2, this.industryType.getText());
			stmt.setString(3, this.parentCompany.getText());
			stmt.setString(4, this.fee.getText());
			
			stmt.execute();
			conn.close();
			
			this.name.setText("");
			this.industryType.setText("");
			this.parentCompany.setText("");
			this.fee.setText("");

			((Stage) this.name.getScene().getWindow()).close();
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
