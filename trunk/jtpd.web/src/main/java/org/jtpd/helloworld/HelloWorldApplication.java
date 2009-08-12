package org.jtpd.helloworld;

/**
 * @author tdiler
 *
 */
import org.apache.wicket.protocol.http.WebApplication;
 
public class HelloWorldApplication extends WebApplication
{    
	public Class<Index> getHomePage()
	{
		return Index.class;
	}
}