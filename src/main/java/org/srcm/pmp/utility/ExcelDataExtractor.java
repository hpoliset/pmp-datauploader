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
import org.srcm.pmp.to.SeekerAimsTO;

/**
 * @author MASTER
 *
 */
public class ExcelDataExtractor {

	private static final String YES = "yes";
	private static final String DD_MMM_YYYY = "dd-MMM-yyyy";
	private static final String FULL_NAME = "Full Name";
	private SimpleDateFormat dateFormat = new SimpleDateFormat(DD_MMM_YYYY);

	public static enum PARTICIPANT_COLS {
		NAME(1), CITY(2), STATE(3), EMAIL(4), PHONE(5),OCCUPATION(6), INTRODUCED(7), INTR_DATE(
				8), INTR_BY(9),REMARKS(10);
		private int col;

		private PARTICIPANT_COLS(int col) {
			this.col = col;
		}

		/**
		 * @return
		 */
		public int getCol() {
			return col;
		}
	}

	public static enum HEADER_ROWS {
		PROGRAM_NAME(2), CO_NAME(3), CO_EMAIL(4), CO_CENTER(5), CO_COUNTRY(6), CO_INSTNAME(7), CO_WEB_SITE(8), CO_DATE(
				9);
		int cellVal;

		private HEADER_ROWS(int i) {
			this.cellVal = i;
		}

		/**
		 * @return
		 */
		public int getRowVal() {
			return cellVal;
		}
	}

	public static enum FILE_TYPE {
		XLS, XLSX;
	}

	public static enum SEEKER_ROWS {

	}

	private static Logger sLogger = LoggerFactory.getLogger(ExcelDataExtractor.class.getName());
	Workbook workbook;
	Sheet sheet;

	/**
	 * @param data
	 * @param fileType
	 */
	public ExcelDataExtractor(byte[] data, FILE_TYPE fileType) {
		buildWorkBook(data, fileType);

	}

	/**
	 * @param data
	 * @param fileType
	 */
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

	/**
	 * @return
	 */
	public ProgramHeaderTO buildProgramDetails() {
		ProgramHeaderTO header = new ProgramHeaderTO();
		Row programRow = sheet.getRow(HEADER_ROWS.PROGRAM_NAME.getRowVal());
		Row nameRow = sheet.getRow(HEADER_ROWS.CO_NAME.getRowVal());
		Row mailRow = sheet.getRow(HEADER_ROWS.CO_EMAIL.getRowVal());
		Row centerRow = sheet.getRow(HEADER_ROWS.CO_CENTER.getRowVal());
		Row instRow = sheet.getRow(HEADER_ROWS.CO_INSTNAME.getRowVal());
		Row codateRow = sheet.getRow(HEADER_ROWS.CO_DATE.getRowVal());
		Row website = sheet.getRow(HEADER_ROWS.CO_WEB_SITE.getRowVal());

		header.setEmail(validateNull(mailRow.getCell(2)));
		header.setChannelName(programRow.getCell(2).getStringCellValue());
		header.setCenter(centerRow.getCell(2).getStringCellValue());
		populateCoordinatorNameAndEmail(header, nameRow);
		populateCenterAndCountry(header, centerRow);
		header.setInstituteName(instRow.getCell(2).toString());
		header.setWebsite(validateNull(website.getCell(2)));
		populateStartDate(header, codateRow);

		return header;
	}

