package com.foo.web;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 * User: Altug Bilgin ALTINTAS
 * Date: 17.May.2010
 * Time: 03:16:31
 */
public class NormalPage extends WebPage {


    public NormalPage() {
        int counter = 0;        
        MyAuthenticatedWebSession session = (MyAuthenticatedWebSession) Session.get();
        Object x = session.get("x");
        if (x == null) {            
            counter = counter + 1;
            session.add("x", counter);
        } else {

            counter = (Integer) x;
            counter++;
            session.add("x", counter);
        }
        Label sessionIdLabel = new Label("result_go", counter + "");
        add(sessionIdLabel);

    }
}
