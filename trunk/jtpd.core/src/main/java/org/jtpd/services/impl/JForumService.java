/**
 * 
 */
package org.jtpd.services.impl;

import java.util.List;

import org.jtpd.services.IJForumService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tdiler
 *
 */
@Component
@Transactional
public class JForumService extends GenericService implements IJForumService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3875258712289769924L;
	
	public List getLastestTopics() throws Exception {
		return this.getLastestTopics(15);
	}
	
	// TODO COMMENT KALDIRILACAK
	public List getLastestTopics(int maxTopic) throws Exception {
		return null;
//		try {
//		JforumDBOperations.openConnection();
//		
//		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
//		.getExternalContext().getRequest();
//
//		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance()
//		.getExternalContext().getResponse();
//
//		request.setAttribute("action", "list");
//		request.setAttribute("module", "recentTopics");
//		JForumHelper.service(request, response);
//		
//		List<Topic> topics = (List)request.getAttribute("topicsForMe");
//		
//		List<Topic> newTopicList = new ArrayList();
//		int count = 0 ;
//		for(Topic topic : topics) {
//			newTopicList.add(topic) ;
//			if (count > maxTopic) {
//				break;
//			}
//			count++;
//		}
//		return newTopicList ;
//		
//		
//		
//		} catch (Exception ex) {
//			throw ex ;
//		} finally {
//			JforumDBOperations.closeConnection();
//		}
	}

}
