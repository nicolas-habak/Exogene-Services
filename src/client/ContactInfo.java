package client;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import dbUtil.DBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ContactInfo {
	final StringProperty uid;
	final StringProperty company;
	final StringProperty name;
	final StringProperty department;
	final StringProperty title;
	final StringProperty phoneNumber;
	final StringProperty email;
	final StringProperty notes;
	Button btnRemove;
	
	public ContactInfo(String uid, String company, String name, String department, String title, String phoneNumber, String email, String notes) {
		this.uid = new SimpleStringProperty(uid);
		this.company = new SimpleStringProperty(company);
		this.name = new SimpleStringProperty(name);		
		this.department = new SimpleStringProperty(department);
		this.title = new SimpleStringProperty(title);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
		this.email = new SimpleStringProperty(email);
		this.notes = new SimpleStringProperty(notes);
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
					String removeQuery = "DELETE FROM CandidateContact WHERE NAME = '" + name + "'";
					
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
	
	public StringProperty uidProperty() {
		return uid;
	}
	
	public void setUid(String uid) {
		this.uid.set(uid);
	}
	
	public StringProperty companyProperty() {
		return company;
	}
	
	public void setCompany(String company) {
		this.company.set(company);
	}
	
	public StringProperty nameProperty() {
		return name;
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public StringProperty departmentProperty() {
		return department;
	}

	public void setDepartment(String department) {
		this.department.set(department);
	}
	
	public StringProperty titleProperty() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title.set(title);
	}

	public StringProperty phoneNumberProperty() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}
	
	public StringProperty emailProperty() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email.set(email);
	}
	
	public StringProperty notesProperty() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes.set(notes);
	}
	
	public Button getBtnRemove() {
		return btnRemove;
	}
	
	public void setBtnRemove(Button btnRemove) {
		this.btnRemove = btnRemove;
	}
}
