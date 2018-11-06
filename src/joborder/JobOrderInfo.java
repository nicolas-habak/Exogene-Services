package joborder;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JobOrderInfo {
	final StringProperty uid;
	final StringProperty company;
	final StringProperty employType;
	final StringProperty status;
	final StringProperty statusNote;
	final StringProperty startDate;
	
	public JobOrderInfo(String uid, String company, String employType, String status, String statusNote, String startDate) {
		this.uid = new SimpleStringProperty(uid);
		this.company = new SimpleStringProperty(company);
		this.employType = new SimpleStringProperty(employType);
		this.status = new SimpleStringProperty(status);
		this.statusNote = new SimpleStringProperty(statusNote);
		this.startDate = new SimpleStringProperty(startDate);
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
	
	public StringProperty employTypeProperty() {
		return employType;
	}

	public void setEmployType(String employType) {
		this.employType.set(employType);
	}
	
	public StringProperty statusProperty() {
		return status;
	}
	
	public void setParentCompany(String status) {
		this.status.set(status);
	}

	public StringProperty statusNoteProperty() {
		return statusNote;
	}
	
	public void setStatusNote(String statusNote) {
		this.statusNote.set(statusNote);
	}
	
	public StringProperty startDateProperty() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate.set(startDate);
	}
}
