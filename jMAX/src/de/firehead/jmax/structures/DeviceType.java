package de.firehead.jmax.structures;

public enum DeviceType {

	CUBE(0),

	THERMOSTAT(1),

	THERMOSTAT_PLUS(2),

	WALL_THERMOSTAT(3),

	SHUTTER_CONTACT(4),

	BUTTON(5);

	private DeviceType(int anID) {
		id = anID;
	}

	private int id;

	public int getId() {
		return id;
	}

	public static DeviceType getByID(int anID) {
		for (DeviceType type : values()) {
			if (type.getId() == anID) {
				return type;
			}
		}

		return null;
	}

}
