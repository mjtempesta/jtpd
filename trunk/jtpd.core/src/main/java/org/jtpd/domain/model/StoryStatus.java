/**
 * 
 */
package org.jtpd.domain.model;

/**
 * @author tdiler
 *
 */
public enum StoryStatus {
	PUBLISHED, // published
	REJECTED, // ejected by admin
	ACCEPTED, // accepted by admin to check that is valid for publishing
	WAITING, // waiting for being accepted by admin
	EDITING; // still editing by user

	public String getResourceKey() {
		return "jtpd.story.status."+name().toLowerCase();
	}  
}
