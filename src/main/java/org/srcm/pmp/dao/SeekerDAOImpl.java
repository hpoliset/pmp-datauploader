package org.srcm.pmp.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.srcm.pmp.domain.SeekerAim;

@Repository
public class SeekerDAOImpl implements SeekerDAO{
	@Autowired
	private SessionFactory sessionFactory;
	public SeekerDAOImpl(){
	}
	@Override
	public void saveOrUpdate(SeekerAim seeker) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(seeker);
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
     
}
