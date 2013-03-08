package org.devshred.security;

import java.io.Serializable;

import java.util.logging.Logger;

import org.devshred.model.Message;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;


public class MessagePermissionEvaluator implements PermissionEvaluator {
	private static final Logger LOGGER = Logger.getLogger(MessagePermissionEvaluator.class.getName());

	@Override
	public boolean hasPermission(final Authentication authentication, final Object targetDomainObject,
		final Object permission) {
		if (permission.equals("read")) {
			final Message message = (Message) targetDomainObject;
			if (message.getOwner().equals(authentication.getName())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean hasPermission(final Authentication authentication, final Serializable targetId,
		final String targetType, final Object permission) {
		LOGGER.info(authentication.getName());
		return true;
	}
}
