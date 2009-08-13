/**
 * 
 */
package org.jtpd.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author tdiler
 *
 */
@Component
public class JForumService extends GenericService implements IJForumService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3875258712289769924L;
	
	public List getLastestTopics() throws Exception {
		return this.getLastestTopics(15);
	}
	
	public List getLastestTopics(int maxTopic) throws Exception {
		
		try {
		JforumDBOperations.openConnection();
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
		.getExternalContext().getRequest();

		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance()
		.getExternalContext().getResponse();

		request.setAttribute("action", "list");
		request.setAttribute("module", "recentTopics");
		JForumHelper.service(request, response);
		
		List<Topic> topics = (List)request.getAttribute("topicsForMe");
		
		List<Topic> newTopicList = new ArrayList();
		int count = 0 ;
		for(Topic topic : topics) {
			newTopicList.add(topic) ;
			if (count > maxTopic) {
				break;
			}
			count++;
		}
		return newTopicList ;
		
		
		
		} catch (Exception ex) {
			throw ex ;
		} finally {
			JforumDBOperations.closeConnection();
		}
	}

}
