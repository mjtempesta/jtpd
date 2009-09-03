/**
 * 
 */
package org.jtpd.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jtpd.dao.IStoryDAO;
import org.jtpd.domain.model.Story;
import org.jtpd.domain.model.User;
import org.jtpd.exception.CantEditException;
import org.jtpd.util.Constants;
import org.jtpd.util.DateUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author altuga
 * 
 */
@Repository
@Transactional
public class StoryDAO extends JTPDGenericDAO<Integer, Story> implements IStoryDAO {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6045226641371244116L;
	
	public Logger logger = Logger.getLogger("appLogger");

    public void addNewStory(Story story) throws Exception {
        String now = DateUtils.getNow();
    	if (story.getId() != null && story.getId() > 0) {
            story.setLastEditDate(now);
        } else {
            story.setCreatedDate(now);
        }
        this.saveOrUpdate(story);
        logger.debug("Story with identifier "+story.getId()+ " has been saved.");
    }

    public List<Story> listMyStories(User user) {
    	Criteria criteria = this.getSession().createCriteria(Story.class);
    	criteria.add(Restrictions.eq("user.id", user.getId()));
    	return this.findByCriteria(criteria);
    }

    public Story getTheStory(User user, int storyId, boolean justForAdminRead) {
    	Story story = this.find(storyId);
    	User author = story.getUser();
        if ((user.getId() == author.getId()) // user is the author of the story 
        		|| (justForAdminRead && user.getIsAdmin() == Constants.ADMIN) // user is admin
        		|| (story.getAdminId() == user.getId()) // user who took responsibility to check
        		) {
            return story;
        } else {
            return null; // this is not this user's story.
        }
    }

    public void deleteTheStory(User user, int storyId) throws Exception {
        Story storyToDelete = this.find(storyId);
        if(storyToDelete != null){
        	this.checkIfUserCanEditThisStory(storyToDelete);
        	this.remove(storyToDelete);
        } else {
        	throw new Exception("The requested story with "+storyId+" identifier isn't found in database.");
        }
    }

    public void checkIfUserCanEditThisStory(Story story)
            throws CantEditException {
        if (story.getIsOnline() == Constants.OFFLINE_DONE || story.getIsOnline() == Constants.ONLINE) {
            throw new CantEditException();
        }
    }

    // TODO change method name
    public List<Story> getUnCheckedStories() {
    	Criteria criteria = this.getSession().createCriteria(Story.class);
    	criteria.add(Restrictions.eq("adminId", 0));
    	criteria.add(Restrictions.eq("isOnline", 0));
    	return this.findByCriteria(criteria);
    }

    // TODO change method name
    public List<Story> getThisAdminStories(User admin) {
    	Criteria criteria = this.getSession().createCriteria(Story.class);
    	criteria.add(Restrictions.eq("adminId", admin.getId()));
    	criteria.add(Restrictions.eq("isOnline", 0));
    	return this.findByCriteria(criteria);
    }
    
    public void setSelectedStoriesToThisAdmin(User admin, String[] storyIds) {
    	
    }

    public void setSelectedStoriesToThisAdmin(User admin, Integer[] storyIds) {
    	
    	Query updateQuery = this.getSession().createQuery("update "+Story.class.getName()+" set adminId = :id where id in (:arrayOfStoryId)");
    	updateQuery.setParameter("id", admin.getId());
    	updateQuery.setParameter("arrayOfStoryId", storyIds);
    	int countAffectedRow = updateQuery.executeUpdate();
    	if(countAffectedRow == 0 && storyIds.length > 0){
    		logger.warn("There is a problem on assigning stories"+Arrays.toString(storyIds)+" to admin["+admin.getId()+"]");
    	} else {
    		logger.debug("Stories"+Arrays.toString(storyIds)+" are assigned to admin["+admin.getId()+"]");
    	}
    }

    // TODO User admin method icerisinde kullanýlmýyor
    public void undoSelectedStoryFromThisAdmin(User admin, Integer storyId) {
    	Query updateQuery = this.getSession().createQuery("update "+Story.class.getName()+" set adminId = :id where id = (:storyId)");
    	updateQuery.setParameter("id", Constants.NO_ADMIN_YET);
    	updateQuery.setParameter("storyId", storyId);
    	int countAffectedRow = updateQuery.executeUpdate();
    	if(countAffectedRow == 0 && storyId !=null ){
    		logger.warn("There is a problem on making story["+storyId+"] to free");
    	} else {
    		logger.debug("Status of story["+storyId+"] is free");
    	}
    }

    public void publishTheStory(User admin, Integer storyId) {
        Story story = this.find(storyId);
        story.setIsOnline(Constants.ONLINE);
        story.setPublishedDate(DateUtils.getNow());
        this.saveOrUpdate(story);
    }

    public void rejectTheStory(User admin, Integer storyId) {
        Story story = this.find(storyId);
        story.setIsOnline(Constants.REJECT);
        this.saveOrUpdate(story);
    }

    /***************************************************************************
     * Get the story that belongs to this admin. There is a security check.
     *
     * @return
     */
    public Story readStoryForCheck(User admin, Integer storyId) {
    	Story story = this.find(storyId);
        if (story == null) {
            return null;
        }
        if (admin.getId() == story.getAdminId()) { // check if this admin can
            // read this story
            return story;
        } else {
            return null;
        }

    }

    public void saveStoryAsAdmin(User admin, Story story) {
        if (admin.getIsAdmin() == Constants.ADMIN && story.getAdminId() == admin.getId()) {
            this.saveOrUpdate(story);
        }
    }

    // called for Main.jsf
    public List<Story> getAllStories() {
    	Criteria criteria = this.getSession().createCriteria(Story.class);
    	criteria.add(Restrictions.eq("isOnline", Constants.ONLINE));
    	criteria.addOrder( Order.desc("publishedDate") );
    	return this.findByCriteria(criteria);
    }

    // read the story from TheStory.jsf (main page)
    // TODO burada story nullsa veya hata oluþursa yeni story oluþturuluyor.
    public Story getTheStory(long storyId) {
    	Criteria criteria = this.getSession().createCriteria(Story.class);
    	criteria.add(Restrictions.eq("isOnline", Constants.ONLINE));
    	criteria.add(Restrictions.eq("id", storyId));
        Story story = (Story) criteria.uniqueResult();
       if(story == null){
    	   logger.warn("Any online story is not found with "+storyId+". Create empty one." );
    	   return new Story();
       } else {
    	   return story;
       }
    }
}
