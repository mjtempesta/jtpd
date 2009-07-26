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
@Table(name="STUDENT")
public class Student extends GenericModel<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1719265764785883899L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="FIRSTNAME")
	private String firstName;
	
	@Column(name="LASTNAME")
	private String lastName;
	
	@Column(name="AGE")
	private Integer age;
	
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
	
	public Integer getId() {
		return id;
	}

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}


}
