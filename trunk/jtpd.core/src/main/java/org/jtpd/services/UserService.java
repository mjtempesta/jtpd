/**
 * 
 */
package org.jtpd.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jtpd.dao.IJForumDAO;
import org.jtpd.dao.IUserDAO;
import org.jtpd.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tdiler
 *
 */
@Component
public class UserService extends GenericService implements IUserService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6938803698029526121L;
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IJForumDAO jforumDAO;
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
