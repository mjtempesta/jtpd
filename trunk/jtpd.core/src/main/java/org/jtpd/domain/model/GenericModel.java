/**
 * 
 */
package org.jtpd.domain.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private PK id;
	
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
		this.setId(id);
	}
	
	/**
	 * abstract setter method for primary key. It is been calling from constructor with id parameter.
	 * to bypass usage of setter method.
	 * @param id
	 */
	public void setId(PK id){
		this.id = id;
	}

	/**
	 * @return
	 */
	public PK getId(){
		return this.id;
	}

}
