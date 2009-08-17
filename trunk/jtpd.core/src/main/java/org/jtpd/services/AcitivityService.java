/**
 * 
 */
package org.jtpd.services;

import java.util.List;

import org.jtpd.dao.IActivityCustomerDAO;
import org.jtpd.dao.IActivityDAO;
import org.jtpd.domain.model.Activities;
import org.jtpd.domain.model.ActivityCustomer;
import org.jtpd.domain.model.Customer;
import org.jtpd.domain.model.ListObject;
import org.jtpd.domain.model.ListWrapper;
import org.jtpd.domain.model.SubList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tdiler
 *
 */
@Service
public class AcitivityService extends GenericService implements
		IActivityService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5125875227720006523L;
	
	@Autowired IActivityDAO activityDAO;
	
	@Autowired IActivityCustomerDAO activityCustomerDAO;
	
	public ListWrapper getAllActivities() throws Exception {
			List<Activities> list = activityDAO.getActivityList();
			ListWrapper listObjects = new ListWrapper();
			for (Activities ac : list) {
				ListObject lo = new ListObject();
				lo.setId(ac.getId());
				lo.setStartDate(ac.getStartDate());
				lo.setEndDate(ac.getEndDate());
				lo.setOwner(ac.getOwner());
				lo.setTrainingName(ac.getTrainingNames().getActivityName());
				List<ActivityCustomer> activityCustomerS = ac
						.getActivityCustomer();
				for (ActivityCustomer activityCustomer : activityCustomerS) {
					Customer c = activityCustomer.getCustomer();
					SubList sl = new SubList();
					sl.setName(c.getName());
					sl.setSurname(c.getSurname());
					sl.setEmail(c.getEmail());
					sl.setPhone(c.getPhone());
					sl.setCellPhone(c.getCellphone());
					sl.setComment(c.getComment());
					lo.addSubList(sl);
				}
				listObjects.add(lo);
			}
			return listObjects;
	}
	
	
	public List<ActivityCustomer> getAttendeeList(Integer activityId){
		return activityCustomerDAO.getAttendeeList(activityId);
	}
	
	
	public List<Activities> getActivities(int trainingCode, int cityCode, int monthOfTheyear) {
		return activityDAO.getActivityList(trainingCode, cityCode, monthOfTheyear);
	}


}
