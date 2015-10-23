/**
 * 
 */
package org.srcm.pmp.utility;

import org.srcm.pmp.utility.v2.ExcelDataExtractorForValidationV2;
import org.srcm.pmp.utility.v2.ExcelDataExtractorV2;
import org.srcm.pmp.utility.v2.Version;
import org.srcm.pmp.utility.v2.VersionValidator;

/**
 * @author MASTER
 *
 */
public class ExcelDataFactory {
	private static ExcelDataFactory sInstance = new ExcelDataFactory();

	private ExcelDataFactory() {

	}

	public static ExcelDataFactory getInstance() {
		return sInstance;
	}

	/**
	 * @param file
	 * @return
	 */
	public ExcelDataProcessor getExcelDataExtractor(byte[] data, String fileName) {
		VersionValidator vValidator = new VersionValidator(data, fileName);
		ExcelDataProcessor transformer = new InValidExcelDataExtractor();
		if (vValidator.validateVersion() == Version.V1) {
			transformer = new ExcelDataExtractor(data, fileName);
		} else if (vValidator.validateVersion() == Version.V2) {
			transformer = new ExcelDataExtractorV2(data, fileName);
		}
		return transformer;

	}

	/**
	 * @param file
	 * @return
	 */
	public ExcelDataValidator getExcelDataValidator(byte[] data, String fileName) {
		VersionValidator vValidator = new VersionValidator(data, fileName);
		ExcelDataValidator transformerValid = new InValidExcelDataValidator();
		if (vValidator.validateVersion() == Version.V1) {
			transformerValid = new ExcelDataExtractorForValidation(data,
					fileName);
		} else if (vValidator.validateVersion() == Version.V2) {
			transformerValid = new ExcelDataExtractorForValidationV2(data,
					fileName);
		}
		return transformerValid;

	}

}
