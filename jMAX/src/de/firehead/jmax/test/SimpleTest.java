package de.firehead.jmax.test;

import java.io.IOException;

import de.firehead.jmax.JMAXConnection;
import de.firehead.jmax.MessageListener;
import de.firehead.jmax.messages.Message;

public class SimpleTest {

	public static void main(String[] args) {
		try {
			JMAXConnection connection = new JMAXConnection(args[0],
					Integer.parseInt(args[1]));
			connection.addListener(new MessageListener() {

				@Override
				public void messageReceived(Message aMessage) {
					System.out.println(aMessage.toDetailedString());
				}
			});
			connection.start();
		} catch (NumberFormatException exc) {
			exc.printStackTrace();
		} catch (IOException exc) {
			exc.printStackTrace();
		}

		try {
			Thread.sleep(30000);
		} catch (InterruptedException exc) {
			// ignored
		}
	}

}
