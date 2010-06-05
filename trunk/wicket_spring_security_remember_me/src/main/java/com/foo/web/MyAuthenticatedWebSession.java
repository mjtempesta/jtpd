package com.foo.web;

import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.protocol.http.WebResponse;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * User: Altug Bilgin ALTINTAS
 * Date: 15.May.2010
 * Time: 15:27:14
 */
public class MyAuthenticatedWebSession extends AuthenticatedWebSession {

    private static final Logger logger = Logger.getLogger("Logger");

    private HashMap data = new HashMap();

    @SpringBean(name="authenticationManager")
    private AuthenticationManager authenticationManager;

    @SpringBean(name="rememberMeServicesx")
    private TokenBasedRememberMeServices rememberMeServices;

    public MyAuthenticatedWebSession(Request request) {
        super(request);
        injectDependencies();
        ensureDependenciesNotNull();
        String id = this.getSessionStore().getSessionId(request,true);
    }

    @Override
    protected boolean isCurrentRequestValid(RequestCycle lockedRequestCycle) {
        return super.isCurrentRequestValid(lockedRequestCycle);    //To change body of overridden methods use File | Settings | File Templates.
    }

    private void ensureDependenciesNotNull() {
        if (authenticationManager == null) {
            throw new IllegalStateException("AdminSession requires an authenticationManager.");
        }
    }

    private void injectDependencies() {
        InjectorHolder.getInjector().inject(this);
    }

    @Override
    public boolean authenticate(String username, String password) {
        boolean authenticated = false;
        try {
            Authentication authentication = authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            authenticated = authentication.isAuthenticated();

            ServletWebRequest servletWebRequest = (ServletWebRequest) RequestCycle.get().getRequest();
            HttpServletRequest request = servletWebRequest.getHttpServletRequest();
            
            WebResponse webResponse = (WebResponse)   RequestCycle.get().getResponse();
            HttpServletResponse response = webResponse.getHttpServletResponse();

            rememberMeServices.onLoginSuccess(request,response,authentication);
            

        } catch (AuthenticationException exx) {
            //TODO solve it
            logger.warning(" " + exx);
            authenticated = false;
        }
        return authenticated;
    }

    @Override
    public Roles getRoles() {
        Roles roles = new Roles();
        getRolesIfSignedIn(roles);
        return roles;
    }

    private void getRolesIfSignedIn(Roles roles) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
           this.signIn(true);
            addRolesFromAuthentication(roles, authentication);
        }        
    }

    private void addRolesFromAuthentication(Roles roles, Authentication authentication) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            roles.add(authority.getAuthority());
        }
    }

    public void add(String key, Object value) {
        this.setAttribute(key, value);
        
    }

    public void remove(String key) {
         this.removeAttribute(key);
    }

    public Object get(String key) {
        return this.getAttribute(key);
    }


    public void logout() {
        this.invalidate();
        if (SecurityContextHolder.getContext() != null) {
           SecurityContextHolder.getContext().setAuthentication(null);
           this.signIn(false);
        }
        
    }


}
