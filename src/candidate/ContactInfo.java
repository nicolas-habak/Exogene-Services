package candidate;

import dbUtil.DBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactInfo {
	final StringProperty id;
	final StringProperty infoType;
	final StringProperty info;
	Button btnRemove;
	
	public ContactInfo(String id, String infoType, String info) {
		this.id = new SimpleStringProperty(id);
		this.infoType = new SimpleStringProperty(infoType);
		this.info = new SimpleStringProperty(info);

		this.btnRemove = new Button("Remove");

		btnRemove.setOnAction(e -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Remove confirmation");
			alert.setHeaderText(null);
			alert.setContentText("Would you like to remove this contact?");

			Window window = alert.getDialogPane().getScene().getWindow();
			window.setOnCloseRequest(event -> window.hide());
			
			ButtonType btnConfirm = new ButtonType("Confirm");
			ButtonType btnCancel = new ButtonType("Cancel");

			alert.getButtonTypes().setAll(btnConfirm, btnCancel);
			Optional<ButtonType> result = alert.showAndWait();
			
			boolean remove = false;
			
			if (result.isPresent()){
				if (result.get() == btnConfirm)
					remove = true;
			}
			
			if (remove) {
				try {
					String removeQuery = "DELETE FROM CandidateContact WHERE NAME = '" + id + "'";
					
					Connection conn = DBConnection.getConnection();								
					PreparedStatement stmt = conn.prepareStatement(removeQuery);
					
					stmt.execute();
					conn.close();
				}
				catch(SQLException ex) {
					System.err.println("Error: " + ex);
				}
			}
			
		});		
	}

	public StringProperty idProperty() { return id; }
	public StringProperty infoTypeProperty() { return infoType; }
	public StringProperty infoProperty() { return info; }

	public void setId(String id) { this.id.set(id); }
	public void setInfoType(String infoType) { this.infoType.set(infoType); }
	public void setInfo(String info) { this.info.set(info); }

	public Button getBtnRemove() {
		return btnRemove;
	}
	
	public void setBtnRemove(Button btnRemove) {
		this.btnRemove = btnRemove;
	}
}
