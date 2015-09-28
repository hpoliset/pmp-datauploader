/**
 * 
 */
package org.srcm.pmp.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.srcm.pmp.dao.SeekerDAO;
import org.srcm.pmp.domain.Program;
import org.srcm.pmp.domain.SeekerAims;
import org.srcm.pmp.to.ProgramHeaderTO;
import org.srcm.pmp.to.SeekerAimsTO;
import org.srcm.pmp.transformers.ProgramSeekerTOTransformer;
import org.srcm.pmp.utility.ExcelDataExtractor;
import org.srcm.pmp.utility.ExcelDataExtractor.FILE_TYPE;

/**
 * @author MASTER
 *
 */
@Service
public class ParticipantService {
	@Autowired
	private SeekerDAO dao;
	
	

	/**
	 * @param participants
	 */
	@Transactional
	public void persistSeekerDetailsFromExcel(CommonsMultipartFile aFile) {
		ExcelDataExtractor transformer = null;
		
		if((aFile.getFileItem().getName().endsWith("xlsx"))){
			transformer = new ExcelDataExtractor(aFile.getBytes(),FILE_TYPE.XLSX);
		}else if(aFile.getFileItem().getName().endsWith("xls")){
			transformer = new ExcelDataExtractor(aFile.getBytes(),FILE_TYPE.XLS);
		}
		
		List<SeekerAimsTO> aimsTOs = transformer.buildParticipants();
		ProgramHeaderTO programHeaderTO = transformer.buildProgramDetails();
		programHeaderTO.setSeekerAimsTo(aimsTOs);
		List<SeekerAims> aims = ProgramSeekerTOTransformer.convertSeekerAimsFrom(aimsTOs);
		Program program = ProgramSeekerTOTransformer.convertFrom(programHeaderTO);
		program.setSeekerAimses(aims);
		dao.saveOrUpdate(program);
		for(SeekerAims saims:aims){
			saims.setProgram(program);
			dao.saveOrUpdate(saims);
		}
		
	}
}
