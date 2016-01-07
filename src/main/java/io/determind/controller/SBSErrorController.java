package io.determind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by brianbahner on 12/14/15.
 */
@Controller
public class SBSErrorController implements ErrorController {
    private static final String PATH = "/error";


    @Value("true")
    private boolean debug;

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value=PATH)
    public ModelAndView error(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> map = getErrorAttributes(request, debug);
//        Enumeration names = request.getAttributeNames();
//        Principal user = request.getUserPrincipal();
        ModelAndView mav = new ModelAndView();

        int status = response.getStatus();
        if (status == 403) {
            // HTTP 403 happens when CSRF token is invalid
            mav.setViewName("login");
            map.put("message", "Just a moment while we refresh your session. Please sign in again.");
        } else if (status == 404) {
            mav.setViewName("error");
            map.put("message", "The requested resource was not found.");
        } else {
            mav.setViewName("error");
        }

        mav.addAllObjects(map);
        return mav;
    }

    // Spring Security failureUrl maps here in SecurityConfig
    @RequestMapping(value = "/loginerror", method = RequestMethod.GET)
    public ModelAndView loginerror(HttpServletRequest request, HttpServletResponse response) {

//        Enumeration names = request.getAttributeNames();
//        Principal user = request.getUserPrincipal();
//        int status = response.getStatus();

        Map<String, Object> map = getErrorAttributes(request, debug);
        map.put("message", "You have entered an invalid Email address or Password. Please sign in again.");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        mav.addAllObjects(map);

        return mav;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }

}
