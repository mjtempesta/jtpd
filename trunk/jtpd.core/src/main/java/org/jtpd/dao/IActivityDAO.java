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
public interface IActivityDAO extends IJTPDGenericDAO<Integer, Activities> {

	/**
	 * 
	 */
	public List<Activities> getActivityList();

	public List<Activities> getActivityList(int trainingCode, int cityCode, int monthOfTheYear);

}
