package org.jtpd.domain.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;



@Entity
@Indexed
public class Comment extends GenericModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2331502752090809703L;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "userId" )
	private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    
    private boolean approved;

    @Lob
    @Column(nullable = false)
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String comment;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "storyId" )
    private Story story;
    
    @Transient
    public String getFormattedDate(){
    	DateFormat fmt = new SimpleDateFormat("dd-MM-yyyy HH:ss");
    	return fmt.format(this.getDate());
    }
   
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }
}
