package org.jtpd.helloworld;

/**
 * @author tdiler
 *
 */
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
 
/**
 * Homepage
 */
public class Index extends WebPage {
 
	private static final long serialVersionUID = 1L;
 
    public Index(final PageParameters parameters) {
 
        // Add the simplest type of label
        add(new Label("message", "Wicket Hello World"));
 
    }
}
