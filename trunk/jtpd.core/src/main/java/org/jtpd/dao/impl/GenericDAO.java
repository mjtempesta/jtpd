/**
 * 
 */
package org.jtpd.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jtpd.dao.IGenericDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tdiler
 *
 */
@Repository
@Transactional
public class GenericDAO implements IGenericDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
}
