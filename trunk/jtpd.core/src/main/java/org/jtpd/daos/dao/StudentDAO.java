/**
 * 
 */
package org.jtpd.daos.dao;

import org.jtpd.daos.idao.IStudentDAO;
import org.jtpd.domain.model.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tdiler
 *
 */
@Transactional
@Repository
public class StudentDAO extends GenericDAO<Integer, Student> implements IStudentDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8430010488487895383L;

}
