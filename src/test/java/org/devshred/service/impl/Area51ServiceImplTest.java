package org.devshred.service.impl;

import org.devshred.security.AbstractSecurityTest;
import org.devshred.service.Area51Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
public class Area51ServiceImplTest extends AbstractSecurityTest {
    @Autowired
    Area51Service service;

    @Test
    public void greeting(){
        loginAs(USER);
        assertThat(service.greeting(), is(USER));
    }
}
