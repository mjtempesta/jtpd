/**
 * 
 */
package org.jtpd.dao;

import java.util.List;

import org.jtpd.domain.model.ActivityCustomer;

/**
 * @author tdiler
 *
 */
public interface IActivityCustomerDAO extends IJTPDGenericDAO<Integer, ActivityCustomer> {

	public List<ActivityCustomer> getAttendeeList(Integer activityId);

}
