/**
 * 
 */
package org.jtpd.daos.dao;

import org.jtpd.daos.idao.IUserDAO;
import org.jtpd.domain.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tdiler
 *
 */
@Transactional
@Repository
public class UserDAO extends GenericDAO<Integer, User> implements IUserDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3940880727572086516L;

}
