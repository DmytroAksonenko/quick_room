package com.aksonenko.messenger.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import com.aksonenko.messenger.network.TCPConnection;
import com.aksonenko.messenger.network.TCPConnectionObserver;

//Server with Java Sockets. The server starts first and waits for incoming connections
public class RoomServer implements TCPConnectionObserver{
	
	private final ArrayList<TCPConnection> connections = new ArrayList<>();
	private TCPConnection connection;
	public static boolean serverIsRunning = false;
	
	public RoomServer() {
		System.out.println("Server running...");
		try {
			ServerSocket serverSocket = new ServerSocket(5555);
			while (true) {
				try {
					connection = new TCPConnection(this, serverSocket.accept());
					serverIsRunning = true;
				} catch (Exception e) {
					System.out.println("TCPConnection exception: " + e);
				}

			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public synchronized void connectionReady(TCPConnection connection) {
		connections.add(connection);
		sendToAllConnections("Client connected: " + connection);
	}

	@Override
	public synchronized void recieveString(TCPConnection connection, String message) {
		sendToAllConnections(message);
	}

	@Override
	public synchronized void disconnect(TCPConnection connection) {
		connections.remove(connection);
		sendToAllConnections("Client disconnected: " + connection);
	}

	@Override
	public synchronized void exception(TCPConnection connection, Exception e) {
		System.out.println("TCPConnection exception: " + e);
	}
	
	private void sendToAllConnections(String message) {
//		System.out.println("Server Observer: " + message);
		for(int i = 0; i < connections.size(); i++) {
			connections.get(i).sendMessage(message);
		}
	}
	
	public void stopServer() {
		connection.disconnect();
	}
	
	public static boolean isServerIsRunning() {
		return serverIsRunning;
	}

	public static void setServerIsRunning(boolean serverIsRunning) {
		RoomServer.serverIsRunning = serverIsRunning;
	}
}
