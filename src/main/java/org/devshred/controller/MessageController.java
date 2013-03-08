package org.devshred.controller;

import org.devshred.exception.ObjectNotFoundException;
import org.devshred.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@PreAuthorize("denyAll")
public class MessageController {
	@Autowired
	private MessageService service;

	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/message/{messageId}")
	public ModelAndView showMessage(@PathVariable
		final int messageId) {
		final ModelAndView modelAndView = new ModelAndView("message/show");
		try {
			modelAndView.addObject("message", service.findById(messageId));
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/messages")
	public ModelAndView listMessages() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final String username = auth.getName();
		final ModelAndView modelAndView = new ModelAndView("message/list");
		modelAndView.addObject("messages", service.findByUsername(username));
		return modelAndView;
	}
}
