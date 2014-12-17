package de.firehead.jmax.structures;

import java.util.ArrayList;
import java.util.List;

import de.firehead.jmax.util.StringUtil;

public class Room implements Structure {

	private int id;

	private String name;

	private int address;

	private List<Device> devices = new ArrayList<Device>();

	public Room(int aId, String aName, int aAddress) {
		super();
		id = aId;
		name = aName;
		address = aAddress;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAddress() {
		return address;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void initDevices(List<Device> someDevices) {
		for (Device device : someDevices) {
			if (device.getRoomID() == id) {
				devices.add(device);
			}
		}
	}

	@Override
	public String toString() {
		return "Room " + id + " (" + name + "), Address "
				+ StringUtil.formatRFAddress(address) + " with "
				+ devices.size() + " devices";
	}

	@Override
	public String toDetailedString() {
		StringBuilder builder = new StringBuilder();
		builder.append(toString());
		builder.append(":" + LINE_DIVIDER);
		for (Device device : devices) {
			builder.append(INDENTATION);
			builder.append(device.toDetailedString());
			builder.append(LINE_DIVIDER);
		}

		return builder.toString();
	}
}
