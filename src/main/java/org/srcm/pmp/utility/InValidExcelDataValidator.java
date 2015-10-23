/**
 * 
 */
package org.srcm.pmp.utility;

/**
 * @author MASTER
 *
 */
public class InValidExcelDataValidator implements ExcelDataValidator {

	/* (non-Javadoc)
	 * @see org.srcm.pmp.utility.ExcelDataValidator#validateContent(java.lang.StringBuffer)
	 */
	@Override
	public boolean validateContent(StringBuffer loggerMessage) {
		return false;
	}

}
