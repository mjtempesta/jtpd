/**
 * 
 */
package org.jtpd.web.page;

import org.jtpd.web.AnaSayfa;
import org.jtpd.web.panel.StoryAjaxTablePanel;

/**
 * @author tdiler
 *
 */
public class StoryTablePage extends AnaSayfa {
	public StoryTablePage(){
		add(new StoryAjaxTablePanel("storyTable"));
	}
}
