/**
 * 
 */
package org.srcm.pmp.utility;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.srcm.pmp.to.ProgramHeaderTO;
import org.srcm.pmp.to.ProgramHeaderValidator;

/**
 * @author MASTER
 *
 */
public class ExcelDataExtractorForValidation {

	private static final String PROGRAM_NAME = "Program Name";

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


	public List<Object> buildHeader() {
		List<Object> data = new ArrayList<Object>();
		ProgramHeaderTO header = new ProgramHeaderTO();
		ProgramHeaderValidator validator = new ProgramHeaderValidator();
		int rowVal = 0;
		for(int cnt=0;cnt < sheet.getPhysicalNumberOfRows();cnt++){
			Row row = sheet.getRow(cnt);
			if(row.getCell(0)!=null){
				String val = row.getCell(0).toString();
				if(val.contains(PROGRAM_NAME)){
					rowVal = cnt;
					break;
				}
			}
		}
		
		
		Row programRow = sheet.getRow(rowVal);
		Row nameRow = sheet.getRow(rowVal+1);
		Row centerRow = sheet.getRow(rowVal+3);
		Row stateRow = sheet.getRow(rowVal+4);
		Row countryRow = sheet.getRow(rowVal+5);
		Row emailRow = sheet.getRow(rowVal+2);
		Row instRow = sheet.getRow(rowVal+6);
		Row websiteRow = sheet.getRow(rowVal+7);
		Row codateRow = sheet.getRow(rowVal+8);

		header.setChannelName(validateNull(programRow.getCell(2)));
		header.setState(validateNull(stateRow.getCell(2)));
		header.setCenter(validateNull(centerRow.getCell(2)));
		header.setCoordinatorName(validateNull(nameRow.getCell(2)));
		header.setCountry(validateNull(countryRow.getCell(2)));
		header.setEmail(validateNull(emailRow.getCell(2)));
		header.setInstituteName(validateNull(instRow.getCell(2)));
		header.setWebsite(validateNull(websiteRow.getCell(2)));
		String startDate = validateDateNull(codateRow.getCell(2));
		if(startDate!=null){
		 header.setProgramStartDate(getDate(startDate));
		}

		validator.setProgramName(validateNull(programRow.getCell(0)));
		validator.setCenter(validateNull(centerRow.getCell(0)));
		validator.setState(validateNull(stateRow.getCell(0)));
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

	/**
	 * @param startDate
	 * @return
	 */
	private Date getDate(String startDate) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			return format.parse(startDate);
		} catch (ParseException e) {
			return null;
		}
		
	}

	private String validateNull(Cell cell) {
		if (cell != null) {
			return cell.toString();
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
		return null;
	}

	public static void main(String[] args) {
		if(args[0]==null){
			sLogger.error("Please provide Input file name.");
		}
		StringBuffer loggerMessage = new StringBuffer();
		boolean isValid = validateHeartfulnessExcelFile(args[0], loggerMessage);
		if(!isValid) {
			System.exit(-1);
		}
		System.exit(0);
	}

	public static boolean validateHeartfulnessExcelFile(String arg, StringBuffer loggerMessage) {

		FileInputStream fs;
		try {
			String fileName = arg;
			if (fileName == null) {
				String message = "Please provide valid file name.";
				loggerMessage.append(message + "\n");
				sLogger.info(message);
				return false;
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
				return false;
			}
			List<Object> buildHeader = transformer.buildHeader();
			ProgramHeaderTO header = (ProgramHeaderTO) buildHeader.get(0);
			ProgramHeaderValidator validator = (ProgramHeaderValidator) buildHeader
					.get(1);
			List<String> validateHeaders = validator.validateHeaders();
			List<String> validateValues = validator.validateValues(header);
			if (validateHeaders.isEmpty()) {
				String message = "Headers are correct";
				loggerMessage.append(message + "\n");
				sLogger.info(message);
			} else {
				String message = "Non compliance with headers,please use the template"+validateHeaders;
				loggerMessage.append(message + "\n");
				sLogger.info(message);
				loggerMessage.append(validateValues.toString() + "\n");
				loggerMessage.append(header.toString() + "\n");
				return false;
			}
			if (validateValues.isEmpty()) {
				String message = "Header Values are correct";
				loggerMessage.append(message + "\n");
				sLogger.info(message);
			} else {
				String message = validateValues.toString();
				loggerMessage.append(message + "\n");
				sLogger.info(message);
			}
			loggerMessage.append(header.toString());
		} catch (IOException e) {
			sLogger.error(e.getMessage());
		}
		return true;
	}
}
