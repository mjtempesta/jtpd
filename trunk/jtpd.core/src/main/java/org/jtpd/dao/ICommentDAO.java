/**
 * 
 */
package org.jtpd.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jtpd.domain.model.Comment;
import org.jtpd.domain.model.Story;

/**
 * @author tdiler
 *
 */
public interface ICommentDAO extends IJTPDGenericDAO<Integer, Comment> {

	public void approveComments(List<Comment> comments);

	public void deleteComments(List<Comment> comments);

	public void saveComment(Comment comment);

	public List<Comment> getWaitingComments();

	public List<Comment> getLastComments(int count);

	@SuppressWarnings("unchecked")
	public List<Comment> getApprovedComments(Story story);

}
