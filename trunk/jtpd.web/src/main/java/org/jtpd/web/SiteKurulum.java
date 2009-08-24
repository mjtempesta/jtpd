package org.jtpd.web;

/**
 * @author tdiler
 *
 */
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
 
public class SiteKurulum extends WebApplication
{    
	

    /* (non-Javadoc)
	 * @see org.apache.wicket.protocol.http.WebApplication#init()
	 */
	@Override
	protected void init() {
		super.init();
		addComponentInstantiationListener(new SpringComponentInjector(this));
	}

	@Override
    public Class<? extends Page> getHomePage() {
        return  GirisSayfa.class;
    }
}