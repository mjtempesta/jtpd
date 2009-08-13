/**
 * 
 */
package org.jtpd.dao;

import java.util.List;

import org.jtpd.domain.model.Activities;

/**
 * @author tdiler
 *
 */
public interface IActivityDAO extends IGenericDAO<Integer, Activities> {

	/**
	 * 
	 */
	List<Activities> getActivityList();

}
