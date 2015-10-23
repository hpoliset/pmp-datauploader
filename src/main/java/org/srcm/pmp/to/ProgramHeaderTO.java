package org.srcm.pmp.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author MASTER
 *
 */
public class ProgramHeaderTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2032653757116096359L;
	private String channelName;
	 private String instituteName;
	  private String website;
	  private Date programStartDate;
	  private String programRawStartDate;
	  private String state;
	  private String other;
	  private String eventCity;
	  private String coordinatorMobile;
	  private String orgContactPerson;
	  private String preceptorName;
	  private String preceptorId;
	  private String welcomeCardSignedBy;
	  private String welcomeCardSignedID;
	  private String remarks;
	  private String orgContactEmail;
	  private String orgContactMobile;
	  /**
	 * @return the other
	 */
	public String getOther() {
		return other;
	}
	/**
	 * @param other the other to set
	 */
	public void setOther(String other) {
		this.other = other;
	}
	/**
	 * @return the eventCity
	 */
	public String getEventCity() {
		return eventCity;
	}
	/**
	 * @param eventCity the eventCity to set
	 */
	public void setEventCity(String eventCity) {
		this.eventCity = eventCity;
	}
	/**
	 * @return the coordinatorMobile
	 */
	public String getCoordinatorMobile() {
		return coordinatorMobile;
	}
	/**
	 * @param coordinatorMobile the coordinatorMobile to set
	 */
	public void setCoordinatorMobile(String coordinatorMobile) {
		this.coordinatorMobile = coordinatorMobile;
	}
	/**
	 * @return the orgContactPerson
	 */
	public String getOrgContactPerson() {
		return orgContactPerson;
	}
	/**
	 * @param orgContactPerson the orgContactPerson to set
	 */
	public void setOrgContactPerson(String orgContactPerson) {
		this.orgContactPerson = orgContactPerson;
	}
	/**
	 * @return the preceptorName
	 */
	public String getPreceptorName() {
		return preceptorName;
	}
	/**
	 * @param preceptorName the preceptorName to set
	 */
	public void setPreceptorName(String preceptorName) {
		this.preceptorName = preceptorName;
	}
	/**
	 * @return the preceptorId
	 */
	public String getPreceptorId() {
		return preceptorId;
	}
	/**
	 * @param preceptorId the preceptorId to set
	 */
	public void setPreceptorId(String preceptorId) {
		this.preceptorId = preceptorId;
	}
	/**
	 * @return the welcomeCardSignedBy
	 */
	public String getWelcomeCardSignedBy() {
		return welcomeCardSignedBy;
	}
	/**
	 * @param welcomeCardSignedBy the welcomeCardSignedBy to set
	 */
	public void setWelcomeCardSignedBy(String welcomeCardSignedBy) {
		this.welcomeCardSignedBy = welcomeCardSignedBy;
	}
	/**
	 * @return the welcomeCardSignedID
	 */
	public String getWelcomeCardSignedID() {
		return welcomeCardSignedID;
	}
	/**
	 * @param welcomeCardSignedID the welcomeCardSignedID to set
	 */
	public void setWelcomeCardSignedID(String welcomeCardSignedID) {
		this.welcomeCardSignedID = welcomeCardSignedID;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * @return the orgContactEmail
	 */
	public String getOrgContactEmail() {
		return orgContactEmail;
	}
	/**
	 * @param orgContactEmail the orgContactEmail to set
	 */
	public void setOrgContactEmail(String orgContactEmail) {
		this.orgContactEmail = orgContactEmail;
	}
	/**
	 * @return the orgContactMobile
	 */
	public String getOrgContactMobile() {
		return orgContactMobile;
	}
	/**
	 * @param orgContactMobile the orgContactMobile to set
	 */
	public void setOrgContactMobile(String orgContactMobile) {
		this.orgContactMobile = orgContactMobile;
	}
	/**
	 * @return the programRawStartDate
	 */
	public String getProgramRawStartDate() {
		return programRawStartDate;
	}
	/**
	 * @param programRawStartDate the programRawStartDate to set
	 */
	public void setProgramRawStartDate(String programRawStartDate) {
		this.programRawStartDate = programRawStartDate;
	}
	private String country;
	  private String coordinatorName;
	  private String email;
	  private String center;
	  private List<SeekerAimsTO> seekerAimsTo;
	/**
	 * @return the channelName
	 */
	public String getChannelName() {
		return channelName;
	}
	/**
	 * @param channelName the channelName to set
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	/**
	 * @return the instituteName
	 */
	public String getInstituteName() {
		return instituteName;
	}
	/**
	 * @param instituteName the instituteName to set
	 */
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}
	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
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
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @param country the country to set
	 */
	public void center(String country) {
		this.country = country;
	}
	/**
	 * @return the coordinatorName
	 */
	public String getCoordinatorName() {
		return coordinatorName;
	}
	/**
	 * @param coordinatorName the coordinatorName to set
	 */
	public void setCoordinatorName(String coordinatorName) {
		this.coordinatorName = coordinatorName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the center
	 */
	public String getCenter() {
		return center;
	}
	/**
	 * @param center the center to set
	 */
	public void setCenter(String center) {
		this.center = center;
	}
	
	
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the seekerAimsTo
	 */
	public List<SeekerAimsTO> getSeekerAimsTo() {
		return seekerAimsTo;
	}
	/**
	 * @param seekerAimsTo the seekerAimsTo to set
	 */
	public void setSeekerAimsTo(List<SeekerAimsTO> seekerAimsTo) {
		this.seekerAimsTo = seekerAimsTo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[channelName=" + channelName
				+ ",\ninstituteName=" + instituteName + ",\n website=" + website
				+ ",\n program raw Start date=" + programRawStartDate 
				+ ",\n programStartDate=" + programStartDate + ",\n country="
				+ country + ",\n coordinatorName=" + coordinatorName + ",\n email="
				+ email + ",\n center=" + center + ",\n State=" + state +"]";
	}
	
	
}
