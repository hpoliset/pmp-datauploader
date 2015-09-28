package org.srcm.pmp.domain;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


/**
 * The persistent class for the seeker_aims database table.
 * 
 */
@Entity
@Table(name="seeker_aims")
public class SeekerAim implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private int seeker_ID;

	private String abhyasi_ID;

	private String address_Line_1;

	private String address_Line_2;

	private String city;

	private String country;

	@Temporal(TemporalType.DATE)
	private Date date_of_Birth;

	@Temporal(TemporalType.DATE)
	private Date date_of_Registration;

	private String email;

	private String first_Name;

	private int gender;

	private String ID_Card_Num;

	private String language;

	private String last_Name;

	private String channel_Name; 
	private String coord_Name; 
	private String coord_Email; 
	private String coord_Center_Name; 
	private String coord_Country; 
	private String inst_Name; 
	private String inst_Website; 
	private Date program_Start_Date; 
	private Date program_End_Date; 
	private boolean introduced; 
	private Date introduced_Date; 
	private String introduced_By; 

	private String middle_Name;

	private String phone_Mobile;

	private String state;

	private byte status;

	private String sync_Status;
	
	

	public SeekerAim() {
	}

	@Id
	@GenericGenerator(name="master",strategy="increment")
	@GeneratedValue(generator="master")
	@Column(name="Seeker_ID")
	public int getSeeker_ID() {
		return this.seeker_ID;
	}

	public void setSeeker_ID(int seeker_ID) {
		this.seeker_ID = seeker_ID;
	}

	public String getAbhyasi_ID() {
		return this.abhyasi_ID;
	}

	public void setAbhyasi_ID(String abhyasi_ID) {
		this.abhyasi_ID = abhyasi_ID;
	}

	public String getAddress_Line_1() {
		return this.address_Line_1;
	}

	public void setAddress_Line_1(String address_Line_1) {
		this.address_Line_1 = address_Line_1;
	}

	public String getAddress_Line_2() {
		return this.address_Line_2;
	}

	public void setAddress_Line_2(String address_Line_2) {
		this.address_Line_2 = address_Line_2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDate_of_Birth() {
		return this.date_of_Birth;
	}

	public void setDate_of_Birth(Date date_of_Birth) {
		this.date_of_Birth = date_of_Birth;
	}

	public Date getDate_of_Registration() {
		return this.date_of_Registration;
	}

	public void setDate_of_Registration(Date date_of_Registration) {
		this.date_of_Registration = date_of_Registration;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="First_Name")
	public String getFirst_Name() {
		return this.first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}
	@Column(name="gender")
	public int getGender() {
		return this.gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}
	/**
	 * @return the hannel_Name
	 */
	@Column(name="Channel_Name ")
	public String getChannel_Name() {
		return channel_Name;
	}

	/**
	 * @param hannel_Name the hannel_Name to set
	 */
	public void setChannel_Name(String channel_Name) {
		this.channel_Name = channel_Name;
	}

	/**
	 * @return the coord_Name
	 */
	@Column(name="Coord_Name ")
	public String getCoord_Name() {
		return coord_Name;
	}

	/**
	 * @param coord_Name the coord_Name to set
	 */
	public void setCoord_Name(String coord_Name) {
		this.coord_Name = coord_Name;
	}

	/**
	 * @return the coord_Email
	 */
	@Column(name="Coord_Email")
	public String getCoord_Email() {
		return coord_Email;
	}

	/**
	 * @param coord_Email the coord_Email to set
	 */
	public void setCoord_Email(String coord_Email) {
		this.coord_Email = coord_Email;
	}

	/**
	 * @return the coord_Center_Name
	 */
	@Column(name="Coord_Center_Name")
	public String getCoord_Center_Name() {
		return coord_Center_Name;
	}

	/**
	 * @param coord_Center_Name the coord_Center_Name to set
	 */
	public void setCoord_Center_Name(String coord_Center_Name) {
		this.coord_Center_Name = coord_Center_Name;
	}

	/**
	 * @return the coord_Country
	 */
	@Column(name="Coord_Country")
	public String getCoord_Country() {
		return coord_Country;
	}

	/**
	 * @param coord_Country the coord_Country to set
	 */
	public void setCoord_Country(String coord_Country) {
		this.coord_Country = coord_Country;
	}

	/**
	 * @return the inst_Name
	 */
	@Column(name="Inst_Name")
	public String getInst_Name() {
		return inst_Name;
	}

	/**
	 * @param inst_Name the inst_Name to set
	 */
	public void setInst_Name(String inst_Name) {
		this.inst_Name = inst_Name;
	}

	/**
	 * @return the inst_Website
	 */
	@Column(name="Inst_Website")
	public String getInst_Website() {
		return inst_Website;
	}

	/**
	 * @param inst_Website the inst_Website to set
	 */
	public void setInst_Website(String inst_Website) {
		this.inst_Website = inst_Website;
	}

	/**
	 * @return the program_Start_Date
	 */
	@Column(name="Program_Start_Date")
	public Date getProgram_Start_Date() {
		return program_Start_Date;
	}

	/**
	 * @param program_Start_Date the program_Start_Date to set
	 */
	public void setProgram_Start_Date(Date program_Start_Date) {
		this.program_Start_Date = program_Start_Date;
	}

	/**
	 * @return the program_End_Date
	 */
	@Column(name="Program_End_Date")
	public Date getProgram_End_Date() {
		return program_End_Date;
	}

	/**
	 * @param program_End_Date the program_End_Date to set
	 */
	public void setProgram_End_Date(Date program_End_Date) {
		this.program_End_Date = program_End_Date;
	}

	/**
	 * @return the ntroduced
	 */
	@Column(name="Introduced")
	public boolean isIntroduced() {
		return introduced;
	}

	/**
	 * @param ntroduced the ntroduced to set
	 */
	public void setIntroduced(boolean introduced) {
		this.introduced = introduced;
	}

	/**
	 * @return the introduced_Date
	 */
	@Column(name="Introduced_Date")
	public Date getIntroduced_Date() {
		return introduced_Date;
	}

	/**
	 * @param introduced_Date the introduced_Date to set
	 */
	public void setIntroduced_Date(Date introduced_Date) {
		this.introduced_Date = introduced_Date;
	}

	/**
	 * @return the introduced_By
	 */
	@Column(name="Introduced_By ")
	public String getIntroduced_By() {
		return introduced_By;
	}

	/**
	 * @param introduced_By the introduced_By to set
	 */
	public void setIntroduced_By(String introduced_By) {
		this.introduced_By = introduced_By;
	}

	@Column(name="ID_Card_Num")
	public String getID_Card_Num() {
		return this.ID_Card_Num;
	}

	public void setID_Card_Num(String ID_Card_Num) {
		this.ID_Card_Num = ID_Card_Num;
	}
	@Column(name="Language")
	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	@Column(name="Last_Name")
	public String getLast_Name() {
		return this.last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}
	@Column(name="Middle_Name")
	public String getMiddle_Name() {
		return this.middle_Name;
	}

	public void setMiddle_Name(String middle_Name) {
		this.middle_Name = middle_Name;
	}
	@Column(name="Phone_Mobile")
	public String getPhone_Mobile() {
		return this.phone_Mobile;
	}

	public void setPhone_Mobile(String phone_Mobile) {
		this.phone_Mobile = phone_Mobile;
	}
	@Column(name="state")
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getSync_Status() {
		return this.sync_Status;
	}

	public void setSync_Status(String sync_Status) {
		this.sync_Status = sync_Status;
	}

}