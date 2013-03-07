package org.devshred.service;

import org.devshred.exception.ObjectNotFoundException;
import org.devshred.model.Message;

import java.util.Collection;

public interface MessageService {
    Collection<Message> findByUsername(String username);

    Message findById(int messageId) throws ObjectNotFoundException;
}
