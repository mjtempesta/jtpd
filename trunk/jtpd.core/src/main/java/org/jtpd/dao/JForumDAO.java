/**
 * 
 */
package org.jtpd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tdiler
 *
 */
@Transactional
@Repository
public class JForumDAO extends GenericDAO implements IJForumDAO {
	private Logger logger = Logger.getLogger(JForumDAO.class);
	
	/* (non-Javadoc)
	 * @see org.jtpd.dao.IJForumDAO#changePassword(java.lang.String, java.lang.String)
	 */
	public void changePassword(String emailAddress, String newPassword) {
		this.getSession().doWork(new ChangePasswordWork(emailAddress, newPassword));
	}

	private class ChangePasswordWork implements Work {
		private String newPassword;
		private String emailAddress;
		
		public ChangePasswordWork(String emailAddress, String newPassword){
			this.emailAddress = emailAddress;
			this.newPassword = newPassword;
		}
		
		/* (non-Javadoc)
		 * @see org.hibernate.jdbc.Work#execute(java.sql.Connection)
		 */
		public void execute(Connection connection) throws SQLException {
			// TODO IMPORTANT : burada ayný mail adresine sahip birden fazla
			// kullanýcý olabilir
			PreparedStatement ps = connection
					.prepareStatement("update jforum.jforum_users set user_password=MD5(?) where user_email=?");

			ps.setString(1, newPassword);
			ps.setString(2, emailAddress);

			boolean sonuc1 = ps.execute();
			if (sonuc1) {
				if (logger.isDebugEnabled()) {
					logger.debug("Password has been changed for user with "
							+ emailAddress + " email.");
				}
			} else {
				logger.warn("Problem on changing the password of with "
						+ emailAddress + " email.");
			}
			
		}
		
	}
}
