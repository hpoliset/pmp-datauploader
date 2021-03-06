package org.srcm.pmp.utility;

import java.util.List;

import org.srcm.pmp.to.ProgramHeaderTO;
import org.srcm.pmp.to.SeekerAimsTO;

/**
 * @author MASTER
 *
 */
public interface ExcelDataProcessor {

	/**
	 * @return
	 * @throws Exception 
	 */
	public ProgramHeaderTO buildProgramDetails() throws Exception;
	
	/**
	 * @return
	 */
	public List<SeekerAimsTO> buildParticipants() throws Exception;
	
	
	
}
