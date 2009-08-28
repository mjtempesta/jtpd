package org.jtpd.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity 
@Table(name="user")
public class User extends GenericModel<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6395580903197385515L;

	@Column(name="username")
	private String username ; 	

	// 1 = general user ; 2 = admin
	private int isAdmin = 1 ; 
	
	@Column(name="email")
	private String emailAddress;
	
	@Column(name="canSendEmail")
	private int canSendEmail;
	
	@OneToMany (mappedBy="user", cascade=CascadeType.ALL)
	private List<Story> stories ;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getCanSendEmail() {
		return canSendEmail;
	}

	public void setCanSendEmail(int canSendEmail) {
		this.canSendEmail = canSendEmail;
	}

}
