package org.srcm.pmp.dao;

import org.srcm.pmp.domain.Program;
import org.srcm.pmp.domain.SeekerAim;
import org.srcm.pmp.domain.SeekerAims;

/**
 * @author MASTER
 *
 */
public interface SeekerDAO {
	/**
	 * @param seeker
	 */
	public void saveOrUpdate(Program program);
	
	/**
	 * @param seeker
	 */
	public void saveOrUpdate(SeekerAims aims);
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
