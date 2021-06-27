package com.aksonenko.messenger.controllers;

import com.aksonenko.messenger.network.TCPConnection;
import com.aksonenko.messenger.service.SceneService;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

//This controller is responsible for the user filling in the fields required to create the connection.
public class ConnectionUIController {

	private static TCPConnection connection;
	private static String ip;
	private static int port;
	private static String nickname;
	
    @FXML
    private Button connectButton;
    
    @FXML
    private TextField nickname_field;

    @FXML
    private TextField ip_field;

    @FXML
    private TextField port_field;
    
    @FXML
	void initialize() {
    	connectButton.setOnAction(event -> {
    		ip = ip_field.getText().trim();
    		port = Integer.parseInt(port_field.getText().trim());
    		nickname = nickname_field.getText().trim();  
    		
    		if(!nickname.equals("") && !ip.equals("")) {
    			SceneService sceneService = new SceneService();
    			sceneService.openScene(connectButton, "/views/room.fxml");
    		}
		});
	}

	public static TCPConnection getConnection() {
		return connection;
	}

	public static void setConnection(TCPConnection connection) {
		ConnectionUIController.connection = connection;
	}

	public static String getIp() {
		return ip;
	}

	public static void setIp(String ip) {
		ConnectionUIController.ip = ip;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		ConnectionUIController.port = port;
	}

	public static String getNickname() {
		return nickname;
	}

	public static void setNickname(String nickname) {
		ConnectionUIController.nickname = nickname;
	}
    
}
