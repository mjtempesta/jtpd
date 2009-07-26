/**
 * 
 */
package org.jtpd.daos.dao;

import org.jtpd.daos.idao.ITeacherDAO;
import org.jtpd.domain.model.Teacher;
import org.springframework.stereotype.Repository;

/**
 * @author tdiler
 *
 */
@Repository
public class TeacherDAO extends GenericDAO<Integer, Teacher> implements ITeacherDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6300679679438175743L;

}
