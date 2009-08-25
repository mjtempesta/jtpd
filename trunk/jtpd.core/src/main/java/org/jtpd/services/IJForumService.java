/**
 * 
 */
package org.jtpd.services;

import java.util.List;

/**
 * @author tdiler
 *
 */
public interface IJForumService extends IGenericService {

	public List getLastestTopics() throws Exception;

	public List getLastestTopics(int maxTopic) throws Exception;

}
