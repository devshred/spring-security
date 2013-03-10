package org.devshred.service.impl;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import java.util.Collection;
import java.util.List;

import org.devshred.exception.ObjectNotFoundException;
import org.devshred.model.Message;
import org.devshred.service.MessageService;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import static com.google.common.collect.Lists.newArrayList;


@Service
@PreAuthorize("isAuthenticated()")
public class MessageServiceImpl implements MessageService {
	private static final List<Message> messages = newArrayList();

	static {
		for (int i = 0; i < 10; i++) {
			final String owner = ((i % 2) == 0) ? "admin" : "user";
			messages.add(new Message(i, owner));
		}
	}

	@Override
    @PreAuthorize("#username == authentication.name or hasRole('ROLE_ADMIN')")
	public Collection<Message> findByUsername(final String username) {
		return Collections2.filter(messages, new Predicate<Message>() {
				@Override
				public boolean apply(final Message message) {
					return message.getOwner().equals(username);
				}
			});
	}

	@Override
	@PostAuthorize("hasPermission(returnObject, read) or hasRole('ROLE_ADMIN')")
	public Message findById(final int messageId) throws ObjectNotFoundException {
		try {
			return messages.get(messageId);
		} catch (IndexOutOfBoundsException e) {
			throw new ObjectNotFoundException();
		}
	}
}
