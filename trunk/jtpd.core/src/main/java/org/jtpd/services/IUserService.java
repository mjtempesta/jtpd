/**
 * 
 */
package org.jtpd.services;

import org.jtpd.domain.model.Story;
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

	public void writeAStory(Integer userId, Story story);

	public User findUser(Integer userId);

}
