package de.firehead.jmax.messages;

import java.nio.charset.Charset;
import java.util.Base64;

public class DataBlock {

	private byte[] data;

	// First two bytes are always a magic number and a version; we ignore them.
	private int position = 2;

	public DataBlock(byte[] someData) {
		data = someData;
	}

	public DataBlock(String aDataString) {
		data = Base64.getDecoder().decode(aDataString);
	}

	public int readUnsignedByte() {
		return data[position++] & 0xFF;
	}

	public int readThreeByteInt() {
		return ((data[position++] & 0xFF) << 16)
				+ ((data[position++] & 0xFF) << 8) + (data[position++] & 0xFF);
	}

	public String readString() {
		return readString(readUnsignedByte());
	}

	public String readString(int aLength) {
		position += aLength;
		return new String(data, position - aLength, aLength,
				Charset.forName("UTF-8"));
	}

	public void skipBytes(int aLength) {
		position += aLength;
	}

}
