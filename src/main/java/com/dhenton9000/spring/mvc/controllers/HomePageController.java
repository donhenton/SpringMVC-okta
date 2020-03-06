package com.dhenton9000.spring.mvc.controllers;

import com.dhenton9000.spring.mvc.auth.OktaFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.*;
import org.springframework.ui.Model;

/**
 * The controller for the homepage
 *
 * @author Don
 *
 */
@Controller
public class HomePageController {

    private static Logger log = LogManager.getLogger(HomePageController.class);

    @RequestMapping("/")
    public ModelAndView homePage(HttpServletRequest request, HttpServletResponse response) {

        Object message = request.getSession().getAttribute(OktaFilter.USER_SESSION_KEY);
        return new ModelAndView("tiles.homepage", "message", message);
    }

    @RequestMapping("/profile/display")
    public String profilePage(Model model, HttpServletRequest request) {

        Object message = request.getSession().getAttribute(OktaFilter.USER_SESSION_KEY);

        model.addAttribute("message", message);
        return "tiles.profile.page";
    }

}
