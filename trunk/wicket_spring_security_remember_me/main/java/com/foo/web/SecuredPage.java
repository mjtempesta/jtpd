package com.foo.web;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;

/**
 * User: Altug Bilgin ALTINTAS
 * Date: 15.May.2010
 * Time: 15:30:53
 */
@AuthorizeInstantiation("ROLE_ADMIN")
public class SecuredPage extends WebPage {
    //omitted, since not relevant

    public  SecuredPage() {
        System.out.println("Secured Page calistirildi");

        
    }
}