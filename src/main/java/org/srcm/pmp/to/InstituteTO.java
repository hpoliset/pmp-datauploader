/**
 * 
 */
package org.srcm.pmp.to;

/**
 * @author MASTER
 *
 */
public class InstituteTO implements TransferObject {
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  private String instituteName;
  private Long instituteId;
  private String website;
/**
 * @return the instituteName
 */
public String getInstituteName() {
	return instituteName;
}
/**
 * @param instituteName the instituteName to set
 */
public void setInstituteName(String instituteName) {
	this.instituteName = instituteName;
}
/**
 * @return the instituteId
 */
public Long getInstituteId() {
	return instituteId;
}
/**
 * @param instituteId the instituteId to set
 */
public void setInstituteId(Long instituteId) {
	this.instituteId = instituteId;
}
/**
 * @return the website
 */
public String getWebsite() {
	return website;
}
/**
 * @param website the website to set
 */
public void setWebsite(String website) {
	this.website = website;
}
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "InstituteTO [instituteName=" + instituteName + ", instituteId="
			+ instituteId + ", website=" + website + "]";
}

}
