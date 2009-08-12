/**
 * 
 */
package org.jtpd.dao;

import java.beans.Expression;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.jtpd.db.HibernateSessionFactory;
import org.jtpd.domain.model.Story;
import org.jtpd.domain.model.User;
import org.jtpd.exceptions.CantEditException;
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
public class StoryDAO extends GenericDAO<Integer, Story> implements IStoryDAO {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6045226641371244116L;
	
	private Logger logger = Logger.getLogger("appLogger");

    public void addNewStory(Story story) throws Exception {
        Date now = DateUtils.getNow();
    	if (story.getId() != null && story.getId() > 0) {
            story.setLastEditDate(now);
        } else {
            story.setCreatedDate(now);
        }
        this.saveOrUpdate(story);
        logger.debug("Story with identifier "+story.getId()+ " has been saved.");
    }

    public List<Story> listMyStories(User user) throws Exception {
        // TODO USE ASSERTION
    	if (user == null) {
            throw new Exception("Kullanici yok");
        }
    	Criteria criteria = this.getSession().createCriteria(Story.class);
    	criteria.add(Restrictions.eq("user", user.getId()));
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

    public void undoSelectedStoryFromThisAdmin(User admin, long storyId) {
        Session session = null;
        session = HibernateSessionFactory.openSession();
        HibernateSessionFactory.beginTransaction();
        Story story = (Story) session.get(Story.class, storyId);
        story.setAdminId(Constants.NO_ADMIN_YET);
        session.saveOrUpdate(story);

    }

    public void publishTheStory(User admin, long storyId) {
        Session session = null;
        session = HibernateSessionFactory.openSession();
        HibernateSessionFactory.beginTransaction();
        Story story = (Story) session.get(Story.class, storyId);
        story.setIsOnline(Constants.ONLINE);
        story.setPublishedDate(DateUtils.getNow());

    }

    public void rejectTheStory(User admin, long storyId) {
        Session session = null;
        session = HibernateSessionFactory.openSession();
        HibernateSessionFactory.beginTransaction();
        Story story = (Story) session.get(Story.class, storyId);
        story.setIsOnline(Constants.REJECT);

    }

    /***************************************************************************
     * Get the story that belongs to this admin. There is a security check.
     *
     * @return
     */
    public Story readStoryForCheck(User admin, int storyId) {
        Session session = null;
        session = HibernateSessionFactory.openSession();

        Story story = (Story) session.get(Story.class, storyId);
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
        Session session = null;
        session = HibernateSessionFactory.openSession();
        HibernateSessionFactory.beginTransaction();

        if (admin.getIsAdmin() == Constants.ADMIN && story.getAdminId() == admin.getId()) {
            session.saveOrUpdate(story);

        }
    }

    // called for Main.jsf
    public List getAllStories() {
        Session session = null;
        session = HibernateSessionFactory.openSession();

        Query query = session.createQuery("select story from Story story where story.isOnline =:online order by story.publishedDate desc");
        query.setInteger("online", Constants.ONLINE);
        List result = query.list();
        return result;
    }

    // read the story from TheStory.jsf (main page)
    public Story getTheStory(long storyId) {

        try {
            Session session = null;
            session = HibernateSessionFactory.openSession();

            Query query = session.createQuery("select story from Story story where story.id=:storyId and story.isOnline=:online ");
            query.setLong("storyId", storyId);
            query.setInteger("online", Constants.ONLINE);
            List result = query.list();

            if (result != null && result.size() > 0) {
                return ((Story) result.get(0));
            } else {
                return new Story();
            }
        } catch (Exception exception) {
             return new Story();
        }


    }

    public void approveComments(List<Comment> comments) {
        Session hibernateSession = HibernateSessionFactory.openSession();
        hibernateSession.beginTransaction();
        for (Comment c : comments) {
            c.setApproved(true);
        }
    }

    public void deleteComments(List<Comment> comments) {
        Session hibernateSession = HibernateSessionFactory.openSession();
        hibernateSession.beginTransaction();
        for (Comment c : comments) {
            hibernateSession.delete(c);
        }
    }

    public void saveComment(Comment comment) {
        try {
            Session session = HibernateSessionFactory.openSession();
            HibernateSessionFactory.beginTransaction();
            session.save(comment);
            HibernateSessionFactory.commitTransaction();
        } catch (Exception ex) {
            this.logger.error(" " + ex.getMessage());
        }
    }

    public List<Comment> getWaitingComments() {

        List<Comment> theList = new ArrayList<Comment>();
        try {
            Session session = HibernateSessionFactory.openSession();
            HibernateSessionFactory.beginTransaction();
            Query query = session.createQuery("select c from Comment c where c.approved = :approved order by c.date desc");
            query.setBoolean("approved", false);
            theList = query.list();
        } catch (Exception ex) {
            this.logger.error(" " + ex.getMessage());
        }
        return theList;
    }
    
    public List<Comment> getLastComments(int count) {

        List<Comment> theList = new ArrayList<Comment>();
        try {
            Session session = HibernateSessionFactory.openSession();
            HibernateSessionFactory.beginTransaction();
            Query query = session.createQuery("select c from Comment c where c.approved = :approved order by c.date desc");
            query.setMaxResults(count);
            query.setBoolean("approved", true);
            theList = query.list();
        } catch (Exception ex) {
            this.logger.error(" " + ex.getMessage());
        }
        return theList;
    }
}
