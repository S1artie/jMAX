package de.firehead.jmax.structures.devicestates;

public class ThermostatDeviceState implements DeviceState {

	private boolean batteryLow;

	private boolean transmitError;

	private boolean panelLock;

	private boolean gatewayKnown;

	private boolean dstState;

	private DeviceMode mode;

	private double setPointTemperature;

	private double temperatureOffset;

}
