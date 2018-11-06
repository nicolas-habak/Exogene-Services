package candidate;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CandidateInfo {
	final StringProperty uid;
	final StringProperty salutation;
	final StringProperty fname;
	final StringProperty mname;
	final StringProperty lname;
	final StringProperty name;
	final StringProperty status;
	final StringProperty available;
	final StringProperty address;
	final StringProperty workEmail;
	final StringProperty personalEmail;
	final StringProperty notes;
	final StringProperty currentSalary;
	final StringProperty desiredSalary;
	final StringProperty placement;
	final StringProperty rotation;
	final StringProperty progress;
	Button btnDetails;
	
	public CandidateInfo(String uid, String salutation, String fname, String mname, String lname, String name, String status, String available, String address, 
			String workEmail, String personalEmail, String notes, String currentSalary, String desiredSalary, String placement, String rotation, String progress) {
		this.uid = new SimpleStringProperty(uid);
		this.salutation = new SimpleStringProperty(salutation);
		this.fname = new SimpleStringProperty(fname);
		this.mname = new SimpleStringProperty(mname);
		this.lname = new SimpleStringProperty(lname);
		this.name = new SimpleStringProperty(name);
		this.status = new SimpleStringProperty(status);
		this.available = new SimpleStringProperty(available);
		this.address = new SimpleStringProperty(address);
		this.workEmail = new SimpleStringProperty(workEmail);
		this.personalEmail = new SimpleStringProperty(personalEmail);
		this.notes = new SimpleStringProperty(notes);
		this.currentSalary = new SimpleStringProperty(currentSalary);
		this.desiredSalary = new SimpleStringProperty(desiredSalary);
		this.placement = new SimpleStringProperty(placement);
		this.rotation = new SimpleStringProperty(rotation);
		this.progress = new SimpleStringProperty(progress);
		this.btnDetails = new Button("Details");
		
		btnDetails.setOnAction(e -> {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("CandidateDetails.fxml"));
			
			try {
				loader.load();
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				Logger.getLogger(CandidateInfo.class.getName()).log(Level.SEVERE, null, ex);
			}
			
			CandidateDetailsController display = loader.getController();
			display.setFields(fname, mname, lname);
//			display.setClientInterviewTable(name);
			
			Parent p = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(p));
			stage.show();
		});
	}
	
	public StringProperty uidProperty() {
		return uid;
	}
	
	public void setUid(String uid) {
		this.uid.set(uid);
	}
	
	public StringProperty salutationProperty() {
		return salutation;
	}
	
	public void setSalutation(String salutation) {
		this.salutation.set(salutation);
	}
	
	public StringProperty fnameProperty() {
		return fname;
	}
	
	public void setFname(String fname) {
		this.fname.set(fname);
	}
	
	public StringProperty mnameProperty() {
		return mname;
	}
	
	public void setMname(String mname) {
		this.mname.set(mname);
	}

	public StringProperty lnameProperty() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname.set(lname);
	}
	
	public StringProperty nameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}
	
	public StringProperty statusProperty() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status.set(status);
	}

	public StringProperty availableProperty() {
		return available;
	}
	
	public void setAvailable(String available) {
		this.available.set(available);
	}
	
	public StringProperty addressProperty() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address.set(address);
	}

	public StringProperty workEmailProperty() {
		return workEmail;
	}
	
	public void setWorkEmail(String workEmail) {
		this.workEmail.set(workEmail);
	}
	
	public StringProperty personalEmailProperty() {
		return personalEmail;
	}
	
	public void setEmailPersonal(String personalEmail) {
		this.personalEmail.set(personalEmail);
	}
	
	public StringProperty notesProperty() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes.set(notes);
	}
	
	public StringProperty currentSalaryProperty() {
		return currentSalary;
	}
	
	public void setCurrentSalary(String currentSalary) {
		this.currentSalary.set(currentSalary);
	}
	
	public StringProperty desiredSalaryProperty() {
		return desiredSalary;
	}
	
	public void setDesiredSalary(String desiredSalary) {
		this.desiredSalary.set(desiredSalary);
	}
	
	public StringProperty placementProperty() {
		return placement;
	}
	
	public void setPlacement(String placement) {
		this.placement.set(placement);
	}
	
	public StringProperty rotationProperty() {
		return rotation;
	}
	
	public void setRotation(String rotation) {
		this.rotation.set(rotation);
	}
	
	public StringProperty progressProperty() {
		return progress;
	}
	
	public void setProgress(String progress) {
		this.progress.set(progress);
	}
	
	public Button getBtnDetails() {
		return btnDetails;
	}
	
	public void setBtnDetails(Button btnDetails) {
		this.btnDetails = btnDetails;
	}
}
