package org.jtpd.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;



@Entity 
@Table(name="customer")
public class Customer extends GenericModel<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4017412077019820169L;

	@Transient
	private Logger logger = Logger.getLogger("appLogger");
	
	private String name ; 
	private String surname; 
	private String email;
	private String phone ; 
	private String cellphone;	
	
    @JoinColumn(name = "companyname" )
    private String companyName;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "businessActivity" )
	private Jobs jobs ;
	
	@OneToMany (mappedBy="customer", cascade=CascadeType.ALL)
	private List<ActivityCustomer> activityCustomer;

	public  Customer() {
		if (logger.isDebugEnabled()) {
			logger.debug("Customer is created...");
		}
	}
	
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		if (logger.isDebugEnabled()) {
			logger.debug("Customer setting name ... " + name);
		}
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}		
	
	
	
	


	public Jobs getJobs() {
		return jobs;
	}


	public void setJobs(Jobs jobs) {
		this.jobs = jobs;
	}


	
	public String getComment() {
		return companyName;
	}


	public void setComment(String companyName) {
		this.companyName = companyName;
	}


	public List<ActivityCustomer> getActivityCustomer() {
		return activityCustomer;
	}


	public void setActivityCustomer(List<ActivityCustomer> activityCustomer) {
		this.activityCustomer = activityCustomer;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	


	
	 
	
}
