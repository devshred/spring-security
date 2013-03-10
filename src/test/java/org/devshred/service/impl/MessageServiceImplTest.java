package org.devshred.service.impl;

import org.devshred.exception.ObjectNotFoundException;
import org.devshred.model.Message;
import org.devshred.security.AbstractSecurityTest;
import org.devshred.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
public class MessageServiceImplTest extends AbstractSecurityTest {
    private static final int USER_MESSAGE_ID = 1;
    private static final int ADMIN_MESSAGE_ID = 2;

    @Autowired
    private MessageService service;


    @Test
    public void findAllMessagesOfUser() {
        loginAs(USER);
        Collection<Message> messages = service.findByUsername(USER);
        assertThat(messages, hasSize(5));
    }

    @Test(expected = AccessDeniedException.class)
    public void userCantRequestMessagesOfAnotherUser() {
        loginAs(USER);
        service.findByUsername(ADMIN);
    }

    @Test
    public void adminCanRequestMessagesOfAnotherUser() {
        loginAs(ADMIN);
        Collection<Message> messages = service.findByUsername(USER);
        assertThat(messages, hasSize(5));
    }

    @Test
    public void userIsPermittedToRequestItsOwnMessages() throws Exception {
        loginAs(USER);
        Message message = service.findById(USER_MESSAGE_ID);
        assertThat(message, notNullValue());
    }

    @Test(expected = AccessDeniedException.class)
    public void userIsNotPermittedToRequestAllMessages() throws Exception {
        loginAs(USER);
        service.findById(ADMIN_MESSAGE_ID);
    }

    @Test
    public void adminIsPermittedToRequestAllMessages() throws Exception {
        loginAs(ADMIN);
        service.findById(USER_MESSAGE_ID);
        service.findById(ADMIN_MESSAGE_ID);
    }

    @Test(expected = AuthenticationException.class)
    public void anonymousIsNotPermittedToRequestMessages() throws Exception {
        service.findById(USER_MESSAGE_ID);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void handleRequestsOfNonExistingMessages() throws Exception {
        loginAs(ADMIN);
        service.findById(10);
    }
}
