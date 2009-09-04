package org.jtpd.web;

/**
 * @author tdiler
 *
 */
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.jtpd.web.page.StoryFormPage;
import org.jtpd.web.page.StoryTablePage;
 
public class JTPDApplication extends WebApplication
{    
	

    /* (non-Javadoc)
	 * @see org.apache.wicket.protocol.http.WebApplication#init()
	 */
	@Override
	protected void init() {
		super.init();
		addComponentInstantiationListener(new SpringComponentInjector(this));
		mountBookmarkablePage("/story/entry", StoryFormPage.class);
		mountBookmarkablePage("/story/list", StoryTablePage.class);
	}

	@Override
    public Class<? extends Page> getHomePage() {
        return  GirisSayfa.class;
    }
}