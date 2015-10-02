package org.srcm.pmp.dao;

import java.util.List;

import org.srcm.pmp.domain.Program;
import org.srcm.pmp.domain.SeekerAim;
import org.srcm.pmp.domain.SeekerAims;

/**
 * @author MASTER
 *
 */
public interface SeekerDAO {
	
	public void saveOrUpdate(Program program);
	
	public void saveOrUpdate(SeekerAims aims);
	
	public SeekerAim findByID(Long  seekerId);
	
	public SeekerAim findByEmail(String email);
	
	public List<Program> findProgram(String programName,String coorName);
	
}
