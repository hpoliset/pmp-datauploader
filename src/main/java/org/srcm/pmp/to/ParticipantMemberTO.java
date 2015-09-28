/**
 * 
 */
package org.srcm.pmp.to;

import java.util.Date;

/**
 * @author MASTER
 *
 */
public class ParticipantMemberTO implements TransferObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4370215761558115426L;
	private Date programStartDate;
	private Date programEndDate;
	private ParticipantTO participant;
	private CoordinatorTO coordinator;
	private InstituteTO institute;
	private ChannelTO channel;
	
	/**
	 * @return the programStartDate
	 */
	public Date getProgramStartDate() {
		return programStartDate;
	}
	/**
	 * @param programStartDate the programStartDate to set
	 */
	public void setProgramStartDate(Date programStartDate) {
		this.programStartDate = programStartDate;
	}
	/**
	 * @return the programEndDate
	 */
	public Date getProgramEndDate() {
		return programEndDate;
	}
	/**
	 * @param programEndDate the programEndDate to set
	 */
	public void setProgramEndDate(Date programEndDate) {
		this.programEndDate = programEndDate;
	}
	/**
	 * @return the participant
	 */
	public ParticipantTO getParticipant() {
		return participant;
	}
	/**
	 * @param participant the participant to set
	 */
	public void setParticipant(ParticipantTO participant) {
		this.participant = participant;
	}
	/**
	 * @return the coordinator
	 */
	public CoordinatorTO getCoordinator() {
		return coordinator;
	}
	/**
	 * @param coordinator the coordinator to set
	 */
	public void setCoordinator(CoordinatorTO coordinator) {
		this.coordinator = coordinator;
	}
	/**
	 * @return the institute
	 */
	public InstituteTO getInstitute() {
		return institute;
	}
	/**
	 * @param institute the institute to set
	 */
	public void setInstitute(InstituteTO institute) {
		this.institute = institute;
	}
	/**
	 * @return the channel
	 */
	public ChannelTO getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(ChannelTO channel) {
		this.channel = channel;
	}
}
