package com.aksonenko.messenger.service;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

//This class automates the creation of additional Stage objects
public class SceneService {
	
	public void openScene(Button button, String path) {
		button.getScene().getWindow().hide();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(path));
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Parent content = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(content));
		stage.setResizable(false);
		stage.show();
	}
}
