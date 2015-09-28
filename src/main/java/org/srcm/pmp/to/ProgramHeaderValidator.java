package org.srcm.pmp.to;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author MASTER
 *
 */
public class ProgramHeaderValidator implements Serializable {
	private static final long serialVersionUID = 6428666466557471589L;
	private List<String> headers = Arrays.asList("Program Name",
			"Coordinator's name", "Email Id", "Name of the Center", "Country",
			"Website", "Dates of the program", "Name of the Institution");

	private String programName;
	private String coordinatorName;
	private String email;
	private String center;
	private String website;
	private String datesOfProgram;
	private String country;
	private String nameOfInstitute;
	private Map<String, String> map = new HashMap<String, String>();

	private void initialize() {
		map.put(headers.get(0), programName);
		map.put(headers.get(1), coordinatorName);
		map.put(headers.get(2), email);
		map.put(headers.get(3), center);
		map.put(headers.get(4), country);
		map.put(headers.get(5), website);
		map.put(headers.get(6), datesOfProgram);
		map.put(headers.get(7), nameOfInstitute);

	}

	/**
	 * @return the headers
	 */
	public Collection<String> getHeaders() {
		return headers;
	}

	/**
	 * @return the programName
	 */
	public String getProgramName() {
		return programName;
	}

	/**
	 * @param programName
	 *            the programName to set
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}

	/**
	 * @return the coordinatorName
	 */
	public String getCoordinatorName() {
		return coordinatorName;
	}

	/**
	 * @param coordinatorName
	 *            the coordinatorName to set
	 */
	public void setCoordinatorName(String coordinatorName) {
		this.coordinatorName = coordinatorName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the center
	 */
	public String getCenter() {
		return center;
	}

	/**
	 * @param center
	 *            the center to set
	 */
	public void setCenter(String center) {
		this.center = center;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website
	 *            the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * @return the datesOfProgram
	 */
	public String getDatesOfProgram() {
		return datesOfProgram;
	}

	/**
	 * @param datesOfProgram
	 *            the datesOfProgram to set
	 */
	public void setDatesOfProgram(String datesOfProgram) {
		this.datesOfProgram = datesOfProgram;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the nameOfInstitute
	 */
	public String getNameOfInstitute() {
		return nameOfInstitute;
	}

	/**
	 * @param nameOfInstitute
	 *            the nameOfInstitute to set
	 */
	public void setNameOfInstitute(String nameOfInstitute) {
		this.nameOfInstitute = nameOfInstitute;
	}

	/**
	 * @return
	 */
	public List<String> validateHeaders() {
		initialize();
		List<String> errList = new ArrayList<String>();
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			if (map.get(key) == null || !map.get(key).equalsIgnoreCase(key)) {
				errList.add(key);
			}
		}
		return errList;
	}

	/**
	 * @param headerTO
	 * @return
	 */
	public List<String> validateValues(ProgramHeaderTO headerTO) {
		List<String> errList = new ArrayList<String>();
		if (headerTO.getCoordinatorName() == null
				|| headerTO.getCoordinatorName().isEmpty()) {
			errList.add("Coordinator Name is Mandatory");
		}
		if(headerTO.getEmail()!=null){
			if(!headerTO.getEmail().contains("@")){
				errList.add("Email Row is invalid");
			}
		}
		if (headerTO.getChannelName() == null
				|| headerTO.getChannelName().isEmpty()) {
			errList.add("Channel Name is Mandatory");
		}
		if (headerTO.getCenter() == null || headerTO.getCenter().isEmpty()) {
			errList.add("Center is Mandatory");
		}
		if (headerTO.getCountry() == null || headerTO.getCountry().isEmpty()) {
			errList.add("Country is Mandatory");
		}
		if (headerTO.getProgramStartDate() == null
				|| headerTO.getProgramStartDate().isEmpty()) {
			errList.add("ProgramStartDate is Mandatory");
		} else {
			SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
			try {
				format.parse(headerTO.getProgramStartDate());
			} catch (ParseException e) {
				errList.add("ProgramStartDate is invalid date format:dd/MM/yyyy");
			}
		}
		return errList;

	}

}
