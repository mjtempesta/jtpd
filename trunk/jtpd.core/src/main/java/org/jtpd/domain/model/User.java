/**
 * 
 */
package org.jtpd.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * @author tdiler
 *
 */
@Entity
@Table(name="USER")
public class User extends GenericModel<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3332605474261117406L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	@Column(name="USERNAME")
	private String username;
	@Column(name="PASSWORD")
	private String password;

	@Column(name="FIRSTNAME")
	private String firstName;
	@Column(name="LASTNAME")
	private String lastName;
	@Column(name="EMAIL")
	private String email;
	@Column(name="PHONE")
	private String phone;
	
	public Integer getId() {
		return id;
	}

	@OneToMany(cascade={CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
	private List<Story> stories;

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Story> getStories() {
		return stories;
	}

	public void setStories(List<Story> stories) {
		this.stories = stories;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public Integer getPK() {
		return id;
	}

	@Override
	protected void setPK(Integer id) {
		this.id = id;
	}

}
