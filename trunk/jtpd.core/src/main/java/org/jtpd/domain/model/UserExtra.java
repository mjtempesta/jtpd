/**
 * 
 */
package org.jtpd.domain.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author tdiler
 * 
 */
@Entity
@Table(name="USER_EXTRA")
public class UserExtra extends GenericModel<Integer> {

	private Integer id; // same id with user
	private User user;
	private Date createdTime;
	private String approvalMessage;
	private Date approvedTime;
	private User approvedBy;

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

	public User getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(User approvedBy) {
		this.approvedBy = approvedBy;
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
