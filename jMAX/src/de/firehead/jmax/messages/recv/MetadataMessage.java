package de.firehead.jmax.messages.recv;

import java.util.ArrayList;
import java.util.List;

import de.firehead.jmax.messages.DataBlock;
import de.firehead.jmax.messages.Message;
import de.firehead.jmax.messages.MessageParsingException;
import de.firehead.jmax.structures.Device;
import de.firehead.jmax.structures.DeviceType;
import de.firehead.jmax.structures.Room;

public class MetadataMessage extends Message {

	private List<Room> rooms;

	public MetadataMessage(String aDataString) throws MessageParsingException {
		DataBlock block = new DataBlock(aDataString);

		int roomCount = block.readUnsignedByte();
		rooms = new ArrayList<Room>(roomCount);
		for (int i = 0; i < roomCount; i++) {
			rooms.add(new Room(block.readUnsignedByte(), block.readString(),
					block.readThreeByteInt()));
		}

		int deviceCount = block.readUnsignedByte();
		List<Device> devices = new ArrayList<Device>(deviceCount);
		for (int i = 0; i < deviceCount; i++) {
			devices.add(new Device(
					DeviceType.getByID(block.readUnsignedByte()), block
							.readThreeByteInt(), block.readString(10), block
							.readString(), block.readUnsignedByte()));
		}

		for (Room room : rooms) {
			room.initDevices(devices);
		}
	}

	@Override
	protected String getMessageDetails() {
		StringBuilder builder = new StringBuilder();
		for (Room room : rooms) {
			builder.append(room.toDetailedString());
		}

		return builder.toString();
	}

	@Override
	public String getMessageTitle() {
		return "Metadata";
	}

}
