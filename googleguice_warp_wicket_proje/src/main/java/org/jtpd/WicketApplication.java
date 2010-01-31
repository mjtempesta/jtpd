package org.jtpd;

import org.apache.wicket.Page;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;
import org.jtpd.config.modules.Module;
import org.jtpd.pages.EventPage;

import com.google.inject.Guice;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * @see wicket.myproject.Start#main(String[]) * 
 * @author Richard Wilkinson - richard.wilkinson@jweekend.com
 *
 */
public class WicketApplication extends WebApplication
{    
	/**
	 * Constructor
	 */
	public WicketApplication()
	{
		addComponentInstantiationListener(getGuiceInjector());
	}
	
	protected GuiceComponentInjector getGuiceInjector()
	{
		return new GuiceComponentInjector(this, Guice.createInjector(new Module()));
	}

	@Override
	protected void init() {
		super.init();
		mountBookmarkablePage("event", EventPage.class);
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

}
