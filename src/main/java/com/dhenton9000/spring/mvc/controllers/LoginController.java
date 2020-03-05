package com.dhenton9000.spring.mvc.controllers;

import com.dhenton9000.spring.mvc.auth.AuthenticationStateHandler;
import com.dhenton9000.spring.mvc.auth.OktaFilter;
import com.okta.authn.sdk.AuthenticationException;
import com.okta.authn.sdk.client.AuthenticationClient;
import com.okta.authn.sdk.resource.AuthenticationResponse;
import com.okta.authn.sdk.resource.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/authn/")
@SessionAttributes("loginForm")
public class LoginController {

    private static Logger LOG = LogManager.getLogger(LoginController.class);

    @Autowired
    private AuthenticationClient authenticationClient;

    @RequestMapping("/login")
    public String doLogin(Model model) {

        LoginForm loginForm = new LoginForm();
        /*
       
         */
        /// model.addAttribute("message",message);
        model.addAttribute("loginForm", loginForm);
        return "tiles.authn.login";
    }

    @RequestMapping("/processLogin")
    public String doLoginWork(@ModelAttribute("loginForm") LoginForm form,
            HttpServletRequest request, HttpServletResponse response, Model model) {
        String password = form.getPassword();
        String username = form.getUserEmail();
        try {

            AuthenticationResponse authRep = authenticationClient.authenticate(username,
                    password.toCharArray(),
                    "/",
                    new AuthenticationStateHandler(request, response));
            User u = authRep.getUser();
            Object ss = request.getSession()
                    .getAttribute(OktaFilter.USER_SESSION_KEY);

            /*
          populate the context holder with the User u object above
           SecurityContextHolder.getContext().setAuthentication(authentication);
             */
            //           Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//                if (auth == null) {
//                   // auth = new Authentication();
//                    
//                    SecurityContextHolder.getContext().setAuthentication(a);
//                }
            //             Object secObject = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("message", authRep.getUser().getLogin());
            return "tiles.homepage";
        } catch (AuthenticationException ex) {
            LOG.error("Auth error " + ex.getMessage());
            return "redirect:/authn/login?status=failedLogin";
        }
      
    }

    @RequestMapping("/logout")
    public String doLogout(HttpServletRequest request, HttpServletResponse response) {
        String message = "Logout";

        if (request.getSession(false) != null) {
            request.getSession().invalidate();
        }
        return "redirect:/authn/login?status=logoutSuccess";
    }

}
