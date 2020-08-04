package com.restapi.bookstore.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserPrincipalDetailsService {
    UserDetails loadUserByUsername(String usernameOrEmail);
}
