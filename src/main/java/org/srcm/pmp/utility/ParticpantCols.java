/**
 * 
 */
package org.srcm.pmp.utility;

/**
 * @author MASTER
 *
 */
public enum ParticpantCols {
	NAME(1), CITY(2), STATE(3), EMAIL(4), PHONE(5),OCCUPATION(6), INTRODUCED(7), INTR_DATE(
			8), INTR_BY(9),REMARKS(10);
	private int col;

	private ParticpantCols(int col) {
		this.col = col;
	}

	/**
	 * @return
	 */
	public int getCol() {
		return col;
	}

}
