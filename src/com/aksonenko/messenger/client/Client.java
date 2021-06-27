package com.aksonenko.messenger.client;

import java.io.IOException;

import com.aksonenko.messenger.network.TCPConnection;
import com.aksonenko.messenger.network.TCPConnectionObserver;

//Socket client implementation.
public class Client implements TCPConnectionObserver {

	private static TCPConnection connection;
	private String ipAddress;
	private int port;
	private String nickname;
	private String message;

	public Client(TCPConnection connection, String ipAddress, int port, String nickname) {

		this.ipAddress = ipAddress;
		this.port = port;
		this.nickname = nickname;

		try {
			connection = new TCPConnection(this, ipAddress, port, nickname);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.connection = connection;

	}

	@Override
	public synchronized void connectionReady(TCPConnection connection) {
		printMessage("Connection ready");
	}

	@Override
	public synchronized void recieveString(TCPConnection connection, String message) {
		printMessage(message);
	}

	@Override
	public synchronized void disconnect(TCPConnection connection) {
		printMessage("Connection close");
	}

	@Override
	public synchronized void exception(TCPConnection connection, Exception e) {
		printMessage("Connection exception: " + e);
	}

	public synchronized static void printMessage(String message) {

//		System.out.append("Client Observer: " + message + "\r\n");
		System.out.append(message + "\r\n");

	}

	public static TCPConnection getConnection() {
		return connection;
	}

	public void setConnection(TCPConnection connection) {
		this.connection = connection;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
