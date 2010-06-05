package com.foo.datasource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


/**
 * User: Altug Bilgin ALTINTAS
 * Date: 18.May.2010
 * Time: 03:13:46
 */
public class MyUserDetails extends User {

    public MyUserDetails(String eposta,
                         String password,
                         boolean enabled,
                         boolean accountNonExpired,
                         boolean credentialsNonExpired,
                         boolean accountNonLocked,
                         GrantedAuthority[] authorities) {
        super(eposta, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }


}
