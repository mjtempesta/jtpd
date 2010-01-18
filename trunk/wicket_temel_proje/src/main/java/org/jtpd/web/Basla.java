package org.jtpd.web;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

public class Basla extends WebPage {

	public Basla() {

		
		// degeri Model nesnesinde tutuyoruz
		Model<Integer> model = new Model<Integer>() {
		
			private static final long serialVersionUID = 1L;
			
			private int counter = 0;

			public Integer getObject() {
				return new Integer(counter++);
			}
		};
		
		
		// Html Label bileseni
		final Label label = new Label("counter", model);
		
		label.setOutputMarkupId(true); // Ajax icin bu gerekli
		
		// ajax linkinin sayfaya ekliyoruz
		add(new AjaxFallbackLink("link") {

			public void onClick(AjaxRequestTarget target) {
				if (target != null) {					
					// her tiklamada degerin guncellenmesi icin
					target.addComponent(label);
				}
			}
		});
		
		
		add(label); // label i sayfaya ekliyoruz
	}
}
