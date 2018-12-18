package client;

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

public class ClientInfo {
	final StringProperty uid;
	final StringProperty name;
	final StringProperty industryType;
	final StringProperty parentCompany;
	final StringProperty fee;
	Button btnDetails;
	
	public ClientInfo(String uid, String name, String industryType, String parentCompany, String fee) {
		this.uid = new SimpleStringProperty(uid);
		this.name = new SimpleStringProperty(name);
		this.industryType = new SimpleStringProperty(industryType);
		this.parentCompany = new SimpleStringProperty(parentCompany);
		this.fee = new SimpleStringProperty(fee);
		this.btnDetails = new Button("Details");
	}
	
	public StringProperty uidProperty() {
		return uid;
	}
	
	public void setUid(String uid) {
		this.uid.set(uid);
	}
	
	public StringProperty nameProperty() {
		return name;
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public StringProperty industryTypeProperty() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType.set(industryType);
	}
	
	public StringProperty parentCompanyProperty() {
		return parentCompany;
	}
	
	public void setParentCompany(String parentCompany) {
		this.parentCompany.set(parentCompany);
	}
	
	public StringProperty feeProperty() {
		return fee;
	}
	
	public void setFee(String fee) {
		this.fee.set(fee);
	}
	
	public Button getBtnDetails() {
		return btnDetails;
	}
	
	public void setBtnDetails(Button btnDetails) {
		this.btnDetails = btnDetails;
	}

	public void displayDetails() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ClientDetails.fxml"));

		try {
			loader.load();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(ClientInfo.class.getName()).log(Level.SEVERE, null, ex);
		}

		ClientDetailsController display = loader.getController();
//			display.setFields(name);
//			display.setClientContactTable(name);
//			display.setContractTable(name);

		Parent p = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(p));
		stage.show();
	}
}