	/**
	 * 
	 */
	public List<SeekerAimsTO> buildParticipants() {
		boolean mark = false;
		List<Row> participantRows = new ArrayList<Row>();
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (mark) {
				if (row != null) {
					participantRows.add(row);
				}
			}
			if (row != null && row.getCell(PARTICIPANT_COLS.NAME.getCol()) != null) {
				String string = row.getCell(PARTICIPANT_COLS.NAME.getCol()).toString();
				if (string.contains(FULL_NAME)) {
					mark = true;
				}

				if (mark) {
					if (row == null || (row.getCell(PARTICIPANT_COLS.NAME.getCol()) == null)
							|| row.getCell(PARTICIPANT_COLS.NAME.getCol()).toString().isEmpty()) {
						mark = false;
					}
				}
			}

		}
		List<SeekerAimsTO> processRows = processRows(participantRows);
		sLogger.info("Participant list:" + participantRows.size());
		return processRows;
	}

	/**
	 * @param participantRows
	 */
	private List<SeekerAimsTO> processRows(List<Row> participantRows) {
		List<SeekerAimsTO> aimsList = new ArrayList<SeekerAimsTO>();
		for (Row row : participantRows) {
			SeekerAimsTO buildParticipant = buildParticipant(row);
			aimsList.add(buildParticipant);
		}
		return aimsList;
	}

	/**
	 * @param row
	 */
	private SeekerAimsTO buildParticipant(Row row) {
		SeekerAimsTO seekerTo = new SeekerAimsTO();
		Cell cellName = row.getCell(PARTICIPANT_COLS.NAME.getCol());
		seekerTo.setFirstName(validateNull(cellName));
		Cell cellCity = row.getCell(PARTICIPANT_COLS.CITY.getCol());
		seekerTo.setCity(validateNull(cellCity));
		Cell cellState = row.getCell(PARTICIPANT_COLS.STATE.getCol());
		seekerTo.setState(validateNull(cellState));
		Cell cellEmail = row.getCell(PARTICIPANT_COLS.EMAIL.getCol());
		seekerTo.setEmail(validateNull(cellEmail));
		Cell cellPhone = row.getCell(PARTICIPANT_COLS.PHONE.getCol());
		populatePhone(seekerTo, cellPhone);
		Cell cellIntroduced = row.getCell(PARTICIPANT_COLS.INTRODUCED.getCol());
		if (cellIntroduced != null) {
			if (cellIntroduced.toString().toLowerCase().contains(YES)) {
				seekerTo.setIntroduced(Boolean.TRUE);
			}
		}
		setDate(row, seekerTo);
		Cell cellIntrBy = row.getCell(PARTICIPANT_COLS.INTR_BY.getCol());
		seekerTo.setIntroducedBy(validateNull(cellIntrBy));
		Cell cellOcc = row.getCell(PARTICIPANT_COLS.OCCUPATION.getCol());
		seekerTo.setOccupation(validateNull(cellOcc));
		Cell cellRemarks = row.getCell(PARTICIPANT_COLS.REMARKS.getCol());
		seekerTo.setRemarks(validateNull(cellRemarks));
		return seekerTo;
	}

	private void populatePhone(SeekerAimsTO seekerTo, Cell cellPhone) {
		if (cellPhone != null) {
			try {
				Double parseDouble = Double.parseDouble(cellPhone.toString());
				seekerTo.setPhoneMobile(String.valueOf(parseDouble.longValue()));
			} catch (Exception e) {
				seekerTo.setPhoneMobile(cellPhone.toString());
			}

		}
	}

	/**
	 * @param row
	 */
	private void setDate(Row row, SeekerAimsTO seekerTo) {
		Cell cellIntrDate = row.getCell(PARTICIPANT_COLS.INTR_DATE.getCol());
		if (cellIntrDate != null) {
			String string = cellIntrDate.toString();
			try {
				Date date = dateFormat.parse(string);
				seekerTo.setIntroducedDate(date);
			} catch (ParseException e) {
				seekerTo.setIntroducedRawDate(string);
			}
		}
	}

	/**
	 * @param header
	 * @param codateRow
	 */
	private void populateStartDate(ProgramHeaderTO header, Row codateRow) {
		if (codateRow.getCell(2) != null) {
			String string = codateRow.getCell(2).toString();
			SimpleDateFormat format = new SimpleDateFormat(DD_MMM_YYYY);
			try {
				Date date = format.parse(string);
				header.setProgramStartDate(date);
			} catch (ParseException e) {
				header.setProgramRawStartDate(string);
			}
		}

	}

	/**
	 * @param header
	 * @param centerRow
	 */
	private void populateCenterAndCountry(ProgramHeaderTO header, Row centerRow) {
		String center = centerRow.getCell(2).getStringCellValue();
		String[] centerData = center.split(",");
		if (centerData != null && centerData.length > 1) {
			header.setCenter(centerData[0]);
			header.setCountry(centerData[1]);
		} else {
			header.setCenter(center);
		}

	}

	/**
	 * @param header
	 * @param nameRow
	 */
	private void populateCoordinatorNameAndEmail(ProgramHeaderTO header, Row nameRow) {
		String coName = nameRow.getCell(2).getStringCellValue();
		if (coName.indexOf("@") > 0) {
			String[] splitData = coName.split(",");
			if (splitData.length > 2) {
				fillNameAndEmail(header, coName, splitData);
			} else {
				splitData = coName.split(" ");
				if (splitData.length > 1) {
					fillNameAndEmail(header, coName, splitData);
				}
			}

		}
	}

	private void fillNameAndEmail(ProgramHeaderTO header, String coName, String[] splitData) {
		String email = splitData[splitData.length - 1];
		header.setEmail(email);
		String coordName = coName.substring(0, coName.indexOf(email));
		header.setCoordinatorName(coordName);
	}

	private String validateNull(Cell cell) {
		if (cell != null) {
			return cell.toString();
		}
		return "";
	}

	public static void main(String[] args) {
		FileInputStream fs;
		try {
			String fileName = "E:\\validatedExcels\\HFN-I-Renigunta-Tirupati-AP-MRKrishna.xlsx";
			fs = new FileInputStream(fileName);
			byte[] data = new byte[10000000];
			fs.read(data);
			fs.close();
			ExcelDataExtractor transformer = null;
			if (fileName.endsWith("xls")) {
				transformer = new ExcelDataExtractor(data, FILE_TYPE.XLS);
			} else if (fileName.endsWith("xlsx")) {
				transformer = new ExcelDataExtractor(data, FILE_TYPE.XLSX);
			} else {
				System.out.println("Invalid File (xls or xlsx)");
				System.exit(0);
			}
			ProgramHeaderTO buildHeader = transformer.buildProgramDetails();
			List<SeekerAimsTO> buildParticipants = transformer.buildParticipants();
			System.out.println(buildHeader);
			System.out.println(buildParticipants);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
