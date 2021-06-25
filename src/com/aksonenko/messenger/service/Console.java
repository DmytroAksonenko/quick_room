package com.aksonenko.messenger.service;

import java.io.IOException;
import java.io.OutputStream;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class Console extends OutputStream {
	private TextArea textArea;

	public Console(TextArea textArea) {
		this.textArea = textArea;
	}

	public void appendText(String valueOf) {
		Platform.runLater(() -> textArea.appendText(valueOf));
	}

	public void write(int i) throws IOException {
		appendText(String.valueOf((char) i));
	}
}
