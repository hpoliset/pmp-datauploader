package org.srcm.pmp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the seeker database table.
 * 
 */
@Entity
@Table(name="seeker")
public class Seeker implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private long seeker_ID;

	private String abhyasi_ID;

	private byte age;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	private String created_by;

	@Temporal(TemporalType.DATE)
	private Date date_of_Registration;

	private String email;

	private String first_Name;

	private int gender;

	private String ID_Card_Num;

	private String language;

	private String occupation;

	private String phone_Mobile;

	private byte status;

	private byte subscribe;
	private SeekerAimsCols seekerAimsCols;

	
	private Date updateTime;

	@Column(name="updated_by")
	private String updatedBy;

	private byte welcome_msg_sent;

	//bi-directional many-to-one association to Membership
	
	private List<Membership> memberships;

	//bi-directional many-to-one association to Maturity
	
	private Maturity maturity;

	public Seeker() {
	}
	@Id
	@GenericGenerator(name="icn" , strategy="increment")
	@GeneratedValue(generator="icn")
	@Column(name="seeker_ID")
	public long getSeeker_ID() {
		return this.seeker_ID;
	}

	public void setSeeker_ID(long seeker_ID) {
		this.seeker_ID = seeker_ID;
	}
	@Column(name="Abhyasi_ID")
	public String getAbhyasi_ID() {
		return this.abhyasi_ID;
	}

	public void setAbhyasi_ID(String abhyasi_ID) {
		this.abhyasi_ID = abhyasi_ID;
	}
	@Column(name="age")
	public byte getAge() {
		return this.age;
	}

	public void setAge(byte age) {
		this.age = age;
	}
	@Column(name="create_Time")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name="created_by")
	public String getCreated_by() {
		return this.created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	@Column(name="date_of_Registration")
	public Date getDate_of_Registration() {
		return this.date_of_Registration;
	}

	public void setDate_of_Registration(Date date_of_Registration) {
		this.date_of_Registration = date_of_Registration;
	}
	@Column(name="email")
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
	@Column(name="Gender")
	public int getGender() {
		return this.gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
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
	@Column(name="occupation")
	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	@Column(name="phone_Mobile")
	public String getPhone_Mobile() {
		return this.phone_Mobile;
	}

	public void setPhone_Mobile(String phone_Mobile) {
		this.phone_Mobile = phone_Mobile;
	}
	@Column(name="status")
	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}
	@Column(name="subscribe")
	public byte getSubscribe() {
		return this.subscribe;
	}

	public void setSubscribe(byte subscribe) {
		this.subscribe = subscribe;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name="updated_By")
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name="welcome_msg_sent")
	public byte getWelcome_msg_sent() {
		return this.welcome_msg_sent;
	}

	public void setWelcome_msg_sent(byte welcome_msg_sent) {
		this.welcome_msg_sent = welcome_msg_sent;
	}
	@OneToMany(mappedBy="seeker")
	public List<Membership> getMemberships() {
		return this.memberships;
	}

	public void setMemberships(List<Membership> memberships) {
		this.memberships = memberships;
	}

	public Membership addMembership(Membership membership) {
		getMemberships().add(membership);
		membership.setSeeker(this);

		return membership;
	}

	public Membership removeMembership(Membership membership) {
		getMemberships().remove(membership);
		membership.setSeeker(null);

		return membership;
	}
	@ManyToOne
	@JoinColumn(name="Maturity_Status_ID")
	public Maturity getMaturity() {
		return this.maturity;
	}

	public void setMaturity(Maturity maturity) {
		this.maturity = maturity;
	}
	@OneToOne(fetch = FetchType.LAZY,mappedBy="seeker")
	public SeekerAimsCols getSeekerAimsCols() {
		return seekerAimsCols;
	}
	public void setSeekerAimsCols(SeekerAimsCols seekerAimsCols) {
		this.seekerAimsCols = seekerAimsCols;
	}

}