package com.foo.web;

import org.apache.wicket.*;
import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.UnauthorizedInstantiationException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebRequestCycle;
import org.apache.wicket.protocol.http.WebRequestCycleProcessor;
import org.apache.wicket.request.IRequestCycleProcessor;
import org.apache.wicket.request.target.coding.QueryStringUrlCodingStrategy;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import java.util.ArrayList;

/**
 * User: Altug Bilgin ALTINTAS
 * Date: 15.May.2010
 * Time: 15:29:18
 */
public class MyWebApplication extends AuthenticatedWebApplication implements ApplicationContextAware {

    private ApplicationContext context;
    boolean isInitialized = false;
    

    @Override
    protected void init() {
        if (!isInitialized) {
            super.init();
            setListeners();
            isInitialized = true;
        }

        
        this.mountBookmarkablePageWithUrlCoding("/normal", com.foo.web.NormalPage.class);
        this.mountBookmarkablePageWithUrlCoding("/login", LoginPage.class);
    }

    @Override
    public Session newSession(Request request, Response response) {
       return new MyAuthenticatedWebSession(request);
    }

    @Override
    protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
        return MyAuthenticatedWebSession.class;
    }

    private void mountBookmarkablePageWithUrlCoding(String path,
                                                    Class pageClass) {
        mount(new QueryStringUrlCodingStrategy(path, pageClass));
    }

    private void setListeners() {
        addComponentInstantiationListener(new SpringComponentInjector(this, context, true));
    }

    @Override
    public Class getHomePage() {
        return HomePage.class;
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return LoginPage.class;
    }

    
}


   
    
