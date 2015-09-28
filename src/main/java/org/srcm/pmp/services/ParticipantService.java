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
import org.srcm.pmp.domain.SeekerAim;
import org.srcm.pmp.to.ParticipantMemberTO;
import org.srcm.pmp.transformers.ParticipantTOTransformer;
import org.srcm.pmp.utility.ExcelHeaderTransformer;
import org.srcm.pmp.utility.ExcelTransformer;

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
		ExcelHeaderTransformer transformer = new ExcelHeaderTransformer(aFile);
		transformer.validate();
	/*	ExcelTransformer transformer = new ExcelTransformer(aFile);
		 List<ParticipantMemberTO> participantData = transformer.buildParticipantData();
		if (participantData != null) {
				for (ParticipantMemberTO participant : participantData) {
					SeekerAim prtPant = ParticipantTOTransformer
							.convertFrom(participant);
						dao.saveOrUpdate(prtPant);
				}
			}*/
	}
}
