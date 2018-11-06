package candidate;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

public class CandidateDetailsController implements Initializable {

	/* Candidate information fields */
	@FXML private Label lblName;
	@FXML private Label lblSalutation;	
	@FXML private Label lblStatus;
	@FXML private Label lblAvailable;
	@FXML private Label lblAddress; 
	@FXML private Label lblWorkEmail;
	@FXML private Label lblPersonalEmail;
	@FXML private Label lblNotes;
	@FXML private Label lblCurrentSalary;
	@FXML private Label lblDesiredSalary;
	@FXML private Label lblPlacement;
	@FXML private Label lblRotation;
	@FXML private Label lblProgress;
	
	/* Candidate table columns */
	@FXML private TableColumn columnDepartment;
	@FXML private TableColumn columnTitle;
	@FXML private TableColumn columnName;
	@FXML private TableColumn columnPhone;
	@FXML private TableColumn columnEmail;
	@FXML private TableColumn columnNotes;
	@FXML private TableColumn columnRemoveBtn;
	
	@FXML private TableView interviewTable;
	
	private DBConnection DBConn;
	
	public void initialize(URL url, ResourceBundle rb) {
		
	}
	
	public void setFields(String fnameCandidate, String mnameCandidate, String lnameCandidate) {
		String salutation;
		String fname;
		String mname;
		String lname;
		String name;
		String status;
		String available;
		String address;
		String workEmail;
		String personalEmail;
		String notes;
		String currentSalary;
		String desiredSalary;
		String placement;
		String rotation;
		String progress;
		
		String candidateDetailsQuery =  "SELECT * FROM Candidates where fname = '" + fnameCandidate + "' AND mname = '" + mnameCandidate + "' AND lname = '" + lnameCandidate + "'";		
		
		this.DBConn = new DBConnection();

		try {
			Connection conn = DBConnection.getConnection();
			
			ResultSet rs = conn.createStatement().executeQuery(candidateDetailsQuery);
			CandidateInfo candidate;

			salutation = rs.getString(2);
			fname = rs.getString(3);
			mname = rs.getString(4);
			lname = rs.getString(5);				
			status = rs.getString(6);
			available = rs.getString(7);
			address = rs.getString(8);
			workEmail = rs.getString(9);
			personalEmail = rs.getString(10);
			notes = rs.getString(11);
			currentSalary = rs.getString(12);
			desiredSalary = rs.getString(13);
			placement = rs.getString(14);
			rotation = rs.getString(15);
			progress = rs.getString(16);
			
			name = fname + " " + mname + " " + lname;
			
			candidate = new CandidateInfo(null, salutation, fname, mname, lname, name, status, available, address, workEmail, personalEmail, notes, 
					currentSalary, desiredSalary, placement, rotation, progress);			
			
			lblName.setText(name);
			lblSalutation.setText(salutation);	
			lblStatus.setText(status);
			lblAvailable.setText(available);
			lblAddress.setText(address); 
			lblWorkEmail.setText(workEmail);
			lblPersonalEmail.setText(personalEmail);
			lblNotes.setText(notes);
			lblCurrentSalary.setText(currentSalary);
			lblDesiredSalary.setText(desiredSalary);
			lblPlacement.setText(placement);
			lblRotation.setText(rotation);
			lblProgress.setText(progress);
			
			conn.close();			
		}
		catch(SQLException e) {
			System.err.println("Error: " + e);
		}
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
