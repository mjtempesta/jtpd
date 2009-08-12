package org.jtpd.dao;

import java.util.List;




import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jtpd.data.Activities;
import org.jtpd.data.ActivityCustomer;
import org.jtpd.data.Customer;
import org.jtpd.data.ListObject;
import org.jtpd.data.ListWrapper;
import org.jtpd.data.SubList;
import org.jtpd.db.HibernateSessionFactory;

public class ListEngine {

	public ListWrapper getAllActivities() throws Exception {
		Session session = null;
		

		try {
			session = HibernateSessionFactory.openSession();
			
			Query query = session.createQuery("select ac from Activities ac order by ac.startDate");
			List<Activities> list = query.list();

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
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
