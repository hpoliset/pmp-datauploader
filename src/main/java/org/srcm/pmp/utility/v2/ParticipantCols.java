package org.srcm.pmp.utility.v2;

/**
 * @author MASTER
 *
 */
public enum ParticipantCols {
	NAME("Name*", 0), FIRST_SITTING("1st Sitting\n(Y/N/Date)", 1), SECONND_SITTING(
			"2nd Sitting\n(Y/N/Date)", 2), THIRD_SITTING(
			"3rd Sitting \n(Y/N/Date)", 3), COUNTRY("Country*", 4), STATE(
			"State*", 5), CITY("City*", 6), EMAIL("Email ID", 7), MOBILE(
			"Mobile", 8), PROFFESION("Profession", 9), DEPARTMENT(
			"Department / Stream Name", 10), BATCH_YEAR("Batch / Year", 11), RECEIVE_UPDATES(
			"Receive Updates\n(Y/N)", 12), GENDER("Gender", 13), AGE_GROUP(
			"Age Group", 14), PREF_LANGUAGE(
			"Preferred language for Communication", 15), WELCOME_CARD_NUMBER(
			"Welcome Card \nNumber (issued after 3rd sittings)", 16), WELCOME_CARD_ISSUE_DATE(
			"Welcome Card\nIssued Date", 17), REMARKS("Remarks", 18);
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
