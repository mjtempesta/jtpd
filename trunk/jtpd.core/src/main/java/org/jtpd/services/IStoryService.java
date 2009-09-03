/**
 * 
 */
package org.jtpd.services;

import java.util.List;

import org.jtpd.domain.model.Comment;
import org.jtpd.domain.model.Story;
import org.jtpd.domain.model.User;

/**
 * @author tdiler
 *
 */
public interface IStoryService extends IGenericService {

	public List<Comment> getApprovedCommentsOfStory(Story story);
	public Story getStory(Integer storyId);
	public List<Story> getStories(User user);
	public void delete(Story story);
}
