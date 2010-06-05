package com.foo.datasource;



import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Altug Bilgin ALTINTAS
 */

public class MyAuthenticationProcessingFilter extends UsernamePasswordAuthenticationFilter {
    private UserDetailsService usrServ;
    
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, javax.servlet.ServletException {
        System.out.println("Successful Login");
        super.successfulAuthentication(request,response,authResult);
    }

    public UserDetailsService getUsrServ() {
        return usrServ;
    }

    public void setUsrServ(UserDetailsService usrServ) {
        this.usrServ = usrServ;
    }
}
