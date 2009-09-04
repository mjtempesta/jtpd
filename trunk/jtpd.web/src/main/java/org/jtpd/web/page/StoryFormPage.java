/**
 * 
 */
package org.jtpd.web.page;

import org.jtpd.domain.model.Story;
import org.jtpd.web.AnaSayfa;
import org.jtpd.web.panel.StoryFormPanel;

/**
 * @author tdiler
 *
 */
public class StoryFormPage extends AnaSayfa {
	public StoryFormPage(){
		this(null);
	}

	public StoryFormPage(Story selected) {
		if(selected == null){
			add(new StoryFormPanel("storyForm"));
		} else {
			add(new StoryFormPanel("storyForm", selected));
		}
	}

}
