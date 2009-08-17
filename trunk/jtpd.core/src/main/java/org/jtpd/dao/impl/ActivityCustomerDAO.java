/**
 * 
 */
package org.jtpd.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jtpd.dao.IActivityCustomerDAO;
import org.jtpd.domain.model.ActivityCustomer;

/**
 * @author tdiler
 *
 */
public class ActivityCustomerDAO extends JTPDGenericDAO<Integer, ActivityCustomer> implements
		IActivityCustomerDAO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5455207123072100167L;

	@SuppressWarnings("unchecked")
	public List<ActivityCustomer> getAttendeeList(Integer activityId) {
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("activities.id", activityId));
		return (List<ActivityCustomer>) criteria.list();
	}
}
