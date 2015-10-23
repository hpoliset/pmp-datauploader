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
public class ExcelDataExtractor implements ExcelDataProcessor {

	private static final String YES = "yes";
	private static final String DD_MMM_YYYY = "dd-MMM-yyyy";
	private static final String FULL_NAME = "Full Name";
	private static final String PROGRAM_NAME = "Program Name";
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
	public ExcelDataExtractor(byte[] data, String fileName) {
		if (fileName.endsWith("xlsx"))
			buildWorkBook(data, FileType.XLSX);
		else if (fileName.endsWith("xls")) {
			buildWorkBook(data, FileType.XLS);
		}

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
		Row emailRow = sheet.getRow(rowVal+2);
		Row centerRow = sheet.getRow(rowVal+3);
		Row stateRow = sheet.getRow(rowVal+4);
		Row countryRow = sheet.getRow(rowVal+5);
		
		Row instRow = sheet.getRow(rowVal+6);
		Row websiteRow = sheet.getRow(rowVal+7);
		Row codateRow = sheet.getRow(rowVal+8);

		header.setEmail(validateNull(emailRow.getCell(2)));
		header.setChannelName(programRow.getCell(2).getStringCellValue());
		header.setCenter(centerRow.getCell(2).getStringCellValue());
		header.setCoordinatorName(validateNull(nameRow.getCell(2)));
		header.setInstituteName(instRow.getCell(2).toString());
		header.setWebsite(validateNull(websiteRow.getCell(2)));
		header.setCountry(validateNull(countryRow.getCell(2)));
		header.setState(validateNull(stateRow.getCell(2)));
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
		ProgramHeaderTO buildProgramDetails = buildProgramDetails();
		List<SeekerAimsTO> processRows = processRows(participantRows);
		for(SeekerAimsTO seekerAims:processRows){
			seekerAims.setCountry(buildProgramDetails.getCountry());
		}
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
			if(buildParticipant.getFirstName()!=null && !buildParticipant.getFirstName().isEmpty()){
			aimsList.add(buildParticipant);
			}
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
			ExcelDataExtractor transformer = new ExcelDataExtractor(data, fileName);
			ProgramHeaderTO buildHeader = transformer.buildProgramDetails();
			List<SeekerAimsTO> buildParticipants = transformer.buildParticipants();
			
			System.out.println(buildHeader);
			System.out.println(buildParticipants);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
}
