package org.jtpd.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="activities")
public class Activities extends GenericModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6686723970642419381L;
	
	private String startDate ;
	private String endDate ; 
	private String owner ; 
	private int isFree ; 
	private String location ; 
	private String infoPage ;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "trainingId" )
	private TrainingNames trainingNames ; 
	
	
	@OneToMany(mappedBy="activities", cascade=CascadeType.ALL)
	private List<ActivityCustomer> activityCustomer;	

	

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getIsFree() {
		return isFree;
	}

	public void setIsFree(int isFree) {
		this.isFree = isFree;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public List<ActivityCustomer> getActivityCustomer() {
		return activityCustomer;
	}

	public void setActivityCustomer(List<ActivityCustomer> activityCustomer) {
		this.activityCustomer = activityCustomer;
	}
	
	public String getInfoPage() {
		return infoPage;
	}

	public void setInfoPage(String infoPage) {
		this.infoPage = infoPage;
	}

	public TrainingNames getTrainingNames() {
		return trainingNames;
	}

	public void setTrainingNames(TrainingNames trainingNames) {
		this.trainingNames = trainingNames;
	}
}
