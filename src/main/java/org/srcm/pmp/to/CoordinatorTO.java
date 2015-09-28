/**
 * 
 */
package org.srcm.pmp.to;


/**
 * @author MASTER
 *
 */
public class CoordinatorTO implements TransferObject {
	private static final long serialVersionUID = -4680868757000209616L;
   private String coordinatorName;
   private String emailId;
   private String center;
   private String country;
   
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "CoordinatorTO [coordinatorName=" + coordinatorName + ", emailId="
			+ emailId + ", center=" + center + ", country=" + country + "]";
}
/**
 * @return the coordinatorName
 */
public String getCoordinatorName() {
	return coordinatorName;
}
/**
 * @param coordinatorName the coordinatorName to set
 */
public void setCoordinatorName(String coordinatorName) {
	this.coordinatorName = coordinatorName;
}
/**
 * @return the emailId
 */
public String getEmailId() {
	return emailId;
}
/**
 * @param emailId the emailId to set
 */
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
/**
 * @return the center
 */
public String getCenter() {
	return center;
}
/**
 * @param center the center to set
 */
public void setCenter(String center) {
	this.center = center;
}
/**
 * @return the country
 */
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
