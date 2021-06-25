package com.aksonenko.messenger.network;

public interface TCPConnectionObserver {

	void connectionReady(TCPConnection roomConnection);

	void recieveString(TCPConnection roomConnection, String string);

	void disconnect(TCPConnection roomConnection);

	void exception(TCPConnection roomConnection, Exception e);

}
