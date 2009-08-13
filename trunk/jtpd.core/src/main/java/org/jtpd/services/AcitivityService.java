/**
 * 
 */
package org.jtpd.services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jtpd.dao.IActivityDAO;
import org.jtpd.domain.model.Activities;
import org.jtpd.domain.model.ActivityCustomer;
import org.jtpd.domain.model.Customer;
import org.jtpd.domain.model.ListObject;
import org.jtpd.domain.model.ListWrapper;
import org.jtpd.domain.model.SubList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tdiler
 *
 */
@Component
public class AcitivityService extends GenericService implements
		IActivityService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5125875227720006523L;
	
	@Autowired
	private IActivityDAO activityDAO;
	
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

}
