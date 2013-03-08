package org.devshred.service;

import java.util.Collection;

import org.devshred.exception.ObjectNotFoundException;
import org.devshred.model.Message;


public interface MessageService {
	Collection<Message> findByUsername(String username);

	Message findById(int messageId) throws ObjectNotFoundException;
}
