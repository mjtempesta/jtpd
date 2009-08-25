/**
 * 
 */
package org.jtpd.services.impl;

import java.util.List;

import org.jtpd.dao.IJForumDAO;
import org.jtpd.dao.IUserDAO;
import org.jtpd.domain.model.User;
import org.jtpd.services.IUserService;
import org.jtpd.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tdiler
 *
 */
@Service
public class UserService extends GenericService implements IUserService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6938803698029526121L;
	
	@Autowired IUserDAO userDAO;
	
	@Autowired IJForumDAO jforumDAO;
	/**
	 * This method is being called by Thread so we must close the Session
	 * 
	 * @param emailAddress
	 * @return
	 */
	public boolean canIsendEmail(String emailAddress) {
		if (emailAddress == null || emailAddress.length() == 0) {
			return false;
		}
		boolean sendMail = false;
		List<User> userList = userDAO.listByEmail(emailAddress);
		if (userList != null) {
			if (userList.size() > 0) {
				// TODO IMPORTANT : Burada birden fazla user gelebilir, riskli bir durum
				User user = userList.get(0);
				if (user.getCanSendEmail() == Constants.DONTSENDEMAIL) {
					sendMail = false; // don't send email
				} else {
					sendMail = true; // send email
				}
			} else {
				sendMail = true; // send email
			}
		} else {
			sendMail = false; // // don't send email
		}
		return sendMail;
	}

	public String getUsername(String emailAddress) {
		List<User> userList = userDAO.listByEmail(emailAddress);
		if (userList != null) {
			if (userList.size() > 0) {
				// TODO IMPORTANT : Burada birden fazla user gelebilir, riskli bir durum
				User user = userList.get(0);
				return user.getUsername();
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	public void setEmailGettingChoice(User user, int choice) {
		User freshedUser = userDAO.find(user.getId());
		if (Constants.SENDEMAIL == choice || Constants.DONTSENDEMAIL == choice) {
			freshedUser.setCanSendEmail(choice);
			user.setCanSendEmail(choice);
		}
		userDAO.saveOrUpdate(freshedUser);
	}

	public void changePassword(String emailAddress, String newPassword) {
		jforumDAO.changePassword(emailAddress, newPassword);
	}

}
