/**
 * 
 */
package org.jtpd.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author tdiler
 * 
 */
@Entity
@Table(name = "TEACHER")
public class Teacher extends GenericModel<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6386312316626234015L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "FIRSTNAME")
	private String firstName;
	
	@Column(name = "LASTNAME")
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/* (non-Javadoc)
	 * @see org.jtpd.models.model.GenericModel#getPK()
	 */
	@Override
	public Integer getPK() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see org.jtpd.models.model.GenericModel#setPK(java.io.Serializable)
	 */
	@Override
	protected void setPK(Integer id) {
		this.id = id;
	}

}
