/**
 * 
 */
package org.srcm.pmp.to;

import java.util.Date;

/**
 * @author MASTER
 *
 */
public class SeekerAimsTO implements TransferObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3900711046306772519L;
	private int seekerID;
	private String abhyasiID;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String country;
	private String email;
	private String firstName;
	private String idCardNum;
	private String language;
	private String introducedRawDate;
	private Date welcomeCardIssuedDate;
	private String welcomeCardIssuedRawDate;
	private String department;
	private boolean receiveUpddates;
	private String ageGroup;
	private String preferredLanguageForCommunication;
	private String batchOrYear;
	private String welcomeCardNo;
	/**
	 * @return the welcomeCardNo
	 */
	public String getWelcomeCardNo() {
		return welcomeCardNo;
	}
	/**
	 * @param welcomeCardNo the welcomeCardNo to set
	 */
	public void setWelcomeCardNo(String welcomeCardNo) {
		this.welcomeCardNo = welcomeCardNo;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the receiveUpddates
	 */
	public boolean isReceiveUpddates() {
		return receiveUpddates;
	}
	/**
	 * @param receiveUpddates the receiveUpddates to set
	 */
	public void setReceiveUpddates(boolean receiveUpddates) {
		this.receiveUpddates = receiveUpddates;
	}
	/**
	 * @return the ageGroup
	 */
	public String getAgeGroup() {
		return ageGroup;
	}
	/**
	 * @param ageGroup the ageGroup to set
	 */
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	/**
	 * @return the preferredLanguageForCommunication
	 */
	public String getPreferredLanguageForCommunication() {
		return preferredLanguageForCommunication;
	}
	/**
	 * @param preferredLanguageForCommunication the preferredLanguageForCommunication to set
	 */
	public void setPreferredLanguageForCommunication(
			String preferredLanguageForCommunication) {
		this.preferredLanguageForCommunication = preferredLanguageForCommunication;
	}
	/**
	 * @return the batchOrYear
	 */
	public String getBatchOrYear() {
		return batchOrYear;
	}
	/**
	 * @param batchOrYear the batchOrYear to set
	 */
	public void setBatchOrYear(String batchOrYear) {
		this.batchOrYear = batchOrYear;
	}
	private String gender;
	
	/**
	 * @return the welcomeCardIssuedDate
	 */
	public Date getWelcomeCardIssuedDate() {
		return welcomeCardIssuedDate;
	}
	/**
	 * @param welcomeCardIssuedDate the welcomeCardIssuedDate to set
	 */
	public void setWelcomeCardIssuedDate(Date welcomeCardIssuedDate) {
		this.welcomeCardIssuedDate = welcomeCardIssuedDate;
	}
	/**
	 * @return the welcomeCardIssuedRawDate
	 */
	public String getWelcomeCardIssuedRawDate() {
		return welcomeCardIssuedRawDate;
	}
	/**
	 * @param welcomeCardIssuedRawDate the welcomeCardIssuedRawDate to set
	 */
	public void setWelcomeCardIssuedRawDate(String welcomeCardIssuedRawDate) {
		this.welcomeCardIssuedRawDate = welcomeCardIssuedRawDate;
	}
	/**
	 * @return the introducedRawDate
	 */
	public String getIntroducedRawDate() {
		return introducedRawDate;
	}
	/**
	 * @param introducedRawDate the introducedRawDate to set
	 */
	public void setIntroducedRawDate(String introducedRawDate) {
		this.introducedRawDate = introducedRawDate;
	}
	/**
	 * @return the seekerID
	 */
	public int getSeekerID() {
		return seekerID;
	}
	/**
	 * @param seekerID the seekerID to set
	 */
	public void setSeekerID(int seekerID) {
		this.seekerID = seekerID;
	}
	/**
	 * @return the abhyasiID
	 */
	public String getAbhyasiID() {
		return abhyasiID;
	}
	/**
	 * @param abhyasiID the abhyasiID to set
	 */
	public void setAbhyasiID(String abhyasiID) {
		this.abhyasiID = abhyasiID;
	}
	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}
	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}
	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the idCardNum
	 */
	public String getIdCardNum() {
		return idCardNum;
	}
	/**
	 * @param idCardNum the idCardNum to set
	 */
	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[seekerID=" + seekerID + "\n, abhyasiID="
				+ abhyasiID + "\n, addressLine1=" + addressLine1
				+ "\n, addressLine2=" + addressLine2 + "\n, city=" + city
				+ "\n, country=" + country + "\n, email=" + email + "\n, firstName="
				+ firstName + "\n, gender=" + gender + "\n, idCardNum=" + idCardNum
				+ "\n, language=" + language + "\n, introducedRawDate="
				+ introducedRawDate + "\n, lastName=" + lastName
				+ "\n, phoneMobile=" + phoneMobile + "\n, occupation=" + occupation
				+ "\n, remarks=" + remarks + "\n, state=" + state + "\n, introduced="
				+ introduced + "\n, introducedDate=" + introducedDate
				+ "\n, introducedBy=" + introducedBy + "]";
	}
	/**
	 * @return the phoneMobile
	 */
	public String getPhoneMobile() {
		return phoneMobile;
	}
	/**
	 * @param phoneMobile the phoneMobile to set
	 */
	public void setPhoneMobile(String phoneMobile) {
		this.phoneMobile = phoneMobile;
	}
	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}
	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
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
	 * @return the introduced
	 */
	public boolean isIntroduced() {
		return introduced;
	}
	/**
	 * @param introduced the introduced to set
	 */
	public void setIntroduced(boolean introduced) {
		this.introduced = introduced;
	}
	/**
	 * @return the introducedDate
	 */
	public Date getIntroducedDate() {
		return introducedDate;
	}
	/**
	 * @param introducedDate the introducedDate to set
	 */
	public void setIntroducedDate(Date introducedDate) {
		this.introducedDate = introducedDate;
	}
	/**
	 * @return the introducedBy
	 */
	public String getIntroducedBy() {
		return introducedBy;
	}
	/**
	 * @param introducedBy the introducedBy to set
	 */
	public void setIntroducedBy(String introducedBy) {
		this.introducedBy = introducedBy;
	}
	private String lastName;
	private String phoneMobile;
    private String occupation;
    private String remarks;
	private String state;
    private boolean introduced;
    private Date introducedDate;
    private String introducedBy;


}
