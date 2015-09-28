/**
 * 
 */
package org.srcm.pmp.to;

import java.util.Date;
import java.util.List;

/**
 * @author MASTER
 *
 */
public class MemberShipTO implements TransferObject {
	private static final long serialVersionUID = -8643331561977226403L;
	private Date programStartDate;
	private Date programEndDate;
	private List<ParticipantTO> participants;
	private ChannelTO channel;
	private InstituteTO institute;
	private CoordinatorTO coordinator;
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
	 * @return the programStartDate
	 */
	public Date getProgramStartDate() {
		return programStartDate;
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
	 * @return the participants
	 */
	public List<ParticipantTO> getParticipants() {
		return participants;
	}
	/**
	 * @param participants the participants to set
	 */
	public void setParticipants(List<ParticipantTO> participants) {
		this.participants = participants;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MemberShipTO [programStartDate=" + programStartDate
				+ ", programEndDate=" + programEndDate + ", participants="
				+ participants + ", channel=" + channel + ", institute="
				+ institute + ", coordinator=" + coordinator + "]";
	}
	

}
