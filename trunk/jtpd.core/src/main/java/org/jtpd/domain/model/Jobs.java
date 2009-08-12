package org.jtpd.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name="jobs")
public class Jobs extends GenericModel<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3329837310103836332L;

	private String description ;
	
	@OneToMany(mappedBy="jobs", fetch=FetchType.LAZY) 
	private List<Customer> customer; 
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Customer> getCustomer() {
		return customer;
	}
	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}
}
