/**
 * 
 */
package org.jtpd.services.impl;

import java.util.List;

import org.jtpd.dao.ICommentDAO;
import org.jtpd.dao.IStoryDAO;
import org.jtpd.domain.model.Comment;
import org.jtpd.domain.model.Story;
import org.jtpd.domain.model.StoryStatus;
import org.jtpd.domain.model.User;
import org.jtpd.exception.InvalidStoryStatusException;
import org.jtpd.services.IStoryService;
import org.jtpd.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
	
	/* (non-Javadoc)
	 * @see org.jtpd.services.IStoryService#sendToAdmin(org.jtpd.domain.model.Story)
	 */
	public void sendToAdmin(Story story) throws InvalidStoryStatusException {
		StoryStatus status = this.determineWhichStatus(story);
		if(!StoryStatus.EDITING.equals(status)){
			throw new InvalidStoryStatusException("The story[Status:"+status.name()+"] must be on editing status to send story to admin.");
		}
		story.setIsOnline(Constants.OFFLINE_DONE);
		story.setAdminId(Constants.NO_ADMIN_YET);
		storyDAO.saveOrUpdate(story);
	}
	
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
	
	@Transactional(propagation = Propagation.NEVER)
	public StoryStatus determineWhichStatus(Story story) {
		if (story.getIsOnline() == Constants.ONLINE) {
			return StoryStatus.PUBLISHED;
		} else if (story.getIsOnline() == Constants.REJECT) {
			return StoryStatus.REJECTED;
		} else if (story.getIsOnline() == Constants.OFFLINE_DONE
				&& story.getAdminId() == Constants.NO_ADMIN_YET) {
			return StoryStatus.WAITING;
		} else if (story.getIsOnline() == Constants.OFFLINE_DONE
				&& story.getAdminId() != Constants.NO_ADMIN_YET) {
			return StoryStatus.ACCEPTED;
		} else {
			return StoryStatus.EDITING;
		}
	}

	public void retrieveFromAdmin(Story story) throws InvalidStoryStatusException {
		StoryStatus status = this.determineWhichStatus(story);
		if(!StoryStatus.WAITING.equals(status)){
			throw new InvalidStoryStatusException("The story[Status:"+status.name()+"] must be on waiting status to retrieve story to edit.");
		}
		story.setIsOnline(Constants.OFFLINE_EDIT_MODE);
		story.setAdminId(Constants.NO_ADMIN_YET);
		storyDAO.saveOrUpdate(story);
	}
}
