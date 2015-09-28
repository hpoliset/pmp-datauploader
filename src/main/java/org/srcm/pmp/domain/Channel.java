package org.srcm.pmp.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the channel database table.
 * 
 */
@Entity
@NamedQuery(name="Channel.findAll", query="SELECT c FROM Channel c")
public class Channel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int channel_ID;

	private byte active;

	@Lob
	private String channel_Descr;

	private String channel_Lead;

	private String channel_Name;

	private String channel_SPOC;

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
	@OneToMany(mappedBy="channel")
	private List<Membership> memberships;

	public Channel() {
	}

	public int getChannel_ID() {
		return this.channel_ID;
	}

	public void setChannel_ID(int channel_ID) {
		this.channel_ID = channel_ID;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getChannel_Descr() {
		return this.channel_Descr;
	}

	public void setChannel_Descr(String channel_Descr) {
		this.channel_Descr = channel_Descr;
	}

	public String getChannel_Lead() {
		return this.channel_Lead;
	}

	public void setChannel_Lead(String channel_Lead) {
		this.channel_Lead = channel_Lead;
	}

	public String getChannel_Name() {
		return this.channel_Name;
	}

	public void setChannel_Name(String channel_Name) {
		this.channel_Name = channel_Name;
	}

	public String getChannel_SPOC() {
		return this.channel_SPOC;
	}

	public void setChannel_SPOC(String channel_SPOC) {
		this.channel_SPOC = channel_SPOC;
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
		membership.setChannel(this);

		return membership;
	}

	public Membership removeMembership(Membership membership) {
		getMemberships().remove(membership);
		membership.setChannel(null);

		return membership;
	}

}