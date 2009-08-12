/**
 * 
 */
package org.jtpd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jtpd.data.User;
import org.jtpd.db.HibernateSessionFactory;
import org.jtpd.util.Constants;

/**
 * @author altuga
 * 
 */
public class UserEngine {

	private Logger logger = Logger.getLogger("appLogger");

	/**
	 * This method is being called by Thread so we must close the Session
	 * 
	 * @param emailAddress
	 * @return
	 */
	public static boolean canIsendEmail(String emailAddress) {

		if (emailAddress == null || emailAddress.length() == 0) {
			return false;
		}
		try {
			Session session = null;

			session = HibernateSessionFactory.openSession();
			Query query = session
					.createQuery("select user from User user where user.emailAddress =:emailAddress");
			query.setString("emailAddress", emailAddress);
			List<User> result = query.list();

			if (result != null) {
				if (result.size() > 0) {
					User user = result.get(0);
					if (user.getCanSendEmail() == Constants.DONTSENDEMAIL) {
						return false; // don't send email
					} else {
						return true; // send email
					}
				} else {
					return true; // send email
				}
			} else {
				return false; // // don't send email
			}
		} catch (Exception ex) {
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public String getUsername(String emailAddress) {
		Session session = null;

		session = HibernateSessionFactory.openSession();
		Query query = session
				.createQuery("select user from User user where user.emailAddress =:emailAddress");
		query.setString("emailAddress", emailAddress);
		List<User> result = query.list();
		if (result != null) {
			if (result.size() > 0) {
				User user = result.get(0);
				return user.getUsername();
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	public void setEmailGettingChoice(User user, int choice) {
		Session session = null;

		session = HibernateSessionFactory.openSession();
		HibernateSessionFactory.beginTransaction();
		User freshedUser = (User) session.get(User.class, user.getId());
		if (Constants.SENDEMAIL == choice || Constants.DONTSENDEMAIL == choice) {
			freshedUser.setCanSendEmail(choice);
			user.setCanSendEmail(choice);
		}
		session.saveOrUpdate(freshedUser);
	}

	public void changePassword(String emailaddress, String newPassword) {
		Session session = null;
		Transaction tx = null;
		Connection con = null ;
		try {
			con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/jforum?user=jforum&password=hebelek");

			PreparedStatement ps = con
					.prepareStatement("update jforum_users set user_password=MD5(?) where user_email=?");

			ps.setString(1, newPassword);
			ps.setString(2, emailaddress);
			
			// session = HibernateSessionFactory.openJforumSession();
			if (logger.isDebugEnabled()) {
				logger.debug("   session is opened ");
			}
			
			//boolean sonuc = ps.execute();
			boolean sonuc2 = ps.execute();
			
			// net.jforum.util.MD5.crypt(str)
			// tx = session.beginTransaction();
			// SQLQuery query = session.createSQLQuery("update jforum_users set
			// user_password=MD5(:password) where user_email=:emailAddress ");
			// query.setString("emailAddress", emailaddress);
			// query.setString("password", newPassword);
			// int result = query.executeUpdate();
			if (logger.isDebugEnabled()) {
				logger.debug("   updated is executed " +  " " + sonuc2);
			}

		} catch (Exception ex) {
			logger.error(" " + ex) ;
		} finally {
			try {				
				con.close();
				if (logger.isDebugEnabled()) {
					logger.debug("   connection  is closed ");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error(" " + e) ;
				
			}
		}
	}

}
