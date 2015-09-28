package org.srcm.pmp.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the memberships database table.
 * 
 */
@Entity
@Table(name="memberships")
@NamedQuery(name="Membership.findAll", query="SELECT m FROM Membership m")
public class Membership implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int membership_ID;

	private String attribute1;

	private String attribute2;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	private String created_by;

	@Temporal(TemporalType.DATE)
	private Date end_Date;

	private String future;

	private byte introduced;

	private String introduced_By;

	@Temporal(TemporalType.DATE)
	private Date introduced_date;

	private byte primary;

	@Temporal(TemporalType.DATE)
	private Date program_End_Date;

	@Temporal(TemporalType.DATE)
	private Date program_Start_Date;

	private String remarks;

	@Temporal(TemporalType.DATE)
	private Date start_Date;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	@Column(name="updated_by")
	private String updatedBy;

	//bi-directional many-to-one association to Channel
	@ManyToOne
	@JoinColumn(name="Channel_ID")
	private Channel channel;

	//bi-directional many-to-one association to Institute
	@ManyToOne
	@JoinColumn(name="Institute_ID")
	private Institute institute;

	//bi-directional many-to-one association to Seeker
	@ManyToOne
	@JoinColumn(name="Seeker_ID")
	private Seeker seeker;

	//bi-directional many-to-one association to Coordinator
	@ManyToOne
	@JoinColumn(name="Coord_ID")
	private Coordinator coordinator;

	public Membership() {
	}

	public int getMembership_ID() {
		return this.membership_ID;
	}

	public void setMembership_ID(int membership_ID) {
		this.membership_ID = membership_ID;
	}

	public String getAttribute1() {
		return this.attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return this.attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
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

	public Date getEnd_Date() {
		return this.end_Date;
	}

	public void setEnd_Date(Date end_Date) {
		this.end_Date = end_Date;
	}

	public String getFuture() {
		return this.future;
	}

	public void setFuture(String future) {
		this.future = future;
	}

	public byte getIntroduced() {
		return this.introduced;
	}

	public void setIntroduced(byte introduced) {
		this.introduced = introduced;
	}

	public String getIntroduced_By() {
		return this.introduced_By;
	}

	public void setIntroduced_By(String introduced_By) {
		this.introduced_By = introduced_By;
	}

	public Date getIntroduced_date() {
		return this.introduced_date;
	}

	public void setIntroduced_date(Date introduced_date) {
		this.introduced_date = introduced_date;
	}

	public byte getPrimary() {
		return this.primary;
	}

	public void setPrimary(byte primary) {
		this.primary = primary;
	}

	public Date getProgram_End_Date() {
		return this.program_End_Date;
	}

	public void setProgram_End_Date(Date program_End_Date) {
		this.program_End_Date = program_End_Date;
	}

	public Date getProgram_Start_Date() {
		return this.program_Start_Date;
	}

	public void setProgram_Start_Date(Date program_Start_Date) {
		this.program_Start_Date = program_Start_Date;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getStart_Date() {
		return this.start_Date;
	}

	public void setStart_Date(Date start_Date) {
		this.start_Date = start_Date;
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

	public Channel getChannel() {
		return this.channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Institute getInstitute() {
		return this.institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public Seeker getSeeker() {
		return this.seeker;
	}

	public void setSeeker(Seeker seeker) {
		this.seeker = seeker;
	}

	public Coordinator getCoordinator() {
		return this.coordinator;
	}

	public void setCoordinator(Coordinator coordinator) {
		this.coordinator = coordinator;
	}

}