/**
 * 
 */
package org.jtpd.services.impl;

import java.util.List;

import org.jtpd.dao.ICommentDAO;
import org.jtpd.dao.IStoryDAO;
import org.jtpd.domain.model.Comment;
import org.jtpd.domain.model.Story;
import org.jtpd.domain.model.User;
import org.jtpd.services.IStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tdiler
 *
 */
@Service
@Transactional
public class StoryService extends GenericService implements IStoryService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8893266793429819537L;
	
	@Autowired ICommentDAO commentDAO;
	@Autowired IStoryDAO storyDAO;
	
	public List<Comment> getApprovedCommentsOfStory(Story story){
		return commentDAO.getApprovedComments(story);
	}

	public Story getStory(Integer storyId) {
		return storyDAO.find(storyId);
	}

	public List<Story> getStories(User user) {
		return storyDAO.listMyStories(user);
	}

	public void delete(Story story) {
		storyDAO.remove(story);
	}
}
