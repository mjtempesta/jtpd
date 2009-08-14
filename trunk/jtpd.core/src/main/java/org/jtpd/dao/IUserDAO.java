/**
 * 
 */
package org.jtpd.dao;

import java.util.List;

import org.jtpd.domain.model.User;

/**
 * @author tdiler
 *
 */
public interface IUserDAO extends IJTPDGenericDAO<Integer, User> {

	List<User> listByEmail(String emailAddress);

}
