/**
 * 
 */
package org.jtpd.web.page;

import org.jtpd.web.AnaSayfa;
import org.jtpd.web.panel.ActionButton;
import org.jtpd.web.panel.ActionButtonPanel;
import org.jtpd.web.panel.StoryAjaxTablePanel;

/**
 * @author tdiler
 *
 */
public class StoryTablePage extends AnaSayfa {
	private ActionButton[] actionButtons = new ActionButton[]{
			new ActionButton("label.writestory"){
				@Override
				public void onClick() {
					setResponsePage(new StoryFormPage());
				}
			}
	};
	public StoryTablePage(){
		add(ActionButtonPanel.create(actionButtons));
		add(new StoryAjaxTablePanel("storyTable"));
	}
}
