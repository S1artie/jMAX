package de.firehead.jmax.messages;

public class MessageParsingException extends Exception {

	private static final long serialVersionUID = 6053745351960586926L;

	public MessageParsingException() {
		super();
	}

	public MessageParsingException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MessageParsingException(String message, Throwable cause) {
		super(message, cause);
	}

	public MessageParsingException(String message) {
		super(message);
	}

	public MessageParsingException(Throwable cause) {
		super(cause);
	}

}
