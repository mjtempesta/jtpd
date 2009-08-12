package org.jtpd.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

/**
 *
 * @author acil
 */
@Entity
@Indexed
// TODO sistem içerisinde kullanýlýyor mu? Çöplük olabilir.
public class Tag extends GenericModel<Integer> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5206129012736434110L;
	@Column(length = 50, nullable = false)
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
  
    
}
