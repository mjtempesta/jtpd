/**
 * 
 */
package org.jtpd.web.panel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.jtpd.domain.model.GenericModel;

/**
 * @author tdiler
 * 
 */
public abstract class ConfirmMessagePanel<T> extends Panel {
	
	private IModel<T> model;
	
	/**
	 * @return the model
	 */
	public IModel<T> getModel() {
		return model;
	}

	public ConfirmMessagePanel(String id, String message, IModel<T> model) {
		super(id);
		this.model = model;
		add(new Label("message", message));
		add(new Link("confirm") {
			
			@Override
			public void onClick() {
				onConfirm();
			}
		});
		add(new Link("cancel") {
			@Override
			public void onClick() {
				onCancel();
			}
		});
	}

	protected abstract void onCancel();

	protected abstract void onConfirm();

}
