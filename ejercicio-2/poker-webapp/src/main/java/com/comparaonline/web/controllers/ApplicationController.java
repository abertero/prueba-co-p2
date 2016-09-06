package com.comparaonline.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/*")
public class ApplicationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home() {
        LOGGER.debug("Called home controller");
        ModelAndView mv = new ModelAndView("home");
        return mv;
    }
}
