package io.determind.controller;

import io.determind.persistence.service.UserServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/*
 *  @Controller annotation tells Spring to use this pojo as a page controller
 */
@Controller
public class HomeController {

    //private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    private final UserServiceInterface userService;

    /*
     *  @Autowired annotation in the constructor tells Spring to inject the given Bean object(s)
     */
    @Autowired
    public HomeController(UserServiceInterface userService) {
        this.userService = userService;
    }

    /*
     *  @RequestMapping maps the URI to the action method
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndexPage() {
//        LOGGER.debug("Getting index page");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(/*@RequestParam("error") Optional<String> error*/) {
        //LOGGER.debug("Getting login page, error={}", error);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
//        if (error.isPresent()) {
//            mav.addObject("error", error);
//        }

        return mav;
    }

}
