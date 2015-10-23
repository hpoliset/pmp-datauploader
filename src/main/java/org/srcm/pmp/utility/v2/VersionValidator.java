/**
 * 
 */
package org.srcm.pmp.utility.v2;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.srcm.pmp.utility.ExcelDataExtractor;
import org.srcm.pmp.utility.FileType;

/**
 * @author MASTER
 *
 */
public class VersionValidator {
	private Workbook workbook;
	private Sheet sheet;
	private static Logger sLogger = LoggerFactory.getLogger(ExcelDataExtractor.class.getName());

	public VersionValidator(byte[] data, String fileName) {
		if (fileName.endsWith("xlsx"))
			buildWorkBook(data, FileType.XLSX);
		else if (fileName.endsWith("xls")) {
			buildWorkBook(data, FileType.XLS);
		}
	}
	
	/**
	 * @return
	 */
	public Version validateVersion(){
		if(workbook.getNumberOfSheets()==1){
			return Version.V1;
		}
		
		
		for(int cnt=0;cnt < sheet.getPhysicalNumberOfRows();cnt++){
			Row row = sheet.getRow(cnt);
			if(row.getCell(0)!=null){
				String val = row.getCell(0).toString();
				String[] versionData = val.split("/");
				if(versionData!=null && versionData.length>1){
					if(versionData[1].contains("V2.1")){
						return Version.V2;
					}
				}
				
			}
		}
		
		
		return Version.INVALID;
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
			sheet = workbook.getSheet("Event Details");
			if(sheet==null){
				sheet = workbook.getSheetAt(0);
			}
		} catch (IOException e) {
			sLogger.error("Issues with files",e);
		}
	}
}
