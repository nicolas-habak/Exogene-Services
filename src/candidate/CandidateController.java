package candidate;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import dbUtil.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CandidateController implements Initializable{
	/* Candidate table */
	@FXML private TableView<CandidateInfo> candidateTable;
	
	/* Candidate table columns */
	@FXML private TableColumn<CandidateInfo, String> columnName;
	@FXML private TableColumn<CandidateInfo, String> columnSalutation;
	@FXML private TableColumn<CandidateInfo, String> columnAddress;
	@FXML private TableColumn<CandidateInfo, String> columnCurrentSalary;
	@FXML private TableColumn<CandidateInfo, String> columnDesiredSalary;
	@FXML private TableColumn<CandidateInfo, String> columnRotation;
	@FXML private TableColumn<CandidateInfo, Button> columnDetailsbtn;
	
	@FXML private TextField candidateFilter;
	@FXML private Button btnAddCandidate;
	
	private DBConnection DBConn;
	private ObservableList<CandidateInfo> data;
	private FilteredList filter;
	
	private String candidateQuery = "SELECT * FROM Candidates";
	
	public void initialize(URL url, ResourceBundle rb) {
		String id;
		String salutation;
		String fname;
		String mname;
		String lname;
		String addressNo;
		String addressStreet;
		String addressCity;
		String addressProvince;
		String addressPostalCode;
		String addressCountry;
		String currentSalary;
		String desiredSalary;
		String rotation;
		String employType;

		String name;
		
		this.DBConn = new DBConnection();

		try {
			Connection conn = DBConnection.getConnection();
			this.data = FXCollections.observableArrayList();
						
			ResultSet rs = conn.createStatement().executeQuery(candidateQuery);

			while(rs.next()) {
				id = rs.getString(1);
				salutation = rs.getString(2);
				fname = rs.getString(3);
				mname = rs.getString(4);
				lname = rs.getString(5);				
				addressNo = rs.getString(6);
				addressStreet = rs.getString(7);
				addressCity = rs.getString(8);
				addressProvince = rs.getString(9);
				addressPostalCode = rs.getString(10);
				addressCountry = rs.getString(11);
				currentSalary = rs.getString(12);
				desiredSalary = rs.getString(13);
				rotation = rs.getString(14);
				employType = rs.getString(15);
				
				this.data.add(new CandidateInfo(id, salutation, fname, mname, lname, addressNo, addressStreet, addressCity,
						addressProvince, addressPostalCode, addressCountry, currentSalary, desiredSalary, rotation, employType));
			}
			filter = new FilteredList(data, e->true);

			conn.close();
		}
		catch(SQLException e) {
			System.err.println("SQLError: " + e);
		}
		catch(NullPointerException e) {
			System.err.println("Error: " + e);
		}
		
		this.columnName.setCellValueFactory(new PropertyValueFactory<CandidateInfo, String>("name"));
		this.columnSalutation.setCellValueFactory(new PropertyValueFactory<CandidateInfo, String>("salutation"));
		this.columnAddress.setCellValueFactory(new PropertyValueFactory<CandidateInfo, String>("address"));
		this.columnCurrentSalary.setCellValueFactory(new PropertyValueFactory<CandidateInfo, String>("currentSalary"));
		this.columnDesiredSalary.setCellValueFactory(new PropertyValueFactory<CandidateInfo, String>("desiredSalary"));
		this.columnRotation.setCellValueFactory(new PropertyValueFactory<CandidateInfo, String>("rotation"));
//		this.columnDetailsbtn.setCellValueFactory(new PropertyValueFactory<CandidateInfo, Button>("btnDetails"));

		this.candidateTable.setItems(null);
		this.candidateTable.setItems(this.data);
		this.candidateTable.setRowFactory( tv -> {
			TableRow<CandidateInfo> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
					CandidateInfo rowData = row.getItem();
					rowData.displayDetails();
				}
			});
			return row ;
		});
	}	
	
	@FXML
	private void addNewCandidate(ActionEvent event) throws IOException {
		Stage userStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = (Pane)loader.load(getClass().getResource("AddCandidate.fxml").openStream());
					
		Scene scene = new Scene(root);
		userStage.setScene(scene);
		userStage.setTitle("New Candidate");
		userStage.setResizable(false);
		userStage.show();
	}
	
	@SuppressWarnings("unchecked")
	@FXML
	private void filterList(KeyEvent event) {		
		candidateFilter.textProperty().addListener((observable, oldValue, newValue) -> {
			filter.setPredicate((Predicate<? super CandidateInfo>) (CandidateInfo CandidateInfo)-> {
				if (newValue.isEmpty() || newValue == null)
					return true;
				else if (CandidateInfo.nameProperty().get().toLowerCase().contains(newValue.toLowerCase()))
					return true;
				else if (CandidateInfo.salutationProperty().get().toLowerCase().contains(newValue.toLowerCase()))
					return true;
				else if (CandidateInfo.addressProperty().get().toLowerCase().contains(newValue.toLowerCase()))
					return true;
				else if (CandidateInfo.currentSalaryProperty().get().toLowerCase().contains(newValue.toLowerCase()))
					return true;
				else if (CandidateInfo.desiredSalaryProperty().get().toLowerCase().contains(newValue.toLowerCase()))
					return true;
				else if (CandidateInfo.rotationProperty().get().toLowerCase().contains(newValue.toLowerCase()))
					return true;
				return false;
			});
		});
		
		SortedList<CandidateInfo> sort = new SortedList<CandidateInfo>(filter);
		sort.comparatorProperty().bind(candidateTable.comparatorProperty());
		candidateTable.setItems(sort);
	}
	
}
