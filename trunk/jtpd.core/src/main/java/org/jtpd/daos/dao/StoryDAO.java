/**
 * 
 */
package org.jtpd.daos.dao;

import org.jtpd.daos.idao.IStoryDAO;
import org.jtpd.domain.model.Story;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tdiler
 *
 */
@Transactional
@Repository
public class StoryDAO extends GenericDAO<Integer, Story> implements IStoryDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8003570149174692117L;

}
