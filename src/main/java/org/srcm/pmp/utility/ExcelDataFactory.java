/**
 * 
 */
package org.srcm.pmp.utility;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.srcm.pmp.utility.v2.ExcelDataExtractorForValidationV2;
import org.srcm.pmp.utility.v2.ExcelDataExtractroV2;
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
	public ExcelDataProcessor getExcelDataExtractor(CommonsMultipartFile file) {
		VersionValidator vValidator = new VersionValidator(file.getBytes(),
				file.getFileItem().getName());
		ExcelDataProcessor transformer = new InValidExcelDataExtractor();
		if (vValidator.validateVersion() == Version.V1) {
			transformer = new ExcelDataExtractor(file.getBytes(), file
					.getFileItem().getName());
		} else if (vValidator.validateVersion() == Version.V2) {
			transformer = new ExcelDataExtractroV2(file.getBytes(), file
					.getFileItem().getName());
		}
		return transformer;

	}

	/**
	 * @param file
	 * @return
	 */
	public ExcelDataValidator getExcelDataValidator(CommonsMultipartFile file) {
		VersionValidator vValidator = new VersionValidator(file.getBytes(),
				file.getFileItem().getName());
		ExcelDataValidator transformerValid = new InValidExcelDataValidator();
		if (vValidator.validateVersion() == Version.V1) {
			transformerValid = new ExcelDataExtractorForValidation(
					file.getBytes(), file.getFileItem().getName());
		} else if (vValidator.validateVersion() == Version.V2) {
			transformerValid = new ExcelDataExtractorForValidationV2(
					file.getBytes(), file.getFileItem().getName());
		}
		return transformerValid;

	}

}
