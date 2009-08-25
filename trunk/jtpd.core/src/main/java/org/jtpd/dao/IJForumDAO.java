/**
 * 
 */
package org.jtpd.dao;


/**
 * @author tdiler
 *
 */
public interface IJForumDAO extends IGenericDAO {

	public void changePassword(String emailAddress, String newPassword);

}
