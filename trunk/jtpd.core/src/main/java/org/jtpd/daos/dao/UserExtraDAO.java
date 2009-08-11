/**
 * 
 */
package org.jtpd.daos.dao;

import org.jtpd.daos.idao.IUserExtraDAO;
import org.jtpd.domain.model.UserExtra;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tdiler
 *
 */
@Transactional
@Repository
public class UserExtraDAO extends GenericDAO<Integer, UserExtra> implements IUserExtraDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6052819343025897720L;


}
