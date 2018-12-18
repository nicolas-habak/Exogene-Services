package client;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dbUtil.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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

public class ClientDetailsController implements Initializable {

	/* Client information fields */
	@FXML private Label lblName;
	@FXML private Label lblIndustryType;
	@FXML private Label lblParentCompany;
	@FXML private Label lblStatus;
	@FXML private Label lblFee; 
	
	/* Contacts table columns */
	@FXML private TableColumn columnSalutation;
	@FXML private TableColumn columnName;
	@FXML private TableColumn columnDepartment;
	@FXML private TableColumn columnInfoType;
	@FXML private TableColumn columnInfo;
	@FXML private TableColumn columnLanguage;
		
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
	
	public void setFields(String id, String name, String industryType, String parentCompany, String fee) {
		lblName.setText(name);
		lblIndustryType.setText(industryType);
		lblParentCompany.setText(parentCompany);
		lblFee.setText(fee);
		setClientContactTable(id);
	}
	
	public void setClientContactTable(String id) {
		String salutation;
		String fname;
		String mname;
		String lname;
		String department;
		String infoType;
		String info;
		String language;
		Integer i;

		String contactsQuery = "SELECT ClientContact.id, ClientContact.salutation, ClientContact.fname, ClientContact.mname, ClientContact.lname, ClientContact.department," +
				" ContactInfo.infoType, ContactInfo.info, ClientContact.language" +
				" FROM ContactInfo LEFT JOIN ClientContact on ContactInfo.contact_id = ClientContact.id" +
				" WHERE ClientContact.client_id = " + id + " and contactType = \"ClientContact\"" +
				" ORDER BY ClientContact.lname, ClientContact.mname, ClientContact.fname";

		this.DBConn = new DBConnection();

		try {
			Connection conn = DBConnection.getConnection();
			this.dataContacts = FXCollections.observableArrayList();
						
			ResultSet rs = conn.createStatement().executeQuery(contactsQuery);

			while(rs.next()) {
				i = 1;
				id = rs.getString(i++);
				salutation = rs.getString(i++);
				fname = rs.getString(i++);
				mname = rs.getString(i++);
				lname = rs.getString(i++);
				department = rs.getString(i++);
				infoType = rs.getString(i++);
				info = rs.getString(i++);
				language = rs.getString(i++);
				this.dataContacts.add(new ContactInfo(id, salutation, fname, mname, lname, department, infoType, info, language));
			}
			conn.close();
		}
		catch(SQLException e) {
			System.err.println("Error: " + e);
		}

		this.columnSalutation.setCellValueFactory(new PropertyValueFactory<ContactInfo, String>("salutation"));
		this.columnName.setCellValueFactory(new PropertyValueFactory<ContactInfo, String>("name"));
		this.columnDepartment.setCellValueFactory(new PropertyValueFactory<ContactInfo, String>("department"));
		this.columnInfoType.setCellValueFactory(new PropertyValueFactory<ContactInfo, String>("infoType"));
		this.columnInfo.setCellValueFactory(new PropertyValueFactory<ContactInfo, String>("info"));
		this.columnLanguage.setCellValueFactory(new PropertyValueFactory<ContactInfo, String>("language"));

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
	private void addContact(ActionEvent event) {
		try {
			Stage userStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = (Pane) loader.load(getClass().getResource("AddContact.fxml").openStream());

			Scene scene = new Scene(root);
			userStage.setScene(scene);
			userStage.setTitle("New Contact");
			userStage.setResizable(false);
			userStage.show();
		}catch (Exception e){
			System.err.println(e);
		}
	}
}
