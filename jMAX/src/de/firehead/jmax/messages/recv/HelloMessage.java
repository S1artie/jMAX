package de.firehead.jmax.messages.recv;

import java.util.Date;

import de.firehead.jmax.messages.Message;
import de.firehead.jmax.messages.MessageParsingException;

public class HelloMessage extends Message {

	private String serialNumber;

	private String rfAddress;

	private String firmwareVersion;

	private int dutyCycle;

	private int freeMemorySlots;

	private Date cubeDate;

	public HelloMessage(String[] someMessageParts)
			throws MessageParsingException {
		if (someMessageParts.length < 3) {
			throw new MessageParsingException("Not enough message parts");
		}

		serialNumber = someMessageParts[0];
		rfAddress = someMessageParts[1];
		if (someMessageParts[2].length() != 4) {
			throw new MessageParsingException("Invalid firmware version: "
					+ someMessageParts[2]);
		}
		firmwareVersion = someMessageParts[2].substring(0, 2) + "."
				+ someMessageParts[2].substring(2, 3) + "."
				+ someMessageParts[2].substring(3, 4);
		if (firmwareVersion.startsWith("0")) {
			firmwareVersion = firmwareVersion.substring(1);
		}
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public String getRfAddress() {
		return rfAddress;
	}

	public String getFirmwareVersion() {
		return firmwareVersion;
	}

	@Override
	public String getMessageTitle() {
		return "Hello from Cube";
	}

	@Override
	public String getMessageDetails() {
		return formatMessageDetailsLine("Serial Number", serialNumber)
				+ formatMessageDetailsLine("RF Address", rfAddress)
				+ formatMessageDetailsLine("Firmware Version", firmwareVersion);
	}

}
