/**
 * 
 */
package org.srcm.pmp.utility.v2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.srcm.pmp.utility.ExcelDataValidator;
import org.srcm.pmp.utility.FileType;

/**
 * @author MASTER
 *
 */
public class ExcelDataExtractorForValidationV2 implements ExcelDataValidator {
	private static final String DATE_CELL = "DD-MMM-YY";
	private static final String DD_MMM_YYYY = "dd-MMM-yy";
	private Workbook workbook;
	private Sheet programSheet;
	private Sheet participantSheet;
	private static Logger sLogger = LoggerFactory
			.getLogger(ExcelDataExtractorForValidationV2.class.getName());

	public ExcelDataExtractorForValidationV2(byte[] data, String fileName) {
		if (fileName.endsWith("xlsx"))
			buildWorkBook(data, FileType.XLSX);
		else if (fileName.endsWith("xls")) {
			buildWorkBook(data, FileType.XLS);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.srcm.pmp.utility.ExcelDataValidator#validateContent(java.lang.
	 * StringBuffer)
	 */
	@Override
	public boolean validateContent(StringBuffer loggerMessage) {
		List<String> programHeadervals = validateProgramHeaders();
		List<String> programVals = validateMandatoryProgramValues();
		List<String> partHeaders = validateParticipantHeaders();
		if (partHeaders.isEmpty()) {
			loggerMessage.append("Participant headers are Correct" + "\n");
		} else {
			loggerMessage
					.append("Participant headers are not Compliance with Template"
							+ "\n");
			loggerMessage.append(partHeaders.toString());
		}
		sLogger.info(loggerMessage.toString());
		if (programHeadervals.isEmpty()) {
			String message = "Program Headers are correct";
			loggerMessage.append(message + "\n");
			sLogger.info(message);
		} else {
			String message = "Non compliance with headers,please use the template"
					+ programHeadervals;
			loggerMessage.append(message + "\n");
			sLogger.info(message);
			loggerMessage.append(programHeadervals.toString() + "\n");
			loggerMessage.append(ProgramCols.values().toString() + "\n");
		}
		if (programVals.isEmpty()) {
			String message = "Header Values are correct";
			loggerMessage.append(message + "\n");
			sLogger.info(message);
		} else {
			String message = programVals.toString();
			loggerMessage.append(message + "\n");
			sLogger.info(message);
		}
		return programHeadervals.isEmpty() && programVals.isEmpty()
				&& partHeaders.isEmpty();

	}

	/**
	 * @param data
	 * @param fileType
	 */
	private void buildWorkBook(byte[] data, FileType fileType) {
		ByteArrayInputStream bs = new ByteArrayInputStream(data);
		try {
			if (fileType == FileType.XLSX)
				workbook = new XSSFWorkbook(bs);
			else if (fileType == FileType.XLS) {
				workbook = new HSSFWorkbook(bs);
			}
			programSheet = workbook.getSheet("Event Details");
			participantSheet = workbook.getSheet("Participants Details");
		} catch (IOException e) {
			sLogger.error("Issues with files", e);
		}
	}

	/**
	 * @return
	 */
	public List<String> validateParticipantHeaders() {
		List<String> errorHeaders = new ArrayList<String>();
		Row rowHeader = participantSheet.getRow(0);
		for (ParticipantCols partHeader : ParticipantCols.values()) {
			Cell cell = rowHeader.getCell(partHeader.getColumn());
			String string = cell.toString();
			if (string == null
					|| !string.trim().equalsIgnoreCase(
							partHeader.getHeader().toLowerCase())) {
				errorHeaders.add(partHeader.getHeader());
			}
		}

		return errorHeaders;

	}

	/**
	 * @return
	 */
	public List<String> validateProgramHeaders() {
		List<String> errorHeaders = new ArrayList<String>();
		for (ProgramCols programHeader : ProgramCols.values()) {
			Row dataRow = programSheet.getRow(programHeader.getRow());
			Cell cell = dataRow.getCell(programHeader.getCell());
			String string = cell.toString();
			if (string == null
					|| !string.trim().equalsIgnoreCase(
							programHeader.getHeader().toLowerCase())) {
				errorHeaders.add(programHeader.getHeader());
			}
		}
		return errorHeaders;
	}

	/**
	 * @return
	 */
	public List<String> validateMandatoryProgramValues() {
		List<String> errorHeaders = new ArrayList<String>();
		for (ProgramCols programHeader : ProgramCols.values()) {
			Row dataRow = programSheet.getRow(programHeader.getRow());
			Cell cell = dataRow.getCell(programHeader.getCell() + 1);
			String string = cell.toString();
			if (string == null && programHeader.getHeader().contains("*")) {
				errorHeaders.add(programHeader.getHeader()
						+ " Is Mandatory Field");
			}

			if (string != null && programHeader.getHeader().contains(DATE_CELL)) {
				validateStartDate(dataRow);
			}
		}
		return errorHeaders;
	}

	/**
	 * @param header
	 * @param codateRow
	 */
	private boolean validateStartDate(Row codateRow) {
		String string = codateRow.getCell(ProgramCols.EVENT_DATE.getCell() + 1)
				.toString();
		SimpleDateFormat format = new SimpleDateFormat(DD_MMM_YYYY);
		try {
			format.parse(string);
			return true;
		} catch (ParseException e) {
			return false;
		}

	}

}
