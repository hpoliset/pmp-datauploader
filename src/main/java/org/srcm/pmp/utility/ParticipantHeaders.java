package org.srcm.pmp.utility;

/**
 * @author MASTER
 *
 */
public enum ParticipantHeaders {
	NAME("Full Name\n(First Name and Last Name)", 1), CITY("City", 2), STATE(
			"State", 3), EMAIL("Email Address", 4), PHONE("Phone #", 5), OCCUPATION(
			"Occupation", 6), INTRODUCED("Introduced (Completed 3 sittings)\n(Yes / No)", 7), INTR_DATE(
			"Introduced Date\n(dd/mm/yyyy)", 8), INTR_BY("Introduced By", 9), REMARKS(
			"Remarks", 10);
	private String header;
	private int col;

	private ParticipantHeaders(String header,int col) {
		this.header = header;
		this.col=col;
	}

	/**
	 * @return
	 */
	public String getHeader() {
		return header;
	}
	
	/**
	 * @return
	 */
	public int getCell(){
		return col;
	}
	
	public String toString(){
		return header;
	}

}
