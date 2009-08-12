package org.jtpd.domain.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="activitycustomer")
public class ActivityCustomer extends GenericModel<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3198685948440892437L;

	@ManyToOne 
	@JoinColumn(name="customerId")
	private Customer customer;
	
	@ManyToOne 
	@JoinColumn(name="activityId")
	private Activities activities;

	private boolean infoVerified;
	
	
	private String registerDate ;
	
	
	public Activities getActivities() {
		return activities;
	}

	public void setActivities(Activities activities) {
		this.activities = activities;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean isInfoVerified() {
		return infoVerified;
	}

	public void setInfoVerified(boolean infoVerified) {
		this.infoVerified = infoVerified;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
}
