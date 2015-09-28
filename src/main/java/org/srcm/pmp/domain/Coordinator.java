package org.srcm.pmp.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the coordinator database table.
 * 
 */
@Entity
@NamedQuery(name="Coordinator.findAll", query="SELECT c FROM Coordinator c")
public class Coordinator implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int coord_ID;

	private String coord_Center_Name;

	private String coord_Email;

	private String coord_Loc_Address_1;

	private String coord_Loc_Address_2;

	private String coord_Loc_City;

	private String coord_Loc_Country;

	private String coord_Loc_State;

	private String coord_Loc_Zip;

	private String coord_Name;

	private String coord_Phone;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	private String created_by;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	@Column(name="updated_by")
	private String updatedBy;

	//bi-directional many-to-one association to Membership
	@OneToMany(mappedBy="coordinator")
	private List<Membership> memberships;

	public Coordinator() {
	}

	public int getCoord_ID() {
		return this.coord_ID;
	}

	public void setCoord_ID(int coord_ID) {
		this.coord_ID = coord_ID;
	}

	public String getCoord_Center_Name() {
		return this.coord_Center_Name;
	}

	public void setCoord_Center_Name(String coord_Center_Name) {
		this.coord_Center_Name = coord_Center_Name;
	}

	public String getCoord_Email() {
		return this.coord_Email;
	}

	public void setCoord_Email(String coord_Email) {
		this.coord_Email = coord_Email;
	}

	public String getCoord_Loc_Address_1() {
		return this.coord_Loc_Address_1;
	}

	public void setCoord_Loc_Address_1(String coord_Loc_Address_1) {
		this.coord_Loc_Address_1 = coord_Loc_Address_1;
	}

	public String getCoord_Loc_Address_2() {
		return this.coord_Loc_Address_2;
	}

	public void setCoord_Loc_Address_2(String coord_Loc_Address_2) {
		this.coord_Loc_Address_2 = coord_Loc_Address_2;
	}

	public String getCoord_Loc_City() {
		return this.coord_Loc_City;
	}

	public void setCoord_Loc_City(String coord_Loc_City) {
		this.coord_Loc_City = coord_Loc_City;
	}

	public String getCoord_Loc_Country() {
		return this.coord_Loc_Country;
	}

	public void setCoord_Loc_Country(String coord_Loc_Country) {
		this.coord_Loc_Country = coord_Loc_Country;
	}

	public String getCoord_Loc_State() {
		return this.coord_Loc_State;
	}

	public void setCoord_Loc_State(String coord_Loc_State) {
		this.coord_Loc_State = coord_Loc_State;
	}

	public String getCoord_Loc_Zip() {
		return this.coord_Loc_Zip;
	}

	public void setCoord_Loc_Zip(String coord_Loc_Zip) {
		this.coord_Loc_Zip = coord_Loc_Zip;
	}

	public String getCoord_Name() {
		return this.coord_Name;
	}

	public void setCoord_Name(String coord_Name) {
		this.coord_Name = coord_Name;
	}

	public String getCoord_Phone() {
		return this.coord_Phone;
	}

	public void setCoord_Phone(String coord_Phone) {
		this.coord_Phone = coord_Phone;
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

	public List<Membership> getMemberships() {
		return this.memberships;
	}

	public void setMemberships(List<Membership> memberships) {
		this.memberships = memberships;
	}

	public Membership addMembership(Membership membership) {
		getMemberships().add(membership);
		membership.setCoordinator(this);

		return membership;
	}

	public Membership removeMembership(Membership membership) {
		getMemberships().remove(membership);
		membership.setCoordinator(null);

		return membership;
	}

}