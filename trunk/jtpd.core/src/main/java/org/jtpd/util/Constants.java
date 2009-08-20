/**
 * 
 */
package org.jtpd.util;

/**
 * @author altuga
 *
 */
public class Constants {

	public final static int ADMIN = 2 ;
	
	public final static String USERBEAN = "userBean";
	
	public final static int NO_ADMIN_YET = 0;
	
	// *************** story publish settings begins ********************
	
	public final static int REJECT = -2; // story is rejected by admin
	
	public final static int OFFLINE_EDIT_MODE = -1; // not online; user is still editing
	
	public final static int OFFLINE_DONE = 0; // not online but user send it to the admin
	
	public final static int ONLINE = 1; // yes it is online
	
	
	
	// *************** story publish settings ends ********************
	
	public final static String theStoriesList = "theStoriesList";
	
	public final static String unCheckedStories = "unCheckedStories";	
	
	public final static String thisAdminStories = "thisAdminStories";
	
	public final static int DONTSENDEMAIL = 1 ;
	public final static int SENDEMAIL = 0 ;
}
