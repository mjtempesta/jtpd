/**
 * 
 */
package org.jtpd.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tdiler
 *
 */
@Transactional
@Repository
public class GenericDAO implements IGenericDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession(){
		return this.sessionFactory.openSession();
	}
}
