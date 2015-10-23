/**
 * 
 */
package org.srcm.pmp.utility.v2;

import java.io.ByteArrayInputStream;
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
import org.srcm.pmp.utility.ExcelDataExtractor;
import org.srcm.pmp.utility.ExcelDataProcessor;
import org.srcm.pmp.utility.FileType;

/**
 * @author MASTER
 *
 */
public class ExcelDataExtractroV2 implements ExcelDataProcessor {
	private static final String DD_MMM_YYYY = "dd-MMM-yyyy";
	private SimpleDateFormat dateFormat = new SimpleDateFormat(DD_MMM_YYYY);
	private static Logger sLogger = LoggerFactory.getLogger(ExcelDataExtractor.class.getName());
	private Workbook workbook;
	private Sheet sheetEvent;
	private Sheet sheetParticipants;

	
	public ExcelDataExtractroV2(byte[]data,FileType fileType){
		buildWorkBook(data,fileType);
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
			sheetEvent = workbook.getSheet("Event Details");
			sheetParticipants = workbook.getSheet("Participants Details");
		} catch (IOException e) {
			sLogger.error("Issues with files",e);
		}
	}

	/* (non-Javadoc)
	 * @see org.srcm.pmp.utility.ExcelDataProcessor#buildProgramDetails()
	 */
	@Override
	public ProgramHeaderTO buildProgramDetails() {
		ProgramHeaderTO header = new ProgramHeaderTO();
		
		Row programEventRow = sheetEvent.getRow(ProgramCols.EVENT_TYPE.getRow());
		Row programOther = sheetEvent.getRow(ProgramCols.OTHER.getRow());
		Row programEventPlace = sheetEvent.getRow(ProgramCols.EVENT_PLACE.getRow());
		Row programEventDate = sheetEvent.getRow(ProgramCols.EVENT_DATE.getRow());
		Row programCountry = sheetEvent.getRow(ProgramCols.EVENT_COUNTRY.getRow());
		Row programState = sheetEvent.getRow(ProgramCols.EVENT_STATE.getRow());
		Row programCity = sheetEvent.getRow(ProgramCols.EVENT_CITY.getRow());
		Row programCoordinator = sheetEvent.getRow(ProgramCols.EVENT_COORDINATORNAME.getRow());
		Row programCoordinatorMobile = sheetEvent.getRow(ProgramCols.EVENT_COORDINATOR_MOBILE.getRow());
		Row programCoordinatorMail = sheetEvent.getRow(ProgramCols.EVENT_COORDINATOR_MAIL.getRow());
		Row organizationName = sheetEvent.getRow(ProgramCols.ORGANIZATION_NAME.getRow());
		Row orgContactPerson = sheetEvent.getRow(ProgramCols.ORGANIZATION_CONTACT_PERSON.getRow());
		Row orgWebSite = sheetEvent.getRow(ProgramCols.ORGANIZATION_WEBSITE.getRow());
		Row orgContactMailId = sheetEvent.getRow(ProgramCols.ORGANIZATION_CONTACT_MAILID.getRow());
		Row orgContactMobile = sheetEvent.getRow(ProgramCols.ORGANIZATION_CONTACT_MOBILE.getRow());
		
		Row preceptorName = sheetEvent.getRow(ProgramCols.PRECEPTOR_NAME.getRow());
		Row preceptorId = sheetEvent.getRow(ProgramCols.PRECEPTOR_ID.getRow());
		Row welcomeCardSignedBy = sheetEvent.getRow(ProgramCols.WELCOME_CARD_SIGNEDBY.getRow());
		Row welcomeCardSignedID = sheetEvent.getRow(ProgramCols.WELCOME_CARD_SIGNER_ID.getRow());
		
		Row remarks = sheetEvent.getRow(ProgramCols.REMARKS.getRow()+1);
		
		
		header.setEmail(validateNull(programCoordinatorMail.getCell(ProgramCols.EVENT_COORDINATOR_MAIL.getCell()+1)));
		header.setChannelName(programEventRow.getCell(
				ProgramCols.EVENT_TYPE.getCell() + 1).getStringCellValue());
		header.setCenter(programEventPlace.getCell(ProgramCols.EVENT_PLACE.getCell()+1).getStringCellValue());
		header.setCoordinatorName(validateNull(programCoordinator.getCell(ProgramCols.EVENT_COORDINATORNAME.getCell()+1)));
		header.setInstituteName(organizationName.getCell(ProgramCols.ORGANIZATION_NAME.getCell()+1).toString());
		header.setWebsite(validateNull(orgWebSite.getCell(ProgramCols.ORGANIZATION_WEBSITE.getCell()+1)));
		header.setCountry(validateNull(programCountry.getCell(ProgramCols.EVENT_COUNTRY.getCell()+1)));
		header.setState(validateNull(programState.getCell(ProgramCols.EVENT_STATE.getCell()+1)));
		populateStartDate(header, programEventDate);
		
		header.setOther(validateNull(programOther.getCell(ProgramCols.OTHER.getCell()+1)));
		header.setEventCity(validateNull(programCity.getCell(ProgramCols.EVENT_CITY.getCell()+1)));
		populateCoordnatorMobile(header,programCoordinatorMobile.getCell(ProgramCols.EVENT_COORDINATOR_MOBILE.getCell()+1));
		populateOrgContactMobile(header,orgContactMobile.getCell(ProgramCols.ORGANIZATION_CONTACT_MOBILE.getCell()+1));
		header.setOrgContactPerson(validateNull(orgContactPerson.getCell(ProgramCols.ORGANIZATION_CONTACT_PERSON.getCell()+1)));
		
		header.setOrgContactEmail(validateNull(orgContactMailId.getCell(ProgramCols.ORGANIZATION_CONTACT_MAILID.getCell()+1)));
		header.setPreceptorName(validateNull(preceptorName.getCell(ProgramCols.PRECEPTOR_NAME.getCell()+1)));
		header.setPreceptorId(validateNull(preceptorId.getCell(ProgramCols.PRECEPTOR_ID.getCell()+1)));
		header.setWelcomeCardSignedBy(validateNull(welcomeCardSignedBy.getCell(ProgramCols.WELCOME_CARD_SIGNEDBY.getCell()+1)));
		header.setWelcomeCardSignedID(validateNull(welcomeCardSignedID.getCell(ProgramCols.WELCOME_CARD_SIGNER_ID.getCell()+1)));
		
		header.setRemarks(validateNull(remarks.getCell(ProgramCols.REMARKS.getCell())));
		return header;

	}
	/**
	 * @param header
	 * @param codateRow
	 */
	private void populateStartDate(ProgramHeaderTO header, Row codateRow) {
		if (codateRow.getCell(ProgramCols.EVENT_DATE.getCell()+1) != null) {
			String string = codateRow.getCell(ProgramCols.EVENT_DATE.getCell()+1).toString();
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
	 * @param cellPhone
	 */
	private void populateCoordnatorMobile(ProgramHeaderTO header, Cell cellPhone) {
		if (cellPhone != null) {
			try {
				Double parseDouble = Double.parseDouble(cellPhone.toString());
				header.setCoordinatorMobile(String.valueOf(parseDouble.longValue()));
			} catch (Exception e) {
				header.setCoordinatorMobile(cellPhone.toString());
			}

		}
	}
	
	
	/**
	 * @param header
	 * @param cellPhone
	 */
	private void populateOrgContactMobile(ProgramHeaderTO header, Cell cellPhone) {
		if (cellPhone != null) {
			try {
				Double parseDouble = Double.parseDouble(cellPhone.toString());
				header.setOrgContactMobile(String.valueOf(parseDouble.longValue()));
			} catch (Exception e) {
				header.setOrgContactMobile(cellPhone.toString());
			}

		}
	}

	
	private String validateNull(Cell cell) {
		if (cell != null) {
			return cell.toString();
		}
		return "";
	}

	
	@Override
	public List<SeekerAimsTO> buildParticipants() {
		boolean mark = false;
		List<Row> participantRows = new ArrayList<Row>();
		for (int i = 0; i < sheetParticipants.getLastRowNum(); i++) {
			Row row = sheetParticipants.getRow(i);
			if (mark) {
				if (row != null) {
					participantRows.add(row);
				}
			}
			if (row != null && row.getCell(ParticipantCols.NAME.getColumn()) != null) {
				String string = row.getCell(ParticipantCols.NAME.getColumn()).toString();
				if (string.contains(ParticipantCols.NAME.getHeader())) {
					mark = true;
				}

				if (mark) {
					if (row == null || (row.getCell(ParticipantCols.NAME.getColumn()) == null)
							|| row.getCell(ParticipantCols.NAME.getColumn()).toString().isEmpty()) {
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
		Cell cellName = row.getCell(ParticipantCols.NAME.getColumn());
		seekerTo.setFirstName(validateNull(cellName));
		Cell cellCity = row.getCell(ParticipantCols.CITY.getColumn());
		seekerTo.setCity(validateNull(cellCity));
		Cell cellState = row.getCell(ParticipantCols.STATE.getColumn());
		seekerTo.setState(validateNull(cellState));
		Cell cellEmail = row.getCell(ParticipantCols.EMAIL.getColumn());
		seekerTo.setEmail(validateNull(cellEmail));
		Cell cellPhone = row.getCell(ParticipantCols.MOBILE.getColumn());
		populatePhone(seekerTo, cellPhone);
		Cell cellIntroduced = row.getCell(ParticipantCols.THIRD_SITTING.getColumn());
		if (cellIntroduced.getStringCellValue() != null
				&& !cellIntroduced.getStringCellValue().isEmpty()
				&& cellIntroduced.getStringCellValue().contains("Y")) {
			seekerTo.setIntroduced(Boolean.TRUE);
		}
		Cell cellAge = row.getCell(ParticipantCols.AGE_GROUP.getColumn());
		seekerTo.setAgeGroup(validateNull(cellAge));
		
		Cell cellBatchYear = row.getCell(ParticipantCols.BATCH_YEAR.getColumn());
		seekerTo.setBatchOrYear(validateNull(cellBatchYear));
		
		
		Cell cellGender = row.getCell(ParticipantCols.GENDER.getColumn());
		seekerTo.setGender(validateNull(cellGender));
		
		Cell cellDepartment = row.getCell(ParticipantCols.DEPARTMENT.getColumn());
		seekerTo.setDepartment(validateNull(cellDepartment));
		
		Cell cellPrefLang = row.getCell(ParticipantCols.PREF_LANGUAGE.getColumn());
		seekerTo.setPreferredLanguageForCommunication(validateNull(cellPrefLang));
		
		Cell cellReceiveUpdates = row.getCell(ParticipantCols.RECEIVE_UPDATES.getColumn());
		String recUpdates = validateNull(cellReceiveUpdates);
		if("Y".equalsIgnoreCase(recUpdates.trim().toUpperCase())){
		seekerTo.setReceiveUpddates(Boolean.TRUE);
		}
		setWelcomeCardIssuedDate(row, seekerTo);
		Cell cellOcc = row.getCell(ParticipantCols.PROFFESION.getColumn());
		seekerTo.setOccupation(validateNull(cellOcc));
		Cell cellRemarks = row.getCell(ParticipantCols.REMARKS.getColumn());
		seekerTo.setRemarks(validateNull(cellRemarks));
		
		Cell cellWelcomeCardNo = row.getCell(ParticipantCols.WELCOME_CARD_NUMBER.getColumn());
		seekerTo.setWelcomeCardNo(validateNull(cellWelcomeCardNo));
		return seekerTo;
	}

	/**
	 * @param seekerTo
	 * @param cellPhone
	 */
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
	 * @param seekerTo
	 */
	private void setWelcomeCardIssuedDate(Row row, SeekerAimsTO seekerTo) {
		Cell welcomIssDate = row.getCell(ParticipantCols.WELCOME_CARD_ISSUE_DATE.getColumn());
		if (welcomIssDate != null) {
			String string = welcomIssDate.toString();
			try {
				Date date = dateFormat.parse(string);
				seekerTo.setWelcomeCardIssuedDate(date);
			} catch (ParseException e) {
				seekerTo.setWelcomeCardIssuedRawDate(string);
			}
		}
	}


}
