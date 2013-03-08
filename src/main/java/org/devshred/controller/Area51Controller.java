package org.devshred.controller;

import java.util.logging.Logger;

import org.devshred.service.Area51Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@PreAuthorize("denyAll")
@RequestMapping("/area51")
public class Area51Controller {
	private static final Logger LOGGER = Logger.getLogger(Area51Controller.class.getName());

	@Autowired
	private Area51Service service;

	@PreAuthorize("isAuthenticated()")
	@RequestMapping
	public ModelAndView homePage() {
		LOGGER.info("request homepage");

		final ModelAndView modelAndView = new ModelAndView("area51/greeting");
		modelAndView.addObject("username", service.greeting());
		return modelAndView;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/status")
	public ModelAndView status() {
		LOGGER.info("request status");

		final ModelAndView modelAndView = new ModelAndView("area51/status");
		modelAndView.addObject("status", service.status());
		return modelAndView;
	}
}
