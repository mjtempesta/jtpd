/**
 * 
 */
package org.jtpd.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.jtpd.domain.model.GenericModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tdiler
 *
 */
@Transactional
@Repository
public abstract class JTPDGenericDAO<PK extends Serializable, M extends GenericModel<PK>> extends GenericDAO implements IJTPDGenericDAO<PK, M> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6004201354374961844L;
	
	private Class<M> clazz;
	
	@SuppressWarnings("unchecked")
	public JTPDGenericDAO(){
		this.clazz = (Class<M>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public Class<M> getClazz(){
        return this.clazz;
	}
	
	@SuppressWarnings("unchecked")
	public M find(PK pk) {
		return (M)this.getSession().load(this.getClazz(), pk);
	}

	@SuppressWarnings("unchecked")
	public List<M> findByCriteria(Criteria criteria) {
		return criteria.list();
	}

	public void remove(M model) {
		this.getSession().delete(model);		
	}

	public PK saveOrUpdate(M model) {
		this.getSession().persist(model);
		return model.getId();
	}
	
	/**
	 * @return
	 */
	protected Criteria createCriteria() {
		Criteria criteria = this.getSession().createCriteria(this.getClazz());
		return criteria;
	}

}
