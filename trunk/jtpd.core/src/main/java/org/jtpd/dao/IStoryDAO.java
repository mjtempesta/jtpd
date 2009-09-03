/**
 * 
 */
package org.jtpd.dao;

import java.util.List;

import org.jtpd.domain.model.Story;
import org.jtpd.domain.model.User;
import org.jtpd.exception.CantEditException;

/**
 * @author tdiler
 *
 */
public interface IStoryDAO extends IJTPDGenericDAO<Integer, Story> {

	public void addNewStory(Story story) throws Exception;

	public List<Story> listMyStories(User user);

	public Story getTheStory(User user, int storyId, boolean justForAdminRead);

	public void deleteTheStory(User user, int storyId) throws Exception;

	public void checkIfUserCanEditThisStory(Story story)
			throws CantEditException;

	public List<Story> getUnCheckedStories();

	public List<Story> getThisAdminStories(User admin);

	public void setSelectedStoriesToThisAdmin(User admin, String[] storyIds);

	public void setSelectedStoriesToThisAdmin(User admin, Integer[] storyIds);

	public void undoSelectedStoryFromThisAdmin(User admin, Integer storyId);

	public void publishTheStory(User admin, Integer storyId);

	public void rejectTheStory(User admin, Integer storyId);

	public Story readStoryForCheck(User admin, Integer storyId);

	public void saveStoryAsAdmin(User admin, Story story);

	public List<Story> getAllStories();

	public Story getTheStory(long storyId);

}
