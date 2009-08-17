/**
 * 
 */
package org.jtpd.web.displaytag.decorator;

import org.displaytag.decorator.TableDecorator;
import org.jtpd.domain.model.Story;
import org.jtpd.util.Constants;
import org.jtpd.util.DateUtils;

/**
 * @author altuga
 * 
 */
public class StoryApproveDecorator extends TableDecorator {

	/**
	 * <display:column property="title" /> <display:column
	 * property="createdDate" /> <display:column property="lastEditDate" />
	 * <display:column property="isOnline" />
	 */

	public String getTitle() {
		long id = ((Story) (this.getCurrentRowObject())).getId();
		String title = ((Story) (this.getCurrentRowObject())).getTitle();
		String link = "<a href=\"StoryApprove.jsf?storyId=" + id + "\">"
				+ title + "</a>&nbsp&nbsp";
		return link;
	}

	public String getCreatedDate() {
		String createdDate = ((Story) (this.getCurrentRowObject()))
				.getCreatedDate();
		return DateUtils.convertToFormattedDate(createdDate);
	}

	public String getLastEditDate() {

		String lastEditDate = ((Story) (this.getCurrentRowObject()))
				.getLastEditDate();
		return DateUtils.convertToFormattedDate(lastEditDate);
	}

	public String getIsOnline() {

		int isOnline = ((Story) (this.getCurrentRowObject())).getIsOnline();
		if (isOnline == Constants.REJECT) {
			return "Ret Edildi";
		} else if (isOnline == Constants.OFFLINE_EDIT_MODE) {
			return "D\u00fczenleme halinde";
		} else if (isOnline == Constants.OFFLINE_DONE) {
			return "Y\u00f6neticiye g\u00f6nderildi, haber bekleniyor.";
		} else if(isOnline == Constants.ONLINE) {
			return "Yay\u0131nda";
		} else {
			return "Belirsiz, yardim icin info@jtpd.org a eposta atiniz";
		}
	}

	
}
