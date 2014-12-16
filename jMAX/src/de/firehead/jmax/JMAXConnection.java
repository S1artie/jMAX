package de.firehead.jmax;

import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import de.firehead.jmax.messages.Message;
import de.firehead.jmax.messages.MessageParser;
import de.firehead.jmax.messages.MessageParsingException;

public class JMAXConnection {

	private Socket socket;

	private MessageProcessingThread processingThread;

	private List<MessageListener> listeners = Collections
			.synchronizedList(new LinkedList<MessageListener>());

	public JMAXConnection(String aHost, int aPort) throws IOException {
		socket = new Socket(aHost, aPort);
	}

	public void start() {
		if (processingThread == null) {
			processingThread = new MessageProcessingThread();
			processingThread.start();
		}
	}

	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			// ignore
		}
		socket = null;
	}

	public void addListener(MessageListener aListener) {
		if (!listeners.contains(aListener)) {
			listeners.add(aListener);
		}
	}

	public void removeListener(MessageListener aListener) {
		listeners.remove(aListener);
	}

	private void processMessage(Message aMessage) {
		if (listeners.size() > 0) {
			synchronized (listeners) {
				for (MessageListener listener : listeners) {
					listener.messageReceived(aMessage);
				}
			}
		}
	}

	private class MessageProcessingThread extends Thread {

		@Override
		public void run() {
			byte[] buffer = new byte[10000];
			int bufferPos = 0;

			try {
				while (socket != null && socket.isConnected()) {
					int bytesRead = socket.getInputStream().read(buffer,
							bufferPos, buffer.length - bufferPos);
					if (bytesRead > 0) {
						for (int i = bufferPos; i < bufferPos + bytesRead; i++) {
							if (buffer[i] == 0x0A && i > 1) {
								byte[] singleMessage = new byte[i - 1];
								System.arraycopy(buffer, 0, singleMessage, 0,
										singleMessage.length);
								int bytesLeft = bufferPos + bytesRead - (i + 1);
								System.arraycopy(buffer, i + 1, buffer, 0,
										bytesLeft);
								bytesRead -= singleMessage.length + 2;
								if (bytesRead < 0) {
									bytesRead = 0;
								}
								bufferPos = 0;
								i = 0;

								try {
									Message message = MessageParser
											.parseMessage(singleMessage);
									if (message != null) {
										processMessage(message);
									}
								} catch (MessageParsingException exc) {
									exc.printStackTrace();
								}
							}
						}

						bufferPos += bytesRead;
					}
				}
			} catch (IOException exc) {
				exc.printStackTrace();
				close();
			}
		}
	}

}
