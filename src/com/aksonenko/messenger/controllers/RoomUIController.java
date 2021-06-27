package com.aksonenko.messenger.controllers;

import java.io.PrintStream;

import com.aksonenko.messenger.client.Client;
import com.aksonenko.messenger.network.TCPConnection;
import com.aksonenko.messenger.server.RoomServer;
import com.aksonenko.messenger.service.Console;
import com.aksonenko.messenger.service.SceneService;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//This controller is responsible for connecting the client to the server, inputting and outputting messages. 
public class RoomUIController {

	private TCPConnection connection;
	private String ip;
	private int port;
	private String nickname;

	private boolean serverIsRunning = false;

	@FXML
	private Label connectionNameLabel;

	@FXML
	private Button disconnectButton;

	@FXML
	private TextField text_field;

	@FXML
	private TextArea textArea;
	private PrintStream ps;

	@FXML
	public void initialize() {

		ps = new PrintStream(new Console(textArea), true);
		System.setOut(ps);
		System.setErr(ps);

		connection = ConnectionUIController.getConnection();
		ip = ConnectionUIController.getIp();
		port = ConnectionUIController.getPort();
		nickname = ConnectionUIController.getNickname();

		new Client(connection, ip, port, nickname);

		connection = Client.getConnection();

		connectionNameLabel.setText(nickname);

		serverIsRunning = RoomServer.isServerIsRunning();

		disconnectButton.setOnAction(event -> {
			connection.disconnect();

			SceneService sceneService = new SceneService();
			sceneService.openScene(disconnectButton, "/views/main.fxml");
		});

		text_field.setOnAction(event -> {

			String textField = text_field.getText().trim();

			if (!textField.equals("")) {
				connection.sendMessage(nickname + ": " + textField);			
				text_field.clear();
			}

		});

	}

}
