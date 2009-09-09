/**
 * 
 */
package org.jtpd.services;

import java.util.List;

import org.jtpd.domain.model.Comment;
import org.jtpd.domain.model.Story;
import org.jtpd.domain.model.StoryStatus;
import org.jtpd.domain.model.User;
import org.jtpd.exception.InvalidStoryStatusException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tdiler
 *
 */
public interface IStoryService extends IGenericService {

	public List<Comment> getApprovedCommentsOfStory(Story story);
	public Story getStory(Integer storyId);
	public List<Story> getStories(User user);
	public void delete(Story story);
	@Transactional(propagation = Propagation.NEVER)
	public StoryStatus determineWhichStatus(Story story);
	public void sendToAdmin(Story selected) throws InvalidStoryStatusException;
	public void retrieveFromAdmin(Story selected) throws InvalidStoryStatusException;
}
