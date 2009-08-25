/**
 * 
 */
package org.jtpd.services;

import java.util.List;

import org.jtpd.domain.model.Activities;
import org.jtpd.domain.model.ActivityCustomer;
import org.jtpd.domain.model.ListWrapper;

/**
 * @author tdiler
 *
 */
public interface IActivityService extends IGenericService {

	public List<Activities> getActivities(int trainingCode, int cityCode, int monthOfTheyear);

	public ListWrapper getAllActivities() throws Exception;

	public List<ActivityCustomer> getAttendeeList(Integer activityId);

}
