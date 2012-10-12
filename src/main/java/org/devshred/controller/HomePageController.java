package org.devshred.controller;

import org.devshred.service.Area51Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Controller
@RequestMapping("/area51")
public class HomePageController {
    private static final Logger LOGGER = Logger.getLogger(HomePageController.class.getName());

    @Autowired
    private Area51Service service;

    //@PreAuthorize("hasPermission('ROLE_USER')")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView homePage() {
        LOGGER.info("starting controller");
        final ModelAndView modelAndView = new ModelAndView("area51");
        modelAndView.addObject("greeting", service.greeting());
        return modelAndView;
    }
}