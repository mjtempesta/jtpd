/**
 * 
 */
package org.jtpd.daos.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jtpd.daos.idao.IGenericDAO;
import org.jtpd.domain.model.GenericModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tdiler
 *
 */
@Transactional
@Repository
public abstract class GenericDAO<PK extends Serializable, M extends GenericModel<PK>> implements IGenericDAO<PK, M> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6004201354374961844L;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Class<M> clazz;
	
	@SuppressWarnings("unchecked")
	public GenericDAO(){
		this.clazz = (Class<M>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Session getSession(){
		return this.sessionFactory.openSession();
	}
	
	public Class<M> getClazz(){
        return this.clazz;
	}
	
	@SuppressWarnings("unchecked")
	public M find(PK pk) {
		return (M)this.getSession().get(this.getClazz(), pk);
	}

	public List<M> findByCriteria(Criteria criteria) {
		return null;
	}

	public void remove(M model) {
		this.getSession().delete(model);		
	}

	public PK saveOrUpdate(M model) {
		this.getSession().persist(model);
		return model.getPK();
	}

}
