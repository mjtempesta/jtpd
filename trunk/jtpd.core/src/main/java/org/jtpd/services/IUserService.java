/**
 * 
 */
package org.jtpd.services;

import java.util.List;

import org.jtpd.domain.model.User;

/**
 * @author tdiler
 *
 */
public interface IUserService extends IGenericService {

	/**
	 * This method is being called by Thread so we must close the Session
	 * 
	 * @param emailAddress
	 * @return
	 */
	public boolean canIsendEmail(String emailAddress);

	public String getUsername(String emailAddress);

	public void setEmailGettingChoice(User user, int choice);

	public void changePassword(String emailAddress, String newPassword);

}
