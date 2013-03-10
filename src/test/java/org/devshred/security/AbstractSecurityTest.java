package org.devshred.security;

import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("classpath:test-context.xml")
abstract public class AbstractSecurityTest {
    protected static final String USER = "user";
    protected static final String ADMIN = "admin";

    @Autowired
    private AuthenticationManager authenticationManager;

    @After
    public void clear() {
        SecurityContextHolder.clearContext();
    }

    protected void loginAs(final String user) {
        Authentication auth = new UsernamePasswordAuthenticationToken(user, user);
        SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(auth));
    }
}
