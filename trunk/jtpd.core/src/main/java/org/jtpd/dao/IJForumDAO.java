/**
 * 
 */
package org.jtpd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author tdiler
 *
 */
public interface IJForumDAO extends IGenericDAO {

	public void changePassword(String emailAddress, String newPassword);

}
