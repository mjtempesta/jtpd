package org.jtpd.web.panel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.ResourceModel;

public abstract class ActionButton extends Link {
	public ActionButton(String label) {
		super("actionButton");
		add(new Label("actionButtonText", new ResourceModel(label)));
	}
}