package com.aksonenko.messenger.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;

//This class creates the TCP connection required for the client and server to work.
public class TCPConnection {

	private final Socket socket;
	private final Thread thread;
	private final TCPConnectionObserver observer;
	private final BufferedReader in;
	private final BufferedWriter out;

	public TCPConnection(TCPConnectionObserver observer, String ipAddress, int port, String nickname)
			throws IOException {
		this(observer, new Socket(ipAddress, port));
	}

	public TCPConnection(TCPConnectionObserver observer, Socket socket) throws IOException {
		this.observer = observer;
		this.socket = socket;

		in = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					observer.connectionReady(TCPConnection.this);
					while (!thread.isInterrupted()) {
						observer.recieveString(TCPConnection.this, in.readLine());
					}
				} catch (IOException e) {
					observer.exception(TCPConnection.this, e);
				} finally {
					observer.disconnect(TCPConnection.this);
				}
			}
		});
		thread.start();
	}

	public void sendMessage(String message) {
		try {
			out.write(message + "\r\n");
			out.flush();
		} catch (IOException e) {
			observer.exception(TCPConnection.this, e);
			disconnect();
		}
	}

	public synchronized void disconnect() {
		thread.interrupt();
		try {
			socket.close();
		} catch (IOException e) {
			observer.exception(TCPConnection.this, e);
		}
	}

	@Override
	public String toString() {
		return "TCPConnection: " + socket.getInetAddress() + ": " + socket.getPort();
	}

}
