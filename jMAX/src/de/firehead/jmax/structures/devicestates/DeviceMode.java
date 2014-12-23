package de.firehead.jmax.structures.devicestates;

public enum DeviceMode {

	AUTO(0),

	MANUAL(1),

	VACATION(2),

	BOOST(3);

	private DeviceMode(int anID) {
		id = anID;
	}

	private int id;

	public int getId() {
		return id;
	}

	public static DeviceMode getByID(int anID) {
		for (DeviceMode type : values()) {
			if (type.getId() == anID) {
				return type;
			}
		}

		return null;
	}

}
