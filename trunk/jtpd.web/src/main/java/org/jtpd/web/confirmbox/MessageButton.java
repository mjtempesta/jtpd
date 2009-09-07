/**
 * 
 */
package org.jtpd.web.confirmbox;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

/**
 * @author tdiler
 * 
 */
@SuppressWarnings("unchecked")
public abstract class MessageButton extends Link {

	private IModel affectedModel;

	/**
	 * @return the affectedModel
	 */
	public IModel getAffectedModel() {
		return affectedModel;
	}

	public MessageButton(String labelKey) {
		this(labelKey, null);
	}

	public MessageButton(String labelKey, IModel model) {
		super("button", new Model(labelKey));
		this.affectedModel = model;
		add( new Label("buttonText",new ResourceModel(labelKey)));
	}

}
