module quick_room {
	exports com.aksonenko.messenger;
	exports com.aksonenko.messenger.server;
	exports com.aksonenko.messenger.client;
	exports com.aksonenko.messenger.controllers;
	exports com.aksonenko.messenger.network;

	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.controls;
	requires java.desktop;
	
	opens com.aksonenko.messenger to javafx.graphics,javafx.fxml;
	opens com.aksonenko.messenger.controllers to javafx.graphics,javafx.fxml;
}