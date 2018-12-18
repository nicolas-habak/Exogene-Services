package client;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import dbUtil.DBConnection;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ClientController implements Initializable{

	/* Client table */
	@FXML private TableView<ClientInfo> clientTable;
	
	/* Client table columns */	
	@FXML private TableColumn<ClientInfo, String> columnName;
	@FXML private TableColumn<ClientInfo, String> columnIndustryType;
	@FXML private TableColumn<ClientInfo, String> columnParentCompany;
	@FXML private TableColumn<ClientInfo, String> columnFee;
	@FXML private TableColumn<ClientInfo, Button> columnDetailsbtn;
	
	@FXML private TextField clientFilter;
	@FXML private Button btnAddClient;	
	
	private DBConnection DBConn;
	private ObservableList<ClientInfo> data;
	private FilteredList filter;

	private Stage userStage = null;
	
	private String clientQuery = "SELECT * FROM Clients";

	public void initialize(URL url, ResourceBundle rb) {
		initTable();
	}	

	private void initTable() {
		String id;
		String name;
		String industryType;
		String parentCompany;
		String fee;

		this.DBConn = new DBConnection();

		try {
			Connection conn = DBConnection.getConnection();
			this.data = FXCollections.observableArrayList();

			ResultSet rs = conn.createStatement().executeQuery(clientQuery);

			while(rs.next()) {
				id = rs.getString(1);
				name = rs.getString(2);
				industryType = rs.getString(3);
				parentCompany = rs.getString(4);
				fee = rs.getString(5);

				this.data.add(new ClientInfo(id, name, industryType, parentCompany, fee));
			}
			filter = new FilteredList(data, e->true);
			conn.close();
		}
		catch(SQLException e) {
			System.err.println("Error: " + e);
		}

		this.columnName.setCellValueFactory(new PropertyValueFactory<ClientInfo, String>("name"));
		this.columnIndustryType.setCellValueFactory(new PropertyValueFactory<ClientInfo, String>("industryType"));
		this.columnParentCompany.setCellValueFactory(new PropertyValueFactory<ClientInfo, String>("parentCompany"));
		this.columnFee.setCellValueFactory(new PropertyValueFactory<ClientInfo, String>("fee"));
//		this.columnDetailsbtn.setCellValueFactory(new PropertyValueFactory<ClientInfo, Button>("btnDetails"));

		this.clientTable.setItems(null);
		this.clientTable.setItems(this.data);
		this.clientTable.setRowFactory( tv -> {
			TableRow<ClientInfo> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
					ClientInfo rowData = row.getItem();
					rowData.displayDetails();
				}
			});
			return row ;
		});
	}

	@FXML
	private void addNewClient(ActionEvent event) throws IOException {
		if(userStage == null) {
			userStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = (Pane) loader.load(getClass().getResource("AddClient.fxml").openStream());

			Scene scene = new Scene(root);
			userStage.setScene(scene);
			userStage.setTitle("New Client");
			userStage.setResizable(false);
			userStage.showAndWait();
			userStage = null;
			initTable();
		} else {
			userStage.setAlwaysOnTop(true);
			userStage.setAlwaysOnTop(false);
		}
	}	
	
	@SuppressWarnings("unchecked")
	@FXML
	private void filterList(KeyEvent event) {		
		clientFilter.textProperty().addListener((observable, oldValue, newValue) -> {
			filter.setPredicate((Predicate<? super ClientInfo>) (ClientInfo clientInfo)-> {
				if (newValue.isEmpty() || newValue == null)
					return true;
				else if (clientInfo.nameProperty().get().toLowerCase().contains(newValue.toLowerCase()))
					return true;
				else if (clientInfo.industryTypeProperty().get().toLowerCase().contains(newValue.toLowerCase()))
					return true;
				else if (clientInfo.parentCompanyProperty().get().toLowerCase().contains(newValue.toLowerCase()))
					return true;
				else if (clientInfo.feeProperty().get().toLowerCase().contains(newValue.toLowerCase()))
					return true;
				
				return false;
			});
		});
		
		SortedList<ClientInfo> sort = new SortedList<ClientInfo>(filter);
		sort.comparatorProperty().bind(clientTable.comparatorProperty());
		clientTable.setItems(sort);
	}
	
}
