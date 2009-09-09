/**
 * 
 */
package org.jtpd.web.panel;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jtpd.domain.model.Story;
import org.jtpd.domain.model.StoryStatus;
import org.jtpd.domain.model.User;
import org.jtpd.exception.InvalidStoryStatusException;
import org.jtpd.services.IStoryService;
import org.jtpd.services.IUserService;
import org.jtpd.web.confirmbox.ConfirmMessagePanel;
import org.jtpd.web.confirmbox.JTPDMessage;
import org.jtpd.web.confirmbox.MessageButton;
import org.jtpd.web.confirmbox.JTPDMessage.TYPE;
import org.jtpd.web.page.StoryFormPage;
import org.jtpd.web.page.StoryTablePage;

/**
 * @author tdiler
 *
 */
public class StoryAjaxTablePanel extends Panel {
	@SpringBean
	private IStoryService storyService;
	@SpringBean
	private IUserService userService;
	
	/**
	 * 
	 */
	class ActionCell extends Panel
	{
		
		
		/**
		 * @param id
		 *            component id
		 * @param model
		 *            model for contact
		 */
		public ActionCell(String id, IModel<Story> model)
		{
			super(id, model);
			StoryStatus status = storyService.determineWhichStatus(model.getObject());
			Link<Story> editLink = new Link<Story>("edit")
			{
				@Override
				public void onClick()
				{
					Story selected = (Story)getParent().getDefaultModelObject();
					setResponsePage(new StoryFormPage(selected));
				}
			};
			Link<Story> sendToAdminLink = new Link<Story>("sendToAdmin")
			{
				@Override
				public void onClick()
				{
					final Story selected = (Story)getParent().getDefaultModelObject();
					JTPDMessage message = new JTPDMessage(TYPE.WARNING, "jtpd.confirmpanel.story.sendadmin.title", "jtpd.confirmpanel.story.sendadmin.body", "jtpd.confirmpanel.story.sendadmin.footer");
					MessageButton messageButton = new MessageButton("label.no"){
						@Override
						public void onClick() {
							this.getParent().getParent().getParent().replaceWith(StoryAjaxTablePanel.this);
						}
					};
					MessageButton messageButton2 = new MessageButton("label.yes", new Model(selected)){
						@Override
						public void onClick() {
							// TODO uygun exception handling le buralar degisecek
							try {
								storyService.sendToAdmin((Story)getAffectedModel().getObject());
							} catch (InvalidStoryStatusException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							setResponsePage(new StoryTablePage());
						}
					};
					
					message.addButton(messageButton);
					message.addButton(messageButton2);
					
					StoryAjaxTablePanel.this.replaceWith(new ConfirmMessagePanel( 
							StoryAjaxTablePanel.this.getId(), message ));
				}
			};
			Link<Story> retrieveFromAdminLink = new Link<Story>("retrieveFromAdmin")
			{
				@Override
				public void onClick()
				{
					Story selected = (Story)getParent().getDefaultModelObject();
					// TODO uygun exception handling le buralar degisecek
					try {
						storyService.retrieveFromAdmin(selected);
					} catch (InvalidStoryStatusException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					setResponsePage(new StoryTablePage());
				}
			};
			Link<Story> deleteLink = new Link<Story>("delete")
			{
				@Override
				public void onClick()
				{
					final Story selected = (Story)getParent().getDefaultModelObject();
					JTPDMessage message = new JTPDMessage(TYPE.WARNING, "jtpd.confirmpanel.story.delete.title", "jtpd.confirmpanel.story.delete.body", null);
					MessageButton messageButton = new MessageButton("label.no"){
						@Override
						public void onClick() {
							this.getParent().getParent().getParent().replaceWith(StoryAjaxTablePanel.this);
						}
					};
					MessageButton messageButton2 = new MessageButton("label.yes", new Model(selected)){
						@Override
						public void onClick() {
							storyService.delete((Story)getAffectedModel().getObject());
							setResponsePage(new StoryTablePage());
						}
					};
					
					message.addButton(messageButton);
					message.addButton(messageButton2);
					
					StoryAjaxTablePanel.this.replaceWith(new ConfirmMessagePanel( 
							StoryAjaxTablePanel.this.getId(), message ));

				}
			};
			editLink.setVisible(false).setEnabled(false);
			deleteLink.setVisible(false).setEnabled(false);
			sendToAdminLink.setVisible(false).setEnabled(false);
			retrieveFromAdminLink.setVisible(false).setEnabled(false);
			
			if(StoryStatus.EDITING.equals(status)){
				editLink.setVisible(true).setEnabled(true);
				deleteLink.setVisible(true).setEnabled(true);
				sendToAdminLink.setVisible(true).setEnabled(true);
			} else if(StoryStatus.WAITING.equals(status)){
				retrieveFromAdminLink.setVisible(true).setEnabled(true);
			}
			add(editLink);
			add(deleteLink);
			add(sendToAdminLink);
			add(retrieveFromAdminLink);
		}
	}
	
	public StoryAjaxTablePanel(String id) {
		super(id);
		User user = userService.findUser(71);
		List<Story> storyList = storyService.getStories(user);
		add(new DataView<Story>("simple", new ListDataProvider(storyList))
		{
			@Override
			protected void populateItem(final Item<Story> item)
			{
				Story story = item.getModelObject();
				//item.add(new ActionPanel("actions", item.getModel()));
				item.add(new Label("id", String.valueOf(story.getId())));
				item.add(new Label("title", story.getTitle()));
				item.add(new Label("createdDate", story.getCreatedDate()));
				item.add(new Label("status", new ResourceModel(storyService.determineWhichStatus(story).getResourceKey())));
				item.add(new Label("publishedDate", story.getPublishedDate()));
				item.add(new ActionCell("actions", item.getModel()));
				item.add(new AttributeModifier("class", true, new AbstractReadOnlyModel<String>()
				{
					@Override
					public String getObject()
					{
						return (item.getIndex() % 2 == 1) ? "even" : "odd";
					}
				}));
			}
			

		});
	}


}
