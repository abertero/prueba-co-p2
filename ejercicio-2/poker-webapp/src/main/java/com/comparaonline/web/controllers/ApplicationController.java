package com.comparaonline.web.controllers;

import com.comparaonline.beans.Deck;
import com.comparaonline.beans.Hand;
import com.comparaonline.web.services.JSONClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/*")
public class ApplicationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);
    public static final String SESSION_ATTR_DECK = "DECK";

    @Autowired
    private JSONClientService jsonClientService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home() {
        LOGGER.debug("Called home controller");
        return new ModelAndView("home");
    }

    @RequestMapping(value = "game", method = RequestMethod.GET)
    public ModelAndView game(@RequestParam(required = false, defaultValue = "false") Boolean shuffle) {
        LOGGER.debug("Called game controller");
        ModelAndView mv = new ModelAndView("game");
        Deck deck = getFromSession(shuffle);
        mv.addObject("deck", deck);
        Hand hand1 = deck.getHand();
        Hand hand2 = deck.getHand();
        if (hand1 == null || hand2 == null) {
            mv.addObject("notEnoughCards", true);
        } else {
            int comparison = hand1.compareTo(hand2);
            mv.addObject("comparison", comparison);
            mv.addObject("hand1", hand1);
            mv.addObject("hand2", hand2);
        }
        return mv;
    }

    private Deck getFromSession(Boolean shuffle) {
        Deck deck = (Deck) request.getSession().getAttribute(SESSION_ATTR_DECK);
        if (shuffle || deck == null) {
            LOGGER.info("No deck in session, getting a new one.");
            deck = jsonClientService.getDeck();
            putInSession(deck);
        }
        LOGGER.info(String.format("Get deck from session: %s", deck));
        return deck;
    }

    private void putInSession(Deck deck) {
        LOGGER.info(String.format("Put deck in session: %s", deck));
        request.getSession().setAttribute(SESSION_ATTR_DECK, deck);
    }
}
