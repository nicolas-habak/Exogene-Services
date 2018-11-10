package joborder;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import client.ClientDetailsController;
import client.ClientInfo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JobOrderInfo {
	final StringProperty id;
	final StringProperty company;
	final StringProperty employType;
	final StringProperty status;
	final StringProperty statusNote;
	final StringProperty startDate;
	Button btnDetails;
	
	public JobOrderInfo(String id, String company, String employType, String status, String statusNote, String startDate) {
		this.id = new SimpleStringProperty(id);
		this.company = new SimpleStringProperty(company);
		this.employType = new SimpleStringProperty(employType);
		this.status = new SimpleStringProperty(status);
		this.statusNote = new SimpleStringProperty(statusNote);
		this.startDate = new SimpleStringProperty(startDate);
		this.btnDetails = new Button("Details");

		btnDetails.setOnAction(e -> {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("JobOrderDetails.fxml"));

			try {
				loader.load();
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				Logger.getLogger(ClientInfo.class.getName()).log(Level.SEVERE, null, ex);
			}

			JobOrderDetailsController display = loader.getController();
			display.setFields(id);
			display.setClientContactTable(id);
			display.setContractTable(id);

			Parent p = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(p));
			stage.show();
		});
	}
	
	public StringProperty idProperty() {
		return id;
	}
	
	public void setId(String id) {
		this.id.set(id);
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

	public Button getBtnDetails() {
		return btnDetails;
	}

	public void setBtnDetails(Button btnDetails) {
		this.btnDetails = btnDetails;
	}
}
