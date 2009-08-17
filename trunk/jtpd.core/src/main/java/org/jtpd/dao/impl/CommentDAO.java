/**
 * 
 */
package org.jtpd.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jtpd.dao.ICommentDAO;
import org.jtpd.domain.model.Comment;
import org.jtpd.domain.model.Story;
import org.springframework.stereotype.Repository;

/**
 * @author tdiler
 *
 */
@Repository
public class CommentDAO extends JTPDGenericDAO<Integer, Comment> implements ICommentDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4452776003742695313L;

    // TODO CommentService içerisine taþýnmalý
    public void approveComments(List<Comment> comments) {
        for (Comment c : comments) {
            c.setApproved(true);
        }
    }

    public void deleteComments(List<Comment> comments) {
        for (Comment c : comments) {
            this.remove(c);
        }
    }
    
    public void saveComment(Comment comment) {
    	this.saveOrUpdate(comment);
    }

    @SuppressWarnings("unchecked")
	public List<Comment> getWaitingComments() {
        Criteria criteria = this.getSession().createCriteria(Comment.class);
        criteria.add(Restrictions.eq("approved", false));
        criteria.addOrder(Order.desc("date"));
        return (List<Comment>)criteria.list();
    }
    
    // TODO Max Result Count'a ne gerek var burada
    @SuppressWarnings("unchecked")
	public List<Comment> getLastComments(int count) {
        Criteria criteria = this.getSession().createCriteria(Comment.class);
        criteria.add(Restrictions.eq("approved", true));
        criteria.addOrder(Order.desc("date"));
        return (List<Comment>)criteria.list();
    }
    
    @SuppressWarnings("unchecked")
	public List<Comment> getApprovedComments(Story story) {
        Criteria criteria = this.getSession().createCriteria(Comment.class);
        criteria.add(Restrictions.eq("approved", true));
        criteria.add(Restrictions.eq("story.id", story.getId()));
        return (List<Comment>)criteria.list();
    }
}
