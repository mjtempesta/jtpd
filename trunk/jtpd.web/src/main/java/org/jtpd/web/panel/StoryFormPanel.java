/**
 * 
 */
package org.jtpd.web.panel;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.jtpd.domain.model.Activities;
import org.jtpd.domain.model.Story;
import org.jtpd.domain.model.User;
import org.jtpd.services.IActivityService;
import org.jtpd.services.IStoryService;
import org.jtpd.services.IUserService;



/**
 * @author tdiler
 *
 */
public class StoryFormPanel extends Panel {
	private static final long serialVersionUID = 8231140493238467690L;

	@SpringBean
	private IUserService userService;
	@SpringBean
	private IStoryService storyService;
	@SpringBean
	private IActivityService activityService;
	
	private Integer userId = 71;
	
	public StoryFormPanel(String id){
		super(id);
		add(new __StoryForm("storyForm", new CompoundPropertyModel<Story>(
				new LoadableDetachableModel<Story>() {
					private static final long serialVersionUID = 1L;

					protected Story load() {
						return new Story();
					}
				})));
	}
	public StoryFormPanel(String id, final Story story) {
		super(id);
		add(new __StoryForm("storyForm", new CompoundPropertyModel<Story>(
				new LoadableDetachableModel<Story>() {
					private static final long serialVersionUID = 1L;

					protected Story load() {
						return story;
					}
				})));
	}
	public StoryFormPanel(String id, final Integer storyId) {
		super(id);
		add(new __StoryForm("storyForm", new CompoundPropertyModel<Story>(
				new LoadableDetachableModel<Story>() {
					private static final long serialVersionUID = 1L;

					protected Story load() {
						return storyService.getStory(storyId);
					}
				})));
	}


	private class __StoryForm extends Form<Story> {

		private static final long serialVersionUID = -3463418235612437853L;
		
		@SuppressWarnings("serial")
		public __StoryForm(String id, IModel<Story> model) {
			super(id, model);
			TextField<String> title = new TextField<String>("title", String.class);
			title.setRequired(true);
			title.add(StringValidator.maximumLength(255));
			add(title);
			
			TextArea<String> header = new TextArea<String>("headerContent");
			header.setRequired(true);
			add(header);
			
			TextArea<String> body = new TextArea<String>("content");
			body.setRequired(true);
			add(body);
			
			add(new Button("save") {
				public void onSubmit() {
					  User user = userService.findUser(71);
					  Story story = (Story) getForm().getModelObject();
					  System.out.println(story.getContent());
					  userService.writeAStory(userId, story);
					}
				}.setDefaultFormProcessing(false));
			
			add(new Button("cancel") {
				public void onSubmit() {
					//setResponsePage(StoryList.class);
				}
			}.setDefaultFormProcessing(false));
			
			
		}
		
	}
}
