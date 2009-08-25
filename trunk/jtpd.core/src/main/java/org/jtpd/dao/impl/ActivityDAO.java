/**
 * 
 */
package org.jtpd.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jtpd.dao.IActivityDAO;
import org.jtpd.domain.model.Activities;
import org.jtpd.util.DateUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tdiler
 *
 */
@Repository
@Transactional
public class ActivityDAO extends JTPDGenericDAO<Integer, Activities> implements
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
	
	@SuppressWarnings("unchecked")
	public List<Activities> getActivityList(int trainingCode, int cityCode, int monthOfTheyear) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		Criteria c =  createCriteria();
		
		if ((trainingCode == 0) && (cityCode == 0)) {
			// it means that user has just enter the page so list all related activities
			String startDate = year + "-" + 
				DateUtils.convertToWantedMonth(Calendar.getInstance().get(Calendar.MONTH)+1)  ;
			c.add( Restrictions.ge("startDate", startDate));
			c.addOrder(Order.asc("startDate"));
			List<Activities> results = c.list() ;
			return results ;
		}
		
		String startDate =  year + "-" + DateUtils.convertToWantedMonth(monthOfTheyear)  ;
		
		if (trainingCode != -1) {
			c.createCriteria("trainingNames").add(Restrictions.eq("id", trainingCode));
		} 
		if (monthOfTheyear != -1) {
			c.add( Restrictions.like("startDate", "%"+startDate+"%"));
		}
		if (cityCode != -1){
			c.add( Restrictions.eq("location", new String(cityCode+"")));
		}			
			
		c.addOrder(Order.asc("startDate"));
		List<Activities> results = c.list() ;  
		
		return results ;
		
	}

}
