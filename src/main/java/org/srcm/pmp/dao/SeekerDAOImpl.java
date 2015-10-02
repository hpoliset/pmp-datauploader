package org.srcm.pmp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.srcm.pmp.domain.Program;
import org.srcm.pmp.domain.SeekerAim;
import org.srcm.pmp.domain.SeekerAims;

@Repository
public class SeekerDAOImpl implements SeekerDAO{
	@Autowired
	private SessionFactory sessionFactory;
	public SeekerDAOImpl(){
	}
	@Override
	public void saveOrUpdate(Program program) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(program);
	}
	
	@Override
	public void saveOrUpdate(SeekerAims aims) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(aims);
	}

	@Override
	public SeekerAim findByID(Long seekerId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria createCriteria = currentSession.createCriteria(SeekerAim.class);
		createCriteria.add(Restrictions.eq("seeker_ID", seekerId));
		return (SeekerAim)createCriteria.uniqueResult();
	}
	@Override
	public SeekerAim findByEmail(String email) {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria createCriteria = currentSession.createCriteria(SeekerAim.class);
		createCriteria.add(Restrictions.eq("email", email));
		return (SeekerAim)createCriteria.uniqueResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Program> findProgram(String programName,String coordName) {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria createCriteria = currentSession.createCriteria(Program.class)
			      .add(Restrictions.eq("programChannel", programName))
			      .add(Restrictions.eqOrIsNull("coordName", coordName));
				 // .add(Restrictions.("programDate",dateOfProgram));
			    //.setResultTransformer(Transformers.aliasToBean(Program.class));

		return createCriteria.list();
	}
     
}
