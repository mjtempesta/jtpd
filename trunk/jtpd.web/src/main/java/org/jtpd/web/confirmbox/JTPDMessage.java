/**
 * 
 */
package org.jtpd.web.confirmbox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tdiler
 *
 */
public class JTPDMessage implements Serializable {
	public enum TYPE {
		ERROR, INFO, SUCCESS, WARNING, DENIED;
	}
	private TYPE type;
	private String bodyKey;
	private String footerKey;
	private List<MessageButton> buttons = new ArrayList<MessageButton>();
	
	public JTPDMessage(TYPE type, String titleKey, String bodyKey, String footerKey){
		this.type = type;
		this.titleKey = titleKey;
		this.bodyKey = bodyKey;
		this.footerKey = footerKey;
	}
	/**
	 * @return the type
	 */
	public TYPE getType() {
		return type;
	}
	
	public void addButton(MessageButton button){
		this.buttons.add(button);
	}
	private String titleKey;
	/**
	 * @return the titleKey
	 */
	public String getTitleKey() {
		return titleKey;
	}
	
	/**
	 * @return the bodyKey
	 */
	public String getBodyKey() {
		return bodyKey;
	}
	
	/**
	 * @return the footerKey
	 */
	public String getFooterKey() {
		return footerKey;
	}
	
	/**
	 * @return the buttons
	 */
	public List<MessageButton> getButtonList() {
		return buttons;
	}
}
