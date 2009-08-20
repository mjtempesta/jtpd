package org.jtpd.web;

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
public class GirisSayfa extends AnaSayfa {

    public GirisSayfa() {
        SideBarPanel sideBarPanel = new SideBarPanel("SideBarPanel");
        add(sideBarPanel);

    }
}
