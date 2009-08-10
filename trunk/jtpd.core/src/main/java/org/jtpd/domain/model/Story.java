/**
 * 
 */
package org.jtpd.domain.model;

import java.util.Date;

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
@Table(name="STORY")
public class Story extends GenericModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "AUTHOR")
	private User author;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "BODY")
	private String body;
	@Column(name = "TYPE")
	private StoryType type;
	@Column(name = "CREATEDTIME")
	private Date createdTime;
	@Column(name = "STATUS")
	private StoryStatus status;
	@Column(name = "LOOKEDBY")
	private User lookedBy;
	@Column(name = "LOOKEDTIME")
	private Date lookedTime;

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public StoryType getType() {
		return type;
	}

	public void setType(StoryType type) {
		this.type = type;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public StoryStatus getStatus() {
		return status;
	}

	public void setStatus(StoryStatus status) {
		this.status = status;
	}

	public User getLookedBy() {
		return lookedBy;
	}

	public void setLookedBy(User lookedBy) {
		this.lookedBy = lookedBy;
	}

	public Date getLookedTime() {
		return lookedTime;
	}

	public void setLookedTime(Date lookedTime) {
		this.lookedTime = lookedTime;
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
