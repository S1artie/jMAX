package de.firehead.jmax.messages;

public abstract class Message {

	protected static final String LINE_DIVIDER = System.lineSeparator();

	private static final String SPACES = "                                                             ";

	private static final int MAX_DESCRIPTION_LENGTH = 20;

	public String toDetailedString() {
		return getMessageTitle()
				+ LINE_DIVIDER
				+ "=================================================================="
				+ LINE_DIVIDER + getMessageDetails();
	}

	protected abstract String getMessageDetails();

	public abstract String getMessageTitle();

	protected static String formatMessageDetailsLine(String aDescription,
			String aValue) {
		return aDescription.length() > MAX_DESCRIPTION_LENGTH ? aDescription
				.substring(0, MAX_DESCRIPTION_LENGTH - 3) + "..."
				: aDescription
						+ SPACES.substring(
								0,
								MAX_DESCRIPTION_LENGTH
										- (aDescription.length() > MAX_DESCRIPTION_LENGTH ? MAX_DESCRIPTION_LENGTH
												: aDescription.length())) + " "
						+ aValue + LINE_DIVIDER;
	}
}
