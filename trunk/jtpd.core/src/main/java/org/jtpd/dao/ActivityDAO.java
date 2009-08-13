/**
 * 
 */
package org.jtpd.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.jtpd.domain.model.Activities;

/**
 * @author tdiler
 *
 */
public class ActivityDAO extends GenericDAO<Integer, Activities> implements
		IActivityDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5524674119790161606L;

	/* (non-Javadoc)
	 * @see org.jtpd.dao.IActivityDAO#getActivityList()
	 */
	@SuppressWarnings("unchecked")
	public List<Activities> getActivityList() {
		Criteria criteria = createCriteria();
		criteria.addOrder(Order.desc("startDate"));
		return criteria.list();
	}


}
