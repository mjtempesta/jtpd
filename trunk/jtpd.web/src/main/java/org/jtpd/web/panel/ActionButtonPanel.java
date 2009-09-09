/**
 * 
 */
package org.jtpd.web.panel;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author tdiler
 *
 */
public class ActionButtonPanel extends Panel {
	
	public ActionButtonPanel(String id, List<ActionButton> buttons) {
		super(id);
		add(new ListView<ActionButton>("actionButtons", buttons) {

			@Override
			protected void populateItem(ListItem<ActionButton> item) {
				final ActionButton button = item.getModelObject();
				item.add(button);
			}
		});
	}
	
	public static ActionButtonPanel create(List<ActionButton> buttonList){
		return new ActionButtonPanel("actionPanel", buttonList);
	}
	
	public static ActionButtonPanel create(ActionButton[] buttons){
		return new ActionButtonPanel("actionPanel", Arrays.asList(buttons));
	}

}
