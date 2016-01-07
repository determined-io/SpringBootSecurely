package io.determind.controller;

import io.determind.persistence.springsecurity.SBSUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProtectedController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProtectedController.class);

    @RequestMapping("/protected")
    public ModelAndView getProtectedPage(@AuthenticationPrincipal SBSUser sbsUser) {
        LOGGER.debug("Getting protected page");
        if (sbsUser != null) {
            LOGGER.debug(sbsUser.toString());
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName("protected");
        mav.addObject("sbsUser", sbsUser);
        return mav;
    }

}
