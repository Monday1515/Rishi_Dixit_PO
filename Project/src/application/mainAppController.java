package application;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.SVGPath;


public class mainAppController {

	
	@FXML
	private Pane pane3;
	
	@FXML 
	private SVGPath searchIcon;
	
	@FXML
	private SVGPath settingIcon;
	
	@FXML
	private SVGPath exitIcon;
	
	@FXML
	private void hoverSearchIcon() {
		searchIcon.setStyle("-fx-fill: #bbbbbb;");
	}
	
	@FXML
	private void hoverSettingIcon() {
		settingIcon.setStyle("-fx-fill: #bbbbbb;");
	}
	
	@FXML
	private void hoverExitIcon() {
		exitIcon.setStyle("-fx-fill: #8a2b2b;");
	}
	
	@FXML
	private void resetSearchIcon() {
	    searchIcon.setStyle("-fx-fill: #dddddd;");
	}

	@FXML
	private void resetSettingIcon() {
	    settingIcon.setStyle("-fx-fill: #dddddd;");
	}
	
	@FXML
	private void resetExitIcon() {
	    exitIcon.setStyle("-fx-fill: #dddddd;");
	}

	
}
