package com.foo.datasource;


import org.apache.wicket.Session;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Altug Bilgin ALTINTAS
 */

public class MyRememberMeProcessingFilter extends RememberMeAuthenticationFilter {

    private UserDetailsService usrServ;

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) {
        // perform some custom logic when the user has been 'remembered' &amp; authenticated - e.g. update a login count etc
        System.out.println("Remember-me worked ");       
        super.onSuccessfulAuthentication(request, response, authResult);
    }

    public UserDetailsService getUsrServ() {
        return usrServ;
    }

    public void setUsrServ(UserDetailsService usrServ) {
        this.usrServ = usrServ;
    }
}
