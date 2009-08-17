package org.jtpd.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class RequestCharacterEncodingFilter implements Filter {

    private Logger logger = Logger.getLogger("appLogger");

    public static final String REQUEST_CHARACTER_ENCODING = "requestCharacterEncoding";

    private String encoding = null;

    /**
     * Describe <code>init</code> method here.
     *
     * @param filterConfig a <code>FilterConfig</code> value
     * @exception ServletException if an error occurs
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter(REQUEST_CHARACTER_ENCODING);
        if (logger.isDebugEnabled()) logger.debug("encoding=" + encoding);
    }

    /**
     * Describe <code>getInitParameter</code> method here.
     *
     * @param filterConfig a <code>FilterConfig</code> value
     * @param parameterName a <code>String</code> value
     * @return a <code>String</code> value
     * @exception ServletException if an error occurs
     */
    private String getInitParameter(FilterConfig filterConfig, String parameterName) throws ServletException {
        String value = filterConfig.getInitParameter(parameterName);
        if (value==null) {
            throw new ServletException(getClass().getName() + ": " + parameterName + " is required");
        }
        
        if (logger.isDebugEnabled()) logger.debug("value=" + value);
        return value;
    }

    /**
     * Describe <code>doFilter</code> method here.
     *
     * @param request a <code>ServletRequest</code> value
     * @param response a <code>ServletResponse</code> value
     * @param chain a <code>FilterChain</code> value
     * @exception IOException if an error occurs
     * @exception ServletException if an error occurs
     */
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException
    {        
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        chain.doFilter(request, response);                
    }

    public void destroy() { }

}
