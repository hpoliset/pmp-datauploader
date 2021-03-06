package org.srcm.pmp.utility.v2;

/**
 * @author MASTER
 *
 */
public enum ProgramCols {
	EVENT_TYPE("Event Type*", 0, 2), OTHER("Other", 2, 2), EVENT_PLACE(
			"Event Place*", 0, 3), EVENT_DATE("Event Date (DD-MMM-YY)*", 2, 3), EVENT_COUNTRY(
			"Event Country*", 0, 4), EVENT_STATE("Event State*", 2, 4), EVENT_CITY(
			"Event City*", 0, 5), EVENT_COORDINATORNAME(
			"Event Coordinator Name*", 0, 6), EVENT_COORDINATOR_MOBILE(
			"Event Coordinator Mobile*", 0, 7), EVENT_COORDINATOR_MAIL(
			"Event Coordinator Email ID*", 2, 7), ORGANIZATION_NAME(
			"Organisation Name*", 0, 9), ORGANIZATION_CONTACT_PERSON(
			"Organisation Contact Person*", 2, 9), ORGANIZATION_WEBSITE(
			"Organisation Website", 0, 10), ORGANIZATION_CONTACT_MAILID(
			"Organisation Contact Email ID*", 2, 10), ORGANIZATION_CONTACT_MOBILE(
			"Organisation Contact Mobile*", 2, 11), PRECEPTOR_NAME(
			"Preceptor Name*", 0, 13), WELCOME_CARD_SIGNEDBY(
			"Welcome Card Signed By", 2, 13), PRECEPTOR_ID("Preceptor ID*", 0,
			14), WELCOME_CARD_SIGNER_ID("Welcome Card Signer's ID", 2, 14), REMARKS(
			"Remarks:", 0, 16);
	private String header;
	private int cell;
	private int row;

	private ProgramCols(String header, int cell, int row) {
		this.header = header;
		this.cell = cell;
		this.row = row;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row
	 *            the row to set
	 */
	public void setRow(int row) {
		this.row = row;
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
	 * @return the cell
	 */
	public int getCell() {
		return cell;
	}

	/**
	 * @param cell
	 *            the cell to set
	 */
	public void setCell(int cell) {
		this.cell = cell;
	}
	
	
}
