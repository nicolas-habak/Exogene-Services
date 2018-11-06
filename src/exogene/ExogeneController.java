package exogene;

import javafx.scene.control.ComboBox;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.ResourceBundle;

import dbUtil.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;


public class ExogeneController implements Initializable{
	
	@FXML private ComboBox comboClients;
	@FXML private ComboBox comboCandidates;
	@FXML private ComboBox comboJobOrders;
	
	@FXML private Pane paneContent;
	
	private DBConnection DBConn;
	private String menuType = "";

	private Hashtable<StringProperty, Integer> inventoryList;
	private Hashtable<StringProperty, Integer> cutList;
	
	public void initialize(URL url, ResourceBundle rb) {
		String menuQuery;
	}
	
	@FXML
	private void loadClient(ActionEvent event) throws IOException {
		Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("/client/Client.fxml"));
		
		if (paneContent.getChildren().size() > 0)
			paneContent.getChildren().remove(0);
		
		paneContent.getChildren().add(newLoadedPane);
	}
	
	@FXML
	private void loadCandidate(ActionEvent event) throws IOException {
		Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("/candidate/Candidate.fxml"));

		if (paneContent.getChildren().size() > 0)
			paneContent.getChildren().remove(0);
		
		paneContent.getChildren().add(newLoadedPane);
	}
	
	@FXML
	private void loadJobOrders(ActionEvent event) throws IOException {
		Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("/joborder/JobOrder.fxml"));

		if (paneContent.getChildren().size() > 0)
			paneContent.getChildren().remove(0);
		
		paneContent.getChildren().add(newLoadedPane);
	}
}