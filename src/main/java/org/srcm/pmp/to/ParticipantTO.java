/**
 * 
 */
package org.srcm.pmp.to;

import java.util.Date;

/**
 * @author MASTER
 *
 */
public class ParticipantTO implements TransferObject {
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ParticipantTO [name=" + name + ", gender=" + gender
				+ ", dateOfBirth=" + dateOfBirth + ", permAddress="
				+ permAddress + ", city=" + city + ", state=" + state
				+ ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", introduced=" + introduced + ", introdutionDate="
				+ introdutionDate + ", introducedBy=" + introducedBy + "]";
	}
	private static final long serialVersionUID = 1L;
    public ParticipantTO(){
    	
    }
	private String name;
	
	private String gender;
	private Date dateOfBirth;
	private String permAddress;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return the permAddress
	 */
	public String getPermAddress() {
		return permAddress;
	}
	/**
	 * @param permAddress the permAddress to set
	 */
	public void setPermAddress(String permAddress) {
		this.permAddress = permAddress;
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
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	 * @return the introdutionDate
	 */
	public Date getIntrodutionDate() {
		return introdutionDate;
	}
	/**
	 * @param introdutionDate the introdutionDate to set
	 */
	public void setIntrodutionDate(Date introdutionDate) {
		this.introdutionDate = introdutionDate;
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
	private String city;
	private String state;
	private String email;
	private String phoneNumber;
	private boolean introduced;
	private Date introdutionDate;
	private String introducedBy;
}
