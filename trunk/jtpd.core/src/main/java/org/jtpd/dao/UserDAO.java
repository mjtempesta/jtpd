/**
 * 
 */
package org.jtpd.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jtpd.domain.model.User;

/**
 * @author tdiler
 *
 */
public class UserDAO extends JTPDGenericDAO<Integer,User> implements IUserDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2949953572084362868L;

	public List<User> listByEmail(String emailAddress) {
		Criteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("emailAddress", emailAddress));
		return this.findByCriteria(criteria);
	}

}
