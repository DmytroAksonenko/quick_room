package com.aksonenko.messenger.controllers;

import com.aksonenko.messenger.server.RoomServer;
import com.aksonenko.messenger.service.SceneService;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainUIController {
	
	private Thread thread;

	@FXML
	private Button createARoomButton;

	@FXML
	private Button joinAnExistingButton;
	
	@FXML
	void initialize() {
		
		thread = new Thread(new Runnable() {

			@Override
			public void run() {
				new RoomServer();
				
			}
			
		});
		
		createARoomButton.setOnAction(event -> {
			SceneService sceneService = new SceneService();
			sceneService.openScene(createARoomButton, "/views/connection.fxml");
			thread.start();		
		});
		
		joinAnExistingButton.setOnAction(event -> {
			SceneService sceneService = new SceneService();
			sceneService.openScene(createARoomButton, "/views/connection.fxml");
		});
		
	}
}
