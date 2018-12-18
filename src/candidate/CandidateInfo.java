package candidate;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CandidateInfo {
	final private StringProperty id;
	final private StringProperty salutation;
	final private StringProperty fname;
	final private StringProperty mname;
	final private StringProperty lname;
	final private StringProperty addressStreet;
	final private StringProperty addressCity;
	final private StringProperty addressProvince;
	final private StringProperty addressPostalCode;
	final private StringProperty addressCountry;
	final private StringProperty currentSalary;
	final private StringProperty desiredSalary;
	final private StringProperty rotation;
	final private StringProperty employType;
	
	public CandidateInfo(String id, String salutation, String fname, String mname, String lname, String addressStreet, String addressCity,
						 String addressProvince, String addressPostalCode, String addressCountry, String currentSalary, String desiredSalary, String rotation,
						 String employType) {
		this.id = new SimpleStringProperty(id);
		this.salutation = new SimpleStringProperty(salutation);
		this.fname = new SimpleStringProperty(fname);
		this.mname = new SimpleStringProperty(mname);
		this.lname = new SimpleStringProperty(lname);
		this.addressStreet = new SimpleStringProperty(addressStreet);
		this.addressPostalCode = new SimpleStringProperty(addressPostalCode);
		this.addressProvince = new SimpleStringProperty(addressProvince);
		this.addressCity = new SimpleStringProperty(addressCity);
		this.addressCountry = new SimpleStringProperty(addressCountry);
		this.currentSalary = new SimpleStringProperty(currentSalary);
		this.desiredSalary = new SimpleStringProperty(desiredSalary);
		this.rotation = new SimpleStringProperty(rotation);
		this.employType = new SimpleStringProperty(employType);
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
		List<String> name = new ArrayList<String>();
		if (fname.isNotEmpty().get())
			name.add(fname.get());
		if (mname.isNotEmpty().get())
			name.add(mname.get());
		if (lname.isNotEmpty().get())
			name.add(lname.get());

		return new SimpleStringProperty(String.join(" ", name));
	}

	public StringProperty addressStreetProperty() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet.set(addressStreet);
	}

	public StringProperty addressCityProperty() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity.set(addressCity);
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

	public StringProperty addressLn1Property() {
		List<String> address = new ArrayList<String>();

		if(addressStreet.isNotEmpty().get())
			address.add(addressStreet.get());
		else
			address.add("-");

		return new SimpleStringProperty(String.join(" ", address));
	}

	public StringProperty addressLn2Property() {
		List<String> address = new ArrayList<String>();

		if(addressCity.isNotEmpty().get())
			address.add(addressCity.get());
		if(addressProvince.isNotEmpty().get())
		    address.add(addressProvince.get());
		if(addressPostalCode.isNotEmpty().get())
			address.add(addressPostalCode.get());

		if(address.size() == 0) {
			address.add("-");
		}

		return new SimpleStringProperty(String.join(", ", address));
	}

	public StringProperty addressLn3Property() {
		List<String> address = new ArrayList<String>();

		if(addressCountry.isNotEmpty().get())
			address.add(addressCountry.get());
		else
			address.add("-");

		return new SimpleStringProperty(String.join(" ", address));
	}

	public StringProperty addressProperty() {
		List<String> address = new ArrayList<String>();
		if(addressStreet.isNotEmpty().get())
			address.add(addressStreet.get());
		if(addressCity.isNotEmpty().get())
			address.add(addressCity.get());
		if(addressProvince.isNotEmpty().get())
			address.add(addressProvince.get());
		if(addressPostalCode.isNotEmpty().get())
			address.add(addressPostalCode.get());
		if(addressCountry.isNotEmpty().get())
			address.add(addressCountry.get());

		return new SimpleStringProperty(String.join(" ", address));
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

	public void displayDetails() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CandidateDetails.fxml"));

		try {
			loader.load();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(CandidateInfo.class.getName()).log(Level.SEVERE, null, ex);
		}

		CandidateDetailsController display = loader.getController();

		display.setData(id.get(), nameProperty().get(), salutation.get(),
				addressLn1Property().get(), addressLn2Property().get(), addressLn3Property().get(),
				currentSalary.get(), desiredSalary.get(), rotation.get(), employType.get());
//			display.setClientInterviewTable(name);

		Parent p = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(p));
		stage.show();
	}
}
