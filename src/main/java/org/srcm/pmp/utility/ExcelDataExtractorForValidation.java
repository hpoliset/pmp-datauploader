/**
 * 
 */
package org.srcm.pmp.utility;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.srcm.pmp.to.ProgramHeaderTO;
import org.srcm.pmp.to.ProgramHeaderValidator;

/**
 * @author MASTER
 *
 */
public class ExcelDataExtractorForValidation {

	public enum Rows {
		PROGRAM_NAME(3), CO_NAME(4), CO_EMAIL(5), CO_CENTER(6), CO_COUNTRY(7), CO_INSTNAME(
				8), CO_WEB_SITE(9), CO_DATE(10);
		int cellVal;

		private Rows(int i) {
			this.cellVal = i;
		}

		/**
		 * @return
		 */
		public int getRowVal() {
			return cellVal;
		}
	}

	public enum FILE_TYPE {
		XLS, XLSX;
	}

	private static Logger sLogger = LoggerFactory
			.getLogger(ExcelDataExtractorForValidation.class.getName());
	private CommonsMultipartFile aFile;
	Workbook workbook;
	Sheet sheet;

	public ExcelDataExtractorForValidation(byte[] data, FILE_TYPE fileType) {
		buildWorkBook(data, fileType);

	}

	private void buildWorkBook(byte[] data, FILE_TYPE fileType) {
		ByteArrayInputStream bs = new ByteArrayInputStream(data);
		try {
			if (fileType == FILE_TYPE.XLSX)
				workbook = new XSSFWorkbook(bs);
			else if (fileType == FILE_TYPE.XLS) {
				workbook = new HSSFWorkbook(bs);
			}
			sheet = workbook.getSheetAt(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ExcelDataExtractorForValidation(CommonsMultipartFile uploadFile) {
		this.aFile = uploadFile;
	}

	public List<Object> buildHeader() {
		List<Object> data = new ArrayList<Object>();
		ProgramHeaderTO header = new ProgramHeaderTO();
		ProgramHeaderValidator validator = new ProgramHeaderValidator();
		Row programRow = sheet.getRow(Rows.PROGRAM_NAME.getRowVal());
		Row nameRow = sheet.getRow(Rows.CO_NAME.getRowVal());
		Row centerRow = sheet.getRow(Rows.CO_CENTER.getRowVal());
		Row countryRow = sheet.getRow(Rows.CO_COUNTRY.getRowVal());
		Row emailRow = sheet.getRow(Rows.CO_EMAIL.getRowVal());
		Row instRow = sheet.getRow(Rows.CO_INSTNAME.getRowVal());
		Row websiteRow = sheet.getRow(Rows.CO_WEB_SITE.getRowVal());
		Row codateRow = sheet.getRow(Rows.CO_DATE.getRowVal());

		header.setChannelName(validateNull(programRow.getCell(2)));
		header.setCenter(validateNull(centerRow.getCell(2)));
		header.setCoordinatorName(validateNull(nameRow.getCell(2)));
		header.setCountry(validateNull(countryRow.getCell(2)));
		header.setEmail(validateNull(emailRow.getCell(2)));
		header.setInstituteName(validateNull(instRow.getCell(2)));
		header.setWebsite(validateNull(websiteRow.getCell(2)));
		header.setProgramStartDate(validateDateNull(codateRow.getCell(2)));

		validator.setProgramName(validateNull(programRow.getCell(0)));
		validator.setCenter(validateNull(centerRow.getCell(0)));
		validator.setCoordinatorName(validateNull(nameRow.getCell(0)));
		validator.setCountry(validateNull(countryRow.getCell(0)));
		validator.setEmail(validateNull(emailRow.getCell(0)));
		validator.setNameOfInstitute(validateNull(instRow.getCell(0)));
		validator.setWebsite(validateNull(websiteRow.getCell(0)));
		validator.setDatesOfProgram(validateDateNull(codateRow.getCell(0)));

		sLogger.info(header.toString());
		data.add(header);
		data.add(validator);
		// header.set

		return data;
	}

	private String validateNull(Cell cell) {
		if (cell != null) {
			return cell.getStringCellValue();
		}
		return "";
	}

	/**
	 * @param cell
	 * @return
	 */
	private String validateDateNull(Cell cell) {
		if (cell != null) {
			return cell.toString();
		}
		return "";
	}

	public static void main(String[] args) {
		if(args[0]==null){
			sLogger.info("Please provide Input file name.");
		}
		FileInputStream fs;
		try {
			String fileName =args[0];
			if (fileName == null) {
				sLogger.info("Please provide valid file name.");
				return;
			}
			fs = new FileInputStream(fileName);
			byte[] data = new byte[10000000];
			fs.read(data);
			fs.close();
			ExcelDataExtractorForValidation transformer = null;
			if (fileName.endsWith("xls")) {
				transformer = new ExcelDataExtractorForValidation(data,
						FILE_TYPE.XLS);
			} else if (fileName.endsWith("xlsx")) {
				transformer = new ExcelDataExtractorForValidation(data,
						FILE_TYPE.XLSX);
			} else {
				sLogger.info("Invalid File (xls or xlsx)");
				System.exit(0);
			}
			List<Object> buildHeader = transformer.buildHeader();
			ProgramHeaderTO header = (ProgramHeaderTO) buildHeader.get(0);
			ProgramHeaderValidator validator = (ProgramHeaderValidator) buildHeader
					.get(1);
			List<String> validateHeaders = validator.validateHeaders();
			List<String> validateValues = validator.validateValues(header);
			if (validateHeaders.isEmpty()) {
				sLogger.info("Headers are correct");
			} else {
				sLogger.info("Non compliance with headers,please use the template"+validateHeaders);
			}
			if (validateValues.isEmpty()) {
				sLogger.info("Header Values are correct");
			} else {
				sLogger.info(validateValues.toString());
			}
		} catch (IOException e) {
			sLogger.error(e.getMessage());
		}

	}
}
