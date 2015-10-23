package org.srcm.pmp.utility.v2;

/**
 * @author MASTER
 *
 */
public enum ParticipantCols {
	NAME("Name*", 0), FIRST_SITTING("1st Sitting\n(Y/N/Date)", 1), SECONND_SITTING(
			"2nd Sitting\n(Y/N/Date)", 2), THIRD_SITTING(
			"3rd Sitting\n(Y/N/Date)", 3), COUNTRY("Country*", 4), STATE(
			"State*", 5), CITY("City*", 6), EMAIL("Email ID", 6), MOBILE(
			"Mobile", 7), PROFFESION("Profession", 8), DEPARTMENT(
			"Department / Stream Name", 9), BATCH_YEAR("Batch / Year", 10), RECEIVE_UPDATES(
			"Receive Updates\n(Y/N)", 11), GENDER("Gender", 12), AGE_GROUP(
			"Age Group", 13), PREF_LANGUAGE(
			"Preferred language for Communication", 14), WELCOME_CARD_NUMBER(
			"Welcome Card\nNumber (issued after 3rd sittings)", 15), WELCOME_CARD_ISSUE_DATE(
			"Welcome Card\nIssued Date", 16), REMARKS("Remarks", 17);
	private String header;
	private int column;

	private ParticipantCols(String header, int column) {
		this.header = header;
		this.column = column;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header
	 *            the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @param column
	 *            the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}

}
