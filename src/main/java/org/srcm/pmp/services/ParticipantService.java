/**
 * 
 */
package org.srcm.pmp.services;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.srcm.pmp.dao.SeekerDAO;
import org.srcm.pmp.domain.Program;
import org.srcm.pmp.domain.SeekerAims;
import org.srcm.pmp.to.ProgramHeaderTO;
import org.srcm.pmp.to.SeekerAimsTO;
import org.srcm.pmp.transformers.ProgramSeekerTOTransformer;
import org.srcm.pmp.utility.ExcelDataFactory;
import org.srcm.pmp.utility.ExcelDataProcessor;
import org.srcm.pmp.utility.ExcelDataValidator;

/**
 * @author MASTER
 *
 */
@Service
public class ParticipantService {
	@Autowired
	private SeekerDAO dao;
	private static Logger sLogger = LoggerFactory
			.getLogger(ParticipantService.class.getName());
	
	

	/**
	 * @param participants
	 * @throws Exception 
	 */
	@Transactional
	public void persistSeekerDetailsFromExcel(CommonsMultipartFile file) throws Exception {
		ExcelDataFactory factory = ExcelDataFactory.getInstance();
		ExcelDataProcessor transformer = factory.getExcelDataExtractor(file.getBytes(),file.getFileItem().getName());
		ExcelDataValidator transformerValid = factory.getExcelDataValidator(file.getBytes(),file.getFileItem().getName());
		StringBuffer loggerMessage = new StringBuffer();
		if (transformerValid.validateContent(loggerMessage)) {
			List<SeekerAimsTO> aimsTOs = transformer.buildParticipants();
			ProgramHeaderTO programHeaderTO = transformer.buildProgramDetails();
			programHeaderTO.setSeekerAimsTo(aimsTOs);
			List<SeekerAims> aims = ProgramSeekerTOTransformer
					.convertSeekerAimsFrom(aimsTOs);
			Program program = ProgramSeekerTOTransformer
					.convertFrom(programHeaderTO);
			program.setSeekerAimses(aims);
			dao.saveOrUpdate(program);
			for (SeekerAims saims : aims) {
				saims.setProgram(program);
				dao.saveOrUpdate(saims);
			}
		}
		sLogger.info(loggerMessage.toString());
		
	}
}
