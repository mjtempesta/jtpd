/**
 * 
 */
package org.jtpd.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author tdiler
 * 
 */
@Entity
@Table(name="USER_EXTRA")
public class UserExtra extends GenericModel<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4344599510458601900L;

	@Id
	  @GeneratedValue(generator="foreign") 
	    @GenericGenerator(name="foreign", strategy = "foreign", parameters = {@Parameter(name="property", value="user")})
	private Integer id; // same id with user
	
	@OneToOne(mappedBy = "extra", cascade=javax.persistence.CascadeType.ALL, optional=false)
	@Cascade(CascadeType.ALL)
	private User user;
	
	@Column(name="CREATEDTIME")
	private Date createdTime;
	@Column(name="APPROVALMESSAGE")
	private String approvalMessage;
	@Column(name="APPROVEDTIME")
	private Date approvedTime;
	@Column(name="APPROVEDBY")
	private Integer approvedBy;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Integer approvedBy) {
		this.approvedBy = approvedBy;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getApprovalMessage() {
		return approvalMessage;
	}

	public void setApprovalMessage(String approvalMessage) {
		this.approvalMessage = approvalMessage;
	}

	public Date getApprovedTime() {
		return approvedTime;
	}

	public void setApprovedTime(Date approvedTime) {
		this.approvedTime = approvedTime;
	}

	public User getUser() {
		return user;
	}

	@Override
	public Integer getPK() {
		return this.id;
	}

	@Override
	protected void setPK(Integer id) {
		this.id = id;
	}
}
