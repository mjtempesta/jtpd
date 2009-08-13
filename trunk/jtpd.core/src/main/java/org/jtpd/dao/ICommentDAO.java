/**
 * 
 */
package org.jtpd.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jtpd.domain.model.Comment;

/**
 * @author tdiler
 *
 */
public interface ICommentDAO extends IGenericDAO<Integer, Comment> {

	public void approveComments(List<Comment> comments);

	public void deleteComments(List<Comment> comments);

	public void saveComment(Comment comment);

	@SuppressWarnings("unchecked")
	public List<Comment> getWaitingComments();

	public List<Comment> getLastComments(int count);

}
