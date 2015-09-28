/**
 * 
 */
package org.srcm.pmp.transformers;

import org.srcm.pmp.domain.SeekerAim;
import org.srcm.pmp.to.ChannelTO;
import org.srcm.pmp.to.CoordinatorTO;
import org.srcm.pmp.to.InstituteTO;
import org.srcm.pmp.to.ParticipantMemberTO;
import org.srcm.pmp.to.ParticipantTO;

/**
 * @author MASTER
 *
 */
public class ParticipantTOTransformer {

	/**
	 * @param participant
	 * @return
	 */
	public static SeekerAim convertFrom(ParticipantMemberTO partMem) {
		SeekerAim seeker = new SeekerAim();
		ParticipantTO participant = partMem.getParticipant();
		ChannelTO channel = partMem.getChannel();
		CoordinatorTO coordinator = partMem.getCoordinator();
		InstituteTO institute = partMem.getInstitute();
		seeker.setEmail(participant .getEmail());
		if (participant.getGender()!=null && participant.getGender().equalsIgnoreCase("M")) {
			seeker.setGender(1);
		} else {
			seeker.setGender(0);
		}
		if (participant.getName() != null) {
			String[] split = participant.getName().split(",");
			if (split.length > 1) {
				seeker.setFirst_Name(split[0]);
				seeker.setLast_Name(split[1]);
			} else {
				seeker.setFirst_Name(participant.getName());
			}
		}
		seeker.setAddress_Line_1(participant.getPermAddress());
		seeker.setCity(participant.getCity());
		seeker.setState(participant.getState());
		seeker.setDate_of_Birth(participant.getDateOfBirth());
		if (participant.getPhoneNumber() != null) {
			Double f = convertToDouble(participant.getPhoneNumber());
			if (f == null) {
				seeker.setPhone_Mobile(participant.getPhoneNumber());
			} else {
				seeker.setPhone_Mobile(String.valueOf(f.longValue()));
			}
		}
		seeker.setDate_of_Registration(participant.getIntrodutionDate());
		seeker.setChannel_Name(channel.getChannelName());
		seeker.setCoord_Center_Name(coordinator.getCenter());
		seeker.setCoord_Name(coordinator.getCoordinatorName());
		seeker.setCoord_Email(coordinator.getEmailId());
		seeker.setCoord_Country(coordinator.getCountry());
		seeker.setInst_Name(institute.getInstituteName());
		seeker.setInst_Website(institute.getWebsite());
		seeker.setIntroduced(participant.isIntroduced());
		seeker.setIntroduced_By(participant.getIntroducedBy());
		seeker.setIntroduced_Date(participant.getIntrodutionDate());
		return seeker;

	}

	private static Double convertToDouble(String phoneNumber) {
		try{
		Double parseFloat = Double.parseDouble(phoneNumber);
		 return parseFloat;
		}catch(NumberFormatException exception){
			
		}
		return null;
	}
	
	
}
