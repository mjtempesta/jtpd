package org.jtpd.web;

/**
 * @author tdiler
 *
 */
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.wicket.markup.html.panel.Panel;

/**
 * Homepage
 */
public class GirisSayfa extends AnaSayfa {
	
	public GirisSayfa(){
        this(SideBarPanel.class);
	}
    public GirisSayfa(Class<? extends Panel> panelClass) {
        try {
			Constructor constructor = panelClass.getConstructor(String.class);
			add((Panel)constructor.newInstance("bodyPanel"));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
