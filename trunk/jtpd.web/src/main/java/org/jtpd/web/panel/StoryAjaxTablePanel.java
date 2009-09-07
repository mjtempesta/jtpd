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
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jtpd.domain.model.Story;
import org.jtpd.domain.model.User;
import org.jtpd.services.IStoryService;
import org.jtpd.services.IUserService;
import org.jtpd.util.Constants;
import org.jtpd.web.confirmbox.ConfirmMessagePanel;
import org.jtpd.web.confirmbox.JTPDMessage;
import org.jtpd.web.confirmbox.MessageButton;
import org.jtpd.web.confirmbox.JTPDMessage.TYPE;
import org.jtpd.web.page.StoryFormPage;

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
			add(new Link("edit")
			{
				@Override
				public void onClick()
				{
					Story selected = (Story)getParent().getDefaultModelObject();
					try {
						setResponsePage(new StoryFormPage(selected));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			add(new Link("delete")
			{
				@Override
				public void onClick()
				{
					final Story selected = (Story)getParent().getDefaultModelObject();
					JTPDMessage message = new JTPDMessage(TYPE.WARNING, "jtpd.confirmpanel.story.delete.title", "jtpd.confirmpanel.story.delete.body", null);
					MessageButton messageButton = new MessageButton("label.no"){
						@Override
						public void onClick() {
							this.replaceWith(StoryAjaxTablePanel.this);
						}
					};
					MessageButton messageButton2 = new MessageButton("label.yes", new Model(selected)){
						@Override
						public void onClick() {
							storyService.delete((Story)getAffectedModel().getObject());
							this.replaceWith(StoryAjaxTablePanel.this);
						}
					};
					
					message.addButton(messageButton);
					message.addButton(messageButton2);
					
					StoryAjaxTablePanel.this.replaceWith(new ConfirmMessagePanel( 
							StoryAjaxTablePanel.this.getId(), message ));

				}
			});
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
				item.add(new Label("status", determineWhichStatus(story)));
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
			
			private String determineWhichStatus(Story story){
				if(story.getIsOnline()==Constants.ONLINE){
					return "Published";
				} else if(story.getIsOnline()==Constants.REJECT){
					return "Rejected";
				} else if(story.getIsOnline()==Constants.OFFLINE_DONE && story.getAdminId() == Constants.NO_ADMIN_YET){
					return "Waiting For Accepted";
				} else if(story.getIsOnline()==Constants.OFFLINE_DONE && story.getAdminId() != Constants.NO_ADMIN_YET){
					return "On Admin";
				} else {
					return "You can edit";
				}  
			}
		});
	}


}
