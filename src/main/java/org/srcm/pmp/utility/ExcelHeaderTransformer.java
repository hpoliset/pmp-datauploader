/**
 * 
 */
package org.srcm.pmp.utility;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.srcm.pmp.to.ProgramHeaderTO;
import org.srcm.pmp.to.ProgramHeaderValidator;
import org.xml.sax.SAXException;

/**
 * ExcelTransformer reads the data from XLS and converted to Beans.
 * 
 * 
 * @author MASTER
 *
 */
public class ExcelHeaderTransformer {
	//private static final String RESOURCES_HEART_FULL_PROGRAM = "resources/HeartFullProgram.xml";
	private static final String RESOURCES_HEART_FULL_PROGRAM = "resources/HeartFullProgramHeader.xml";
	private static Logger sLogger = LoggerFactory
			.getLogger(ExcelHeaderTransformer.class.getName());
	private byte[] data;
	private CommonsMultipartFile aFile;
	public ExcelHeaderTransformer(byte[] data) {
		this.data = data;
	}
	
	public ExcelHeaderTransformer(CommonsMultipartFile uploadFile) {
		this.aFile = uploadFile;
	}

	/**
	 * @return MemberShipTO
	 */
	public void validate() {
		try {
			
			InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(RESOURCES_HEART_FULL_PROGRAM);
			//InputStream resourceAsStream = ClassLoader.getSystemResourceAsStream(RESOURCES_HEART_FULL_PROGRAM);
			InputStream inputXML = new BufferedInputStream(resourceAsStream);
			XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
			InputStream fs = null;
			if(aFile!=null){
				fs = aFile.getInputStream();
			}else{
				fs =	new ByteArrayInputStream(data);
			}
			BufferedInputStream inputXLS = new BufferedInputStream(fs);
			ProgramHeaderTO header = new ProgramHeaderTO();
			ProgramHeaderValidator validator = new ProgramHeaderValidator();
			Map<String,Object> beans = new HashMap<String,Object>();
			beans.put("header", header);
			beans.put("validator", validator);
			XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
			sLogger.info("Processed OK:"+readStatus.isStatusOK());
			validator.validateHeaders();
			validator.validateValues(header);
			
		} catch (FileNotFoundException e) {
			sLogger.error("File Not found:" + RESOURCES_HEART_FULL_PROGRAM, e);
		} catch (IOException e) {
			sLogger.error("File Read exception" + RESOURCES_HEART_FULL_PROGRAM,
					e);
		} catch (SAXException e) {
			sLogger.error("Parser Exception", e);
		} catch (InvalidFormatException e) {
			sLogger.error("Invalid Format Exception", e);
		}
	}

	public static void main(String[] args) {
		FileInputStream fs;
		try {
			fs = new FileInputStream("E:\\SampleData1.xlsx");
			byte[] data = new byte[10000000];
			fs.read(data);
			fs.close();
			ExcelHeaderTransformer transformer = new ExcelHeaderTransformer(data);
			transformer.validate();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
