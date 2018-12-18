package client;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
	final StringProperty id;
	final StringProperty salutation;
	final StringProperty fname;
	final StringProperty mname;
	final StringProperty lname;
	final StringProperty department;
	final StringProperty infoType;
	final StringProperty info;
	final StringProperty language;
	Button btnRemove;
	
	public ContactInfo(String id, String salutation, String fname, String mname, String lname, String department, String infoType, String info, String language) {
		this.id = new SimpleStringProperty(id);
		this.salutation = new SimpleStringProperty(salutation);
		this.fname = new SimpleStringProperty(fname);
		this.mname = new SimpleStringProperty(mname);
		this.lname = new SimpleStringProperty(lname);
		this.department = new SimpleStringProperty(department);
		this.infoType = new SimpleStringProperty(infoType);
		this.info = new SimpleStringProperty(info);
		this.language = new SimpleStringProperty(language);
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
					String removeQuery = "DELETE FROM CandidateContact WHERE id = '" + id + "'";
					
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
	public StringProperty salutationProperty() { return salutation; }
	public StringProperty nameProperty() {
        List<String> name = new ArrayList<String>();
        if(fname.isNotEmpty().get()){
            name.add(fname.get());
        }
        if(mname.isNotEmpty().get()){
            name.add(mname.get());
        }
        if(lname.isNotEmpty().get()){
            name.add(lname.get());
        }
	    return new SimpleStringProperty(String.join(" ", name));
	}
	public StringProperty fnameProperty() { return fname; }
	public StringProperty mnameProperty() { return mname; }
	public StringProperty lnameProperty() { return lname; }
	public StringProperty departmentProperty() { return department; }
	public StringProperty infoTypeProperty() { return infoType; }
	public StringProperty infoProperty() { return info; }
	public StringProperty languageProperty() { return language; }

	public void setId(String id) { this.id.set(id); }
	public void setSalutation(String salutation) { this.salutation.set(salutation); }
	public void setFname(String fname) { this.fname.set(fname); }
	public void setMname(String mname) { this.mname.set(mname); }
	public void setLname(String lname) { this.lname.set(lname); }
	public void setDepartment(String department) { this.department.set(department); }
	public void setInfoType(String infoType) { this.infoType.set(infoType); }
	public void setInfo(String info) { this.info.set(info); }
	public void setLanguage(String language) { this.language.set(language); }

	public Button getBtnRemove() {
		return btnRemove;
	}
	
	public void setBtnRemove(Button btnRemove) {
		this.btnRemove = btnRemove;
	}
}
