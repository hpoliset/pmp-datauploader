package org.srcm.pmp.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the maturity database table.
 * 
 */
@Entity
@NamedQuery(name="Maturity.findAll", query="SELECT m FROM Maturity m")
public class Maturity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int maturity_ID;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	private String created_by;

	private String maturity_Code;

	private String maturity_Desc;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	@Column(name="updated_by")
	private String updatedBy;

	//bi-directional many-to-one association to Seeker
	@OneToMany(mappedBy="maturity")
	private List<Seeker> seekers;

	public Maturity() {
	}

	public int getMaturity_ID() {
		return this.maturity_ID;
	}

	public void setMaturity_ID(int maturity_ID) {
		this.maturity_ID = maturity_ID;
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

	public String getMaturity_Code() {
		return this.maturity_Code;
	}

	public void setMaturity_Code(String maturity_Code) {
		this.maturity_Code = maturity_Code;
	}

	public String getMaturity_Desc() {
		return this.maturity_Desc;
	}

	public void setMaturity_Desc(String maturity_Desc) {
		this.maturity_Desc = maturity_Desc;
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

	public List<Seeker> getSeekers() {
		return this.seekers;
	}

	public void setSeekers(List<Seeker> seekers) {
		this.seekers = seekers;
	}

	public Seeker addSeeker(Seeker seeker) {
		getSeekers().add(seeker);
		seeker.setMaturity(this);

		return seeker;
	}

	public Seeker removeSeeker(Seeker seeker) {
		getSeekers().remove(seeker);
		seeker.setMaturity(null);

		return seeker;
	}

}