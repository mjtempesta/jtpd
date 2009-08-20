package org.jtpd.web;

/**
 * @author tdiler
 *
 */
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
 
public class SiteKurulum extends WebApplication
{    
	

    @Override
    public Class<? extends Page> getHomePage() {
        return  GirisSayfa.class;
    }
}