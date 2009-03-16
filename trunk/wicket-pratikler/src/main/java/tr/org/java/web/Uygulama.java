/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.org.java.web;

import org.apache.log4j.Logger;
import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;

/**
 *
 * @author Administrator
 */
public class Uygulama extends WebApplication{

     private static Logger logger = Logger.getLogger("appLogger");

    /**
     * Yapilandirici.
     */
    public Uygulama()
    {
        logger.debug("basladi");        
    }


    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends Page> getHomePage()
    {
        return KayitlarSayfasi.class;
    }  

    /**
     * @see org.apache.wicket.examples.WicketExampleApplication#init()
     */
    @Override
    protected void init()
    {
        // url rewriting yapmak bu kadar basit.
        mountBookmarkablePage("/guzel-bir-uygulama", KayitlarSayfasi.class);

    }
    
    @Override
	public final Session newSession(Request request, Response response) {
		return new Oturum(request);
	}
    
}
