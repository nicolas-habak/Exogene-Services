package joborder;

import client.ClientInfo;
import client.ContactInfo;
import client.JobsInfo;
import dbUtil.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JobOrderDetailsController implements Initializable {

	/* Client information fields */
	@FXML private Label lblName;
	@FXML private Label lblIndustryType;
	@FXML private Label lblParentCompany;
	@FXML private Label lblStatus;
	@FXML private Label lblFee; 
	
	/* Contacts table columns */
	@FXML private TableColumn columnDepartment;
	@FXML private TableColumn columnTitle;
	@FXML private TableColumn columnName;
	@FXML private TableColumn columnPhone;
	@FXML private TableColumn columnEmail;
	@FXML private TableColumn columnNotes;
	@FXML private TableColumn columnRemoveBtn;
		
	/* Jobs table columns */
	@FXML private TableColumn columnEmployType;
	@FXML private TableColumn columnStatus;
	@FXML private TableColumn columnStatusNote;
	@FXML private TableColumn columnStartDate;
	
	@FXML private TableView contactsTable;
	@FXML private TableView jobsTable;
	
	private ObservableList<ContactInfo> dataContacts;
	private ObservableList<JobsInfo> dataJobs;
	
	private DBConnection DBConn;
	
	public void initialize(URL url, ResourceBundle rb) {
		
	}
	
	public void setFields(String name) {
		String industryType;
		String parentCompany;
		String status;
		String fee;
		
		String clientDetailsQuery =  "SELECT * FROM Clients where name = '" + name + "'";		
		
		this.DBConn = new DBConnection();

		try {
			Connection conn = DBConnection.getConnection();
			
			ResultSet rs = conn.createStatement().executeQuery(clientDetailsQuery);
			ClientInfo client;

			name = rs.getString(2);
			industryType = rs.getString(3);
			parentCompany = rs.getString(4);
			status = rs.getString(5);
			fee = rs.getString(6);
			
			client = new ClientInfo(null, name, industryType, parentCompany, status, fee);
			
			lblName.setText(name);
			lblIndustryType.setText(industryType);
			lblParentCompany.setText(parentCompany);
			lblStatus.setText(status);
			lblFee.setText(fee);
			
			conn.close();			
		}
		catch(SQLException e) {
			System.err.println("Error: " + e);
		}
	}
	
	public void setClientContactTable(String clientName) {
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
	
	public void setContractTable(String clientName) {
		String employType;
		String status;
		String statusNote;
		String startDate;
		
		String contactsQuery = "SELECT EMPLOYTYPE, STATUS, STATUSNOTE, STARTDATE FROM JobOrder WHERE COMPANY = '" + clientName + "'";
		
		this.DBConn = new DBConnection();

		try {
			Connection conn = DBConnection.getConnection();
			this.dataJobs = FXCollections.observableArrayList();
						
			ResultSet rs = conn.createStatement().executeQuery(contactsQuery);

			while(rs.next()) {
				employType = rs.getString(1);
				status = rs.getString(2);
				statusNote = rs.getString(3);
				startDate = rs.getString(4);
				
				this.dataJobs.add(new JobsInfo(null, clientName, employType, status, statusNote, startDate));
			}
			conn.close();
		}
		catch(SQLException e) {
			System.err.println("Error: " + e);
		}		
				
		this.columnEmployType.setCellValueFactory(new PropertyValueFactory<JobsInfo, String>("employType"));
		this.columnStatus.setCellValueFactory(new PropertyValueFactory<JobsInfo, String>("status"));
		this.columnStatusNote.setCellValueFactory(new PropertyValueFactory<JobsInfo, String>("statusNote"));
		this.columnStartDate.setCellValueFactory(new PropertyValueFactory<JobsInfo, String>("startDate"));		
		
		this.jobsTable.setItems(null);
		this.jobsTable.setItems(this.dataJobs);
	}

	@FXML
	private void addContact(ActionEvent event){
		try {
			Stage userStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = (Pane) loader.load(getClass().getResource("AddContact.fxml").openStream());

			Scene scene = new Scene(root);
			userStage.setScene(scene);
			userStage.setTitle("New Job Order");
			userStage.setResizable(false);
			userStage.show();
		}catch (Exception e){
			System.err.println(e);
		}
	}
}
