package candidate;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import candidate.ContactInfo;
import dbUtil.DBConnection;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Note;

public class CandidateDetailsController implements Initializable {

	/* Candidate information fields */
	@FXML private Label lblName;
	@FXML private Label lblSalutation;	
	@FXML private Label lblAddressNoStreet;
	@FXML private Label lblAddressCityProvincePCode;
	@FXML private Label lblAddressCountry;
	@FXML private Label lblCurrentSalary;
	@FXML private Label lblDesiredSalary;
	@FXML private Label lblRotation;
	@FXML private Label lblEmployType;
	
	/* Candidate table columns */
	@FXML private TableColumn columnInfoType;
	@FXML private TableColumn columnInfo;

	@FXML private TableColumn columnNoteDate;
	@FXML private TableColumn columnNoteTitle;
	@FXML private TableColumn columnNote;

	@FXML private TableView contactTable;
	@FXML private TableView notesTable;
	@FXML private TableView interviewTable;

	private DBConnection DBConn;
	private String id;
	private String salutation;
	private String name;
	private String addressLn1;
	private String addressLn2;
	private String addressLn3;
	private String currentSalary;
	private String desiredSalary;
	private String rotation;
	private String employType;

	private ObservableList<ContactInfo> dataContacts;
	private ObservableList<Note> dataNotes;

	public void initialize(URL url, ResourceBundle rb) {
	}

	public void setData(String id, String salutation, String name, String addressLn1, String addressLn2, String addressLn3,
						String currentSalary, String desiredSalary, String rotation, String employType) {
		this.id = id;
		this.salutation = salutation;
		this.name = name;
		this.addressLn1 = addressLn1;
		this.addressLn2 = addressLn2;
		this.addressLn3 = addressLn3;
		this.currentSalary = currentSalary;
		this.desiredSalary = desiredSalary;
		this.rotation = rotation;
		this.employType = employType;

		setFields();
	}
	
	private void setFields() {
			lblName.setText(name);
			lblSalutation.setText(salutation);
			lblAddressNoStreet.setText(addressLn1);
			lblAddressCityProvincePCode.setText(addressLn2);
			lblAddressCountry.setText(addressLn3);
			lblCurrentSalary.setText(currentSalary);
			lblDesiredSalary.setText(desiredSalary);
			lblRotation.setText(rotation);
			lblEmployType.setText(employType);
            setContactTable(id);
			setNotesTable(id);
	}

	private void setContactTable(String candidateID) {
		String infoType;
		String info;
		String contactsQuery = "SELECT infoType, info FROM ContactInfo WHERE contactType = \"Candidate\" and contact_id = \"" + candidateID + "\"";
		Integer i;

		this.DBConn = new DBConnection();

		try {
			Connection conn = DBConnection.getConnection();
			this.dataContacts = FXCollections.observableArrayList();

			ResultSet rs = conn.createStatement().executeQuery(contactsQuery);

			while(rs.next()) {
				i = 1;
				infoType = rs.getString(i++);
				info = rs.getString(i++);

				this.dataContacts.add(new ContactInfo(id, infoType, info));
			}
			conn.close();
		}
		catch(SQLException e) {
			System.err.println("Error: " + e);
		}

		this.columnInfoType.setCellValueFactory(new PropertyValueFactory<ContactInfo, String>("infoType"));
		this.columnInfo.setCellValueFactory(new PropertyValueFactory<ContactInfo, String>("info"));

		this.contactTable.setItems(null);
		this.contactTable.setItems(this.dataContacts);
	}

	private void setNotesTable(String candidateID) {
		try {
			this.dataNotes = FXCollections.observableArrayList();
			dataNotes.addAll(Note.find(candidateID, "Candidate"));

			this.columnNoteDate.setCellValueFactory(new PropertyValueFactory<Note, String>("createdAt"));
			this.columnNoteTitle.setCellValueFactory(new PropertyValueFactory<Note, String>("title"));
			this.columnNote.setCellValueFactory(new PropertyValueFactory<Note, String>("content"));

			this.notesTable.setItems(null);
			this.notesTable.setItems(this.dataNotes);
		} catch (SQLException e) {
			System.err.println("Error: " + e);
		}
	}

    @FXML
    private void addContact(ActionEvent event) {
    }
	/*
	public void setClientInterviewTable(String clientName) {
		String contactName;
		String department;
		String title;
		String phoneNumber;
		String email;
		String notes;
		
		String contactsQuery = "SELECT NAME, DEPARTMENT, TITLE, PHONENUMBER, EMAIL, NOTES FROM CandidateContact WHERE COMPANY = '" + clientName + "'";
		
		this.DBConn = new DBConnection();

		try {
			Connection conn = DBConnection.getConnection();
			this.dataContacts = FXCollections.observableArrayList();
						
			ResultSet rs = conn.createStatement().executeQuery(contactsQuery);

			while(rs.next()) {
				contactName = rs.getString(1);
				department = rs.getString(2);
				title = rs.getString(3);
				phoneNumber = rs.getString(4);
				email = rs.getString(5);
				notes = rs.getString(6);
				
				this.dataContacts.add(new ContactInfo(null, clientName, contactName, department, title, phoneNumber, email, notes));
			}
			conn.close();
		}
		catch(SQLException e) {
			System.err.println("Error: " + e);
		}		
				
		this.columnDepartment.setCellValueFactory(new PropertyValueFactory<ContactInfo, String>("department"));
		this.columnTitle.setCellValueFactory(new PropertyValueFactory<ContactInfo, String>("title"));
		this.columnName.setCellValueFactory(new PropertyValueFactory<ContactInfo, String>("name"));
		this.columnPhone.setCellValueFactory(new PropertyValueFactory<ContactInfo, String>("phoneNumber"));
		this.columnEmail.setCellValueFactory(new PropertyValueFactory<ContactInfo, String>("email"));
		this.columnNotes.setCellValueFactory(new PropertyValueFactory<ContactInfo, String>("notes"));
		this.columnRemoveBtn.setCellValueFactory(new PropertyValueFactory<ContactInfo, Button>("btnRemove"));
		
		this.contactsTable.setItems(null);
		this.contactsTable.setItems(this.dataContacts);
	}
	*/
	@FXML
	private void candidateContact(ActionEvent event) {

	}
}
