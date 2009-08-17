/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jtpd.web.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 *
 * @author altuga
 */
public class CookieManager {
	
    private static Logger logger = Logger.getLogger("appLogger");

    public static void addCookie(String name, String value, HttpServletResponse response)
	{
        try {
            int maxAge = 3600 * 24 * 300;
            if (value == null) {
                maxAge = 0;
                value = "";
            }
            Cookie cookie = new Cookie(name, value);
            cookie.setMaxAge(maxAge);            
            cookie.setPath("/");
            response.addCookie(cookie);
            response.flushBuffer();
        } catch (Exception e) {
            logger.error(e);
        }
	}

    public static Cookie getCookie(String name, HttpServletRequest request)
	{
        try {
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    Cookie c = cookies[i];

                    if (c.getName().equals(name)) {
                        return c;
                    }
                }
            }
            return null;
        } catch (Exception e) { 
            logger.error(e);
            return null;
        }
	}

}
