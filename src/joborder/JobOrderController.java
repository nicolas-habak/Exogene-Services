package joborder;

import client.ClientInfo;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import dbUtil.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JobOrderController implements Initializable{
			
	/* Job Order table */
	@FXML private TableView<JobOrderInfo> jobOrderTable;
	
	/* Job Order table columns */
	@FXML private TableColumn<JobOrderInfo, String> columnCompany;
	@FXML private TableColumn<JobOrderInfo, String> columnEmployType;
	@FXML private TableColumn<JobOrderInfo, String> columnStatus;
	@FXML private TableColumn<JobOrderInfo, String> columnStatusNote;
	@FXML private TableColumn<JobOrderInfo, String> columnStartDate;
	@FXML private TableColumn<JobOrderInfo, Button> columnDetailsbtn;

	@FXML private TextField jobOrderFilter;
	@FXML private Button btnAddJobOrder;	
	
	private DBConnection DBConn;
	private ObservableList<JobOrderInfo> data;
	private FilteredList filter;
	
	private String jobOrderQuery = "SELECT * FROM JobOrder";
	
	public void initialize(URL url, ResourceBundle rb) {
		String company;
		String employType;
		String status;
		String statusNote;
		String startDate;
		
		this.DBConn = new DBConnection();

		try {
			Connection conn = DBConnection.getConnection();
			this.data = FXCollections.observableArrayList();
						
			ResultSet rs = conn.createStatement().executeQuery(jobOrderQuery);

			while(rs.next()) {
				company = rs.getString(2);
				employType = rs.getString(3);
				status = rs.getString(4);
				statusNote = rs.getString(5);
				startDate = rs.getString(6);
				
				this.data.add(new JobOrderInfo(null, company, employType, status, statusNote, startDate));
			}
			filter = new FilteredList(data, e->true);
			conn.close();
		}
		catch(SQLException e) {
			System.err.println("Error: " + e);
		}		
				
		this.columnCompany.setCellValueFactory(new PropertyValueFactory<JobOrderInfo, String>("company"));
		this.columnEmployType.setCellValueFactory(new PropertyValueFactory<JobOrderInfo, String>("employType"));
		this.columnStatus.setCellValueFactory(new PropertyValueFactory<JobOrderInfo, String>("status"));
		this.columnStatusNote.setCellValueFactory(new PropertyValueFactory<JobOrderInfo, String>("statusNote"));
		this.columnStartDate.setCellValueFactory(new PropertyValueFactory<JobOrderInfo, String>("startDate"));
		this.columnDetailsbtn.setCellValueFactory(new PropertyValueFactory<JobOrderInfo, Button>("btnDetails"));
		
		this.jobOrderTable.setItems(null);
		this.jobOrderTable.setItems(this.data);
	}	
	
	@FXML
	private void addNewJobOrder(ActionEvent event) throws IOException {
		Stage userStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = (Pane)loader.load(getClass().getResource("AddJobOrder.fxml").openStream());
					
		Scene scene = new Scene(root);
		userStage.setScene(scene);
		userStage.setTitle("New Job Order");
		userStage.setResizable(false);
		userStage.show();
	}	
	
	@SuppressWarnings("unchecked")
	@FXML
	private void filterList(KeyEvent event) {		
		jobOrderFilter.textProperty().addListener((observable, oldValue, newValue) -> {
			filter.setPredicate((Predicate<? super JobOrderInfo>) (JobOrderInfo JobOrderInfo)-> {
				if (newValue.isEmpty() || newValue == null)
					return true;
				else if (JobOrderInfo.companyProperty().get().toLowerCase().contains(newValue.toLowerCase()))
					return true;
				else if (JobOrderInfo.employTypeProperty().get().toLowerCase().contains(newValue.toLowerCase()))
					return true;
				else if (JobOrderInfo.statusProperty().get().toLowerCase().contains(newValue.toLowerCase()))
					return true;
				else if (JobOrderInfo.statusNoteProperty().get().toLowerCase().contains(newValue.toLowerCase()))
					return true;
				else if (JobOrderInfo.startDateProperty().get().toLowerCase().contains(newValue.toLowerCase()))
					return true;
				
				return false;
			});
		});
		
		SortedList<JobOrderInfo> sort = new SortedList<JobOrderInfo>(filter);
		sort.comparatorProperty().bind(jobOrderTable.comparatorProperty());
		jobOrderTable.setItems(sort);
	}
	
}
