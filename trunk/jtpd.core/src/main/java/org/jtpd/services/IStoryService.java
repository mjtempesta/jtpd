/**
 * 
 */
package org.jtpd.services;

import java.util.List;

import org.jtpd.domain.model.Comment;
import org.jtpd.domain.model.Story;

/**
 * @author tdiler
 *
 */
public interface IStoryService extends IGenericService {

	public List<Comment> getApprovedCommentsOfStory(Story story);

}
