/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jtpd.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;



/**
 *
 * @author acil
 */

@Entity
public class Category extends GenericModel<Integer> {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5705362576524579882L;

	@Column(length=50, nullable=false)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)    
    private List<Story> stories;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }
        
}
