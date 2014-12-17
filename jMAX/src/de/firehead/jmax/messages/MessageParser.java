package de.firehead.jmax.messages;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import de.firehead.jmax.messages.recv.HelloMessage;
import de.firehead.jmax.messages.recv.MetadataMessage;

public class MessageParser {

	public static Message parseMessage(byte[] someBytes)
			throws MessageParsingException {
		if (someBytes.length < 4) {
			throw new MessageParsingException("Message length too short: "
					+ someBytes.length);
		}

		char messageType = (char) someBytes[0];
		if (someBytes[1] != ':') {
			throw new MessageParsingException("Invalid message type divider");
		}

		String messageText = new String(someBytes, 2, someBytes.length - 2,
				Charset.forName("US-ASCII"));
		String[] messageParts = split(messageText, ',');

		switch (messageType) {
		case 'H':
			return new HelloMessage(messageParts);
		case 'M':
			return new MetadataMessage(messageParts[2]);

		default:
			return null;
		}
	}

	public static String[] split(String strToSplit, char delimiter) {
		List<String> arr = new ArrayList<>();
		int foundPosition;
		int startIndex = 0;
		while ((foundPosition = strToSplit.indexOf(delimiter, startIndex)) > -1) {
			arr.add(strToSplit.substring(startIndex, foundPosition));
			startIndex = foundPosition + 1;
		}
		arr.add(strToSplit.substring(startIndex));
		return arr.toArray(new String[arr.size()]);
	}

}
