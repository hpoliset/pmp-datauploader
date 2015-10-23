package org.srcm.pmp.utility;

import java.util.List;

import org.srcm.pmp.to.ProgramHeaderTO;
import org.srcm.pmp.to.SeekerAimsTO;

public class InValidExcelDataExtractor implements ExcelDataProcessor {

	@Override
	public ProgramHeaderTO buildProgramDetails() throws Exception {
		throw new Exception("Invalid Version");
	}

	@Override
	public List<SeekerAimsTO> buildParticipants() throws Exception {
		throw new Exception("Invalid Version");
	}

}
