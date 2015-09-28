/**
 * 
 */
package org.srcm.pmp.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author MASTER
 *
 */
@Entity
@Table(name = "seeker_aims_cols")
public class SeekerAimsCols implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -733198136898578450L;
	private long seekerID;
    private String lastName;
    private String middleName;
    private Date dateOfBirth;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private Seeker seeker;
	/**
	 * @return the seeker
	 */
    @OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Seeker getSeeker() {
		return seeker;
	}
	/**
	 * @param seeker the seeker to set
	 */
	public void setSeeker(Seeker seeker) {
		this.seeker = seeker;
	}
	/**
	 * @return the seekerID
	 */
    @Id
    @Column(name="Seeker_ID")
	public long getSeekerID() {
		return seekerID;
	}
	/**
	 * @param seekerID the seekerID to set
	 */
	public void setSeekerID(long seekerID) {
		this.seekerID = seekerID;
	}
	/**
	 * @return the lastName
	 */
	@Column(name="Last_Name")
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the middleName
	 */
	@Column(name="Middle_Name")
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return the dateOfBirth
	 */
	@Column(name="Date_of_Birth")
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
	 * @return the addressLine1
	 */
	@Column(name="Address_Line_1")
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
	@Column(name="Address_Line_2")
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
	@Column(name="City")
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
	@Column(name="State")
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
	 * @return the country
	 */
	@Column(name="Country")
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
}
