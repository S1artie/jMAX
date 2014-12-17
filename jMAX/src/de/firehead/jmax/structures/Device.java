package de.firehead.jmax.structures;

import de.firehead.jmax.util.StringUtil;

public class Device implements Structure {

	private DeviceType type;

	private int address;

	private String serialNumber;

	private String name;

	private int roomID;

	public Device(DeviceType aType, int aAddress, String aSerialNumber,
			String aName, int aRoomID) {
		super();
		type = aType;
		address = aAddress;
		serialNumber = aSerialNumber;
		name = aName;
		roomID = aRoomID;
	}

	public DeviceType getType() {
		return type;
	}

	public int getAddress() {
		return address;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public String getName() {
		return name;
	}

	public int getRoomID() {
		return roomID;
	}

	@Override
	public String toString() {
		return type + " (" + name + ") " + serialNumber + ", Address "
				+ StringUtil.formatRFAddress(address);
	}

	@Override
	public String toDetailedString() {
		return toString();
	}

}
