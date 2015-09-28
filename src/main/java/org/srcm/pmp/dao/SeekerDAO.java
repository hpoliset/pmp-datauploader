package org.srcm.pmp.dao;

import org.srcm.pmp.domain.SeekerAim;

/**
 * @author MASTER
 *
 */
public interface SeekerDAO {
	/**
	 * @param seeker
	 */
	public void saveOrUpdate(SeekerAim seeker);
	/**
	 * @param seekerId
	 */
	public SeekerAim findByID(Long  seekerId);
	
	
	/**
	 * @param email
	 * @return
	 */
	public SeekerAim findByEmail(String email);
	
}
