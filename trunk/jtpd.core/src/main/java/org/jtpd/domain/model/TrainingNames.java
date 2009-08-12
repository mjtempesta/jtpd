package org.jtpd.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="trainingnames")
public class TrainingNames extends GenericModel<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1614508491776148158L;

	private String  activityName ;
	
	@OneToMany (mappedBy="trainingNames", cascade=CascadeType.ALL)
	private List<Activities> activities;

	public List<Activities> getActivities() {
		return activities;
	}

	public void setActivities(List<Activities> activities) {
		this.activities = activities;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
}
