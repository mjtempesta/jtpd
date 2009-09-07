/**
 * 
 */
package org.jtpd.web.confirmbox;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

/**
 * @author tdiler
 * 
 */
public class ConfirmMessagePanel extends Panel {

	public ConfirmMessagePanel(String id, JTPDMessage message) {
		super(id);
		add(new ContextImage("status", new Model<String>("images/"+message.getType().name()+".gif")));
		add(new Label("title", new ResourceModel(message.getTitleKey(), "")));
		add(new Label("body", new ResourceModel(message.getBodyKey(), message.getBodyKey())));
		add(new Label("footer", new ResourceModel(message.getFooterKey(), "")));
		add(new ListView<MessageButton>("buttons", message.getButtonList()) {
			@Override
			protected void populateItem(ListItem<MessageButton> item) {
				final MessageButton button = item.getModelObject();
				item.add(button);
			}
		});
	}

}
