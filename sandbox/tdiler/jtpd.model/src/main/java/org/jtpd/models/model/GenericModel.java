/**
 * 
 */
package org.jtpd.models.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

import org.jtpd.models.pk.GenericPK;

/**
 * @author tdiler
 *
 */
@MappedSuperclass
public abstract class GenericModel<ID extends GenericPK> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8223081406221272129L;
	
	@EmbeddedId
	private ID id;
	
	public GenericModel(){
		
	}
	
	public GenericModel(ID id){
		this.id = id;
	}
	
	public ID getId(){
		return this.id;
	}

}
