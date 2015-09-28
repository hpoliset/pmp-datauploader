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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.srcm.pmp.to.ChannelTO;
import org.srcm.pmp.to.CoordinatorTO;
import org.srcm.pmp.to.InstituteTO;
import org.srcm.pmp.to.MemberShipTO;
import org.srcm.pmp.to.ParticipantMemberTO;
import org.srcm.pmp.to.ParticipantTO;
import org.xml.sax.SAXException;

/**
 * ExcelTransformer reads the data from XLS and converted to Beans.
 * 
 * 
 * @author MASTER
 *
 */
public class ExcelTransformer {
	//private static final String RESOURCES_HEART_FULL_PROGRAM = "resources/HeartFullProgram.xml";
	private static final String RESOURCES_HEART_FULL_PROGRAM = "resources/HeartFullProgram_Serial.xml";
	private static Logger sLogger = LoggerFactory
			.getLogger(ExcelTransformer.class.getName());
	private byte[] data;
	private CommonsMultipartFile aFile;
	public ExcelTransformer(byte[] data) {
		this.data = data;
	}
	
	public ExcelTransformer(CommonsMultipartFile uploadFile) {
		this.aFile = uploadFile;
	}

	/**
	 * @return MemberShipTO
	 */
	public List<ParticipantMemberTO> buildParticipantData() {
		try {
			if(aFile==null){
				return null;
			}
			
			InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(RESOURCES_HEART_FULL_PROGRAM);
			//InputStream resourceAsStream = ClassLoader.getSystemResourceAsStream(RESOURCES_HEART_FULL_PROGRAM);
			InputStream inputXML = new BufferedInputStream(resourceAsStream);
			XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
			InputStream fs = null;
			if(aFile!=null){
				fs = aFile.getInputStream();
			}else{
				fs =	new ByteArrayInputStream(data);
			}
			List<ParticipantMemberTO> lstPart = new ArrayList<ParticipantMemberTO>();
			BufferedInputStream inputXLS = new BufferedInputStream(fs);
			ChannelTO channelTo = new ChannelTO();
			InstituteTO inst = new InstituteTO();
			CoordinatorTO coordinator = new CoordinatorTO();
			MemberShipTO memberShip = new MemberShipTO();
			List<ParticipantTO> participants = new ArrayList<ParticipantTO>();
			memberShip.setParticipants(participants);
			memberShip.setChannel(channelTo);
			memberShip.setInstitute(inst);
			memberShip.setCoordinator(coordinator);
			Map<String,Object> beans = new HashMap<String,Object>();
			beans.put("channel", channelTo);
			beans.put("institute", inst);
			beans.put("coordinator", coordinator);
			beans.put("member", memberShip);
			beans.put("participants", participants);
			XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
			sLogger.info("Processed OK:"+readStatus.isStatusOK());
			sLogger.debug(memberShip.toString());
			
			for(ParticipantTO participant:participants){
				ParticipantMemberTO partMem = new ParticipantMemberTO();
				partMem.setParticipant(participant);
				partMem.setCoordinator(coordinator);
				partMem.setChannel(channelTo);
				partMem.setInstitute(inst);
				partMem.setProgramEndDate(memberShip.getProgramEndDate());
				partMem.setProgramStartDate(memberShip.getProgramStartDate());
				lstPart.add(partMem);
			}
			
			return lstPart;
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
		return null;
	}

	public static void main(String[] args) {
		FileInputStream fs;
		try {
			fs = new FileInputStream("E:\\SampleData1.xlsx");
			byte[] data = new byte[10000000];
			fs.read(data);
			fs.close();
			ExcelTransformer transformer = new ExcelTransformer(data);
			List<ParticipantMemberTO> buildParticipantData = transformer.buildParticipantData();
			System.out.println(buildParticipantData);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
