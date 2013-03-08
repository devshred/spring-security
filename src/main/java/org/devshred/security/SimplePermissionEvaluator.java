package org.devshred.security;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;


public class SimplePermissionEvaluator implements PermissionEvaluator {
	@Override
	public boolean hasPermission(final Authentication authentication, final Object o, final Object o1) {
		return true;
	}

	@Override
	public boolean hasPermission(final Authentication authentication, final Serializable serializable, final String s,
		final Object o) {
		return true;
	}
}
