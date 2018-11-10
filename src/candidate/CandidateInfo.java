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
	final StringProperty id;
	final StringProperty salutation;
	final StringProperty fname;
	final StringProperty mname;
	final StringProperty lname;
	final StringProperty addressNo;
	final StringProperty addressStreet;
	final StringProperty addressPostalCode;
	final StringProperty addressProvince;
	final StringProperty addressCountry;
	final StringProperty currentSalary;
	final StringProperty desiredSalary;
	final StringProperty rotation;
	final StringProperty employType;
	Button btnDetails;
	
	public CandidateInfo(String id, String salutation, String fname, String mname, String lname, String addressNo, String addressStreet, String addressPostalCode,
			String addressProvince, String addressCountry, String currentSalary, String desiredSalary, String rotation, String employType) {
		this.id = new SimpleStringProperty(id);
		this.salutation = new SimpleStringProperty(salutation);
		this.fname = new SimpleStringProperty(fname);
		this.mname = new SimpleStringProperty(mname);
		this.lname = new SimpleStringProperty(lname);
		this.addressNo = new SimpleStringProperty(addressNo);
		this.addressStreet = new SimpleStringProperty(addressStreet);
		this.addressPostalCode = new SimpleStringProperty(addressPostalCode);
		this.addressProvince = new SimpleStringProperty(addressProvince);
		this.addressCountry = new SimpleStringProperty(addressCountry);
		this.currentSalary = new SimpleStringProperty(currentSalary);
		this.desiredSalary = new SimpleStringProperty(desiredSalary);
		this.rotation = new SimpleStringProperty(rotation);
		this.employType = new SimpleStringProperty(employType);
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

	public StringProperty idProperty() {
		return id;
	}

	public void setId(String id) {
		this.id.set(id);
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

	public StringProperty nameProperty() {
		return new SimpleStringProperty(fname + " " + mname + " " + lname);
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

	public StringProperty addressNoProperty() {
		return addressNo;
	}

	public void setAddressNo(String addressNo) {
		this.addressNo.set(addressNo);
	}

	public StringProperty addressStreetProperty() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet.set(addressStreet);
	}

	public StringProperty addressPostalCodeProperty() {
		return addressPostalCode;
	}

	public void setAddressPostalCode(String addressPostalCode) {
		this.addressPostalCode.set(addressPostalCode);
	}

	public StringProperty addressProvinceProperty() {
		return addressProvince;
	}

	public void setAddressProvince(String addressProvince) {
		this.addressProvince.set(addressProvince);
	}

	public StringProperty addressCountryProperty() {
		return addressCountry;
	}

	public void setAddressCountry(String addressCountry) {
		this.addressCountry.set(addressCountry);
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

	public StringProperty rotationProperty() {
		return rotation;
	}

	public void setRotation(String rotation) {
		this.rotation.set(rotation);
	}

	public StringProperty employTypeProperty() {
		return employType;
	}

	public void setEmployType(String employType) {
		this.employType.set(employType);
	}

	public void setBtnDetails(Button btnDetails) {
		this.btnDetails = btnDetails;
	}
}
