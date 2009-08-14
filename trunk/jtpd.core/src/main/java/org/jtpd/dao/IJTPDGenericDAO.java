/**
 * 
 */
package org.jtpd.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.jtpd.domain.model.GenericModel;

/**
 * @author tdiler
 *
 */
public interface IJTPDGenericDAO<PK extends Serializable, M extends GenericModel<PK>> extends IGenericDAO, Serializable {
	
	public M find(PK id);
	
	public List<M> findByCriteria(Criteria criteria);
	
	public PK saveOrUpdate(M model);
	
	public void remove(M model);
}
