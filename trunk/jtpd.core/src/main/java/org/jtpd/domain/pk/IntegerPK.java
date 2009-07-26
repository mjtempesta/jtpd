/**
 * 
 */
package org.jtpd.domain.pk;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author tdiler
 *
 */
@Embeddable
public class IntegerPK extends GenericPK {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2035363343572150876L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public IntegerPK(){
		
	}
	
	public IntegerPK(Integer id){
		this.id = id;
	}

}
