/**
 * 
 */
package org.srcm.pmp.transformers;

import java.util.ArrayList;
import java.util.List;

import org.srcm.pmp.domain.Program;
import org.srcm.pmp.domain.SeekerAims;
import org.srcm.pmp.to.ProgramHeaderTO;
import org.srcm.pmp.to.SeekerAimsTO;

/**
 * @author Master
 *
 */
public class ProgramSeekerTOTransformer {

	/**
	 * @param ProgramTO
	 * @return
	 */
	public static Program convertFrom(ProgramHeaderTO programTo) {
		List<SeekerAims> seekerAims = null;

		if (programTo == null)
			return null;
		
		if (programTo.getSeekerAimsTo() == null) {
			seekerAims = new ArrayList<SeekerAims>();
		} else {
			seekerAims = convertSeekerAimsFrom(programTo.getSeekerAimsTo());
		}

		// convert ProgramTO to Program Entity
		Program program = new Program(programTo.getChannelName(),
				programTo.getCoordinatorName(), programTo.getEmail(),
				programTo.getCenter(), programTo.getCountry(), programTo.getInstituteName(),
				programTo.getWebsite(), programTo.getProgramStartDate(), programTo.getProgramRawStartDate(),
				seekerAims);
		return program;

	}

	/**
	 * Method is used to conertSeekerAimsTO to Entity object
	 * @param seekerAimsTo
	 * @return
	 */
	public static List<SeekerAims> convertSeekerAimsFrom(List<SeekerAimsTO> seekerAimsTo) {

		List<SeekerAims> seekerAims = new ArrayList<SeekerAims>();

		for (SeekerAimsTO seeker : seekerAimsTo) {
			seekerAims.add(new SeekerAims(seeker.getFirstName(),
					seeker.getLastName(),"", seeker.getEmail(), seeker.getPhoneMobile(),
					seeker.getGender(), null, null, seeker.getAbhyasiID(),
					false, seeker.getAddressLine1(), seeker.getAddressLine2(), seeker.getCity(),
					seeker.getState(), seeker.getCountry(), seeker.getOccupation(), seeker.getRemarks(),
					seeker.getIdCardNum(), seeker.getLanguage(), "", seeker.isIntroduced(),
					seeker.getIntroducedDate(), seeker.getIntroducedBy()));
		}
		
		return seekerAims;

	}

}
