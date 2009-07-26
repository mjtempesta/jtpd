/**
 * 
 */
package org.jtpd.domain.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * @author tdiler
 *
 */
@MappedSuperclass
public abstract class GenericModel<PK extends Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8223081406221272129L;
	
	
	/**
	 * default constructor
	 */
	public GenericModel(){
		// DO NOTHING
	}
	
	/**
	 * @param id
	 */
	public GenericModel(PK id){
		this.setPK(id);
	}
	
	/**
	 * abstract setter method for primary key. It is been calling from constructor with id parameter.
	 * to bypass usage of setter method.
	 * @param id
	 */
	@Transient
	protected abstract void setPK(PK id);

	/**
	 * @return
	 */
	@Transient
	public abstract PK getPK();

}
