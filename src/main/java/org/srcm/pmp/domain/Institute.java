package org.srcm.pmp.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the institute database table.
 * 
 */
@Entity
@NamedQuery(name="Institute.findAll", query="SELECT i FROM Institute i")
public class Institute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int institute_ID;

	private String address_Line_1;

	private String address_line_2;

	private String city;

	private String country;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	private String created_by;

	private String institute_Email;

	private String institute_Name;

	private String institute_SPOC;

	private String institute_Website;

	private String state;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	@Column(name="updated_by")
	private String updatedBy;

	private String zip;

	//bi-directional many-to-one association to Membership
	@OneToMany(mappedBy="institute")
	private List<Membership> memberships;

	public Institute() {
	}

	public int getInstitute_ID() {
		return this.institute_ID;
	}

	public void setInstitute_ID(int institute_ID) {
		this.institute_ID = institute_ID;
	}

	public String getAddress_Line_1() {
		return this.address_Line_1;
	}

	public void setAddress_Line_1(String address_Line_1) {
		this.address_Line_1 = address_Line_1;
	}

	public String getAddress_line_2() {
		return this.address_line_2;
	}

	public void setAddress_line_2(String address_line_2) {
		this.address_line_2 = address_line_2;
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

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreated_by() {
		return this.created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getInstitute_Email() {
		return this.institute_Email;
	}

	public void setInstitute_Email(String institute_Email) {
		this.institute_Email = institute_Email;
	}

	public String getInstitute_Name() {
		return this.institute_Name;
	}

	public void setInstitute_Name(String institute_Name) {
		this.institute_Name = institute_Name;
	}

	public String getInstitute_SPOC() {
		return this.institute_SPOC;
	}

	public void setInstitute_SPOC(String institute_SPOC) {
		this.institute_SPOC = institute_SPOC;
	}

	public String getInstitute_Website() {
		return this.institute_Website;
	}

	public void setInstitute_Website(String institute_Website) {
		this.institute_Website = institute_Website;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public List<Membership> getMemberships() {
		return this.memberships;
	}

	public void setMemberships(List<Membership> memberships) {
		this.memberships = memberships;
	}

	public Membership addMembership(Membership membership) {
		getMemberships().add(membership);
		membership.setInstitute(this);

		return membership;
	}

	public Membership removeMembership(Membership membership) {
		getMemberships().remove(membership);
		membership.setInstitute(null);

		return membership;
	}

}