package com.dhenton9000.spring.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.*;
import org.springframework.ui.Model;


/**
 * The controller for the homepage
 * @author Don
 *
 */
@Controller
public class HomePageController {
	
	
	
	private static Logger log = LogManager.getLogger(HomePageController.class);
	
	
	@RequestMapping("/")
	public ModelAndView homePage() {
		String message = "Hello World, Spring 3.0!";
		return new ModelAndView("tiles.homepage", "message", message);
	}
	
	@RequestMapping("/profile/display")
	public String profilePage(Model model) {
		model.addAttribute("message","stuff");
		return  "tiles.profile.page";
	}	
	
}
