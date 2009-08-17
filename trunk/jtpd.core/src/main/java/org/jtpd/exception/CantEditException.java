/**
 * 
 */
package org.jtpd.exception;

/**
 * @author altuga
 *
 */
// TODO çok basit bir exception ne amaçlý yaratýldý?
public class CantEditException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 615204352823481806L;

	public CantEditException(String message) {
		super(message) ;
	}
	
	public CantEditException() {
		super() ;
	}
}
