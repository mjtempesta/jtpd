/**
 * 
 */
package org.jtpd.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jtpd.domain.model.ActivityCustomer;

/**
 * @author tdiler
 *
 */
public interface IActivityCustomerDAO extends IJTPDGenericDAO<Integer, ActivityCustomer> {

	@SuppressWarnings("unchecked")
	public List<ActivityCustomer> getAttendeeList(Integer activityId);

}
