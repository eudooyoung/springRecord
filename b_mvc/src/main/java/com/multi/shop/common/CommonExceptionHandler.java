package com.multi.shop.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.multi.shop")
public class CommonExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ModelAndView exceptionHandler(Exception ex) {
        ModelAndView view = new ModelAndView();

        view.setViewName("common/errorPage");
        view.addObject("msg", ex.getMessage());

        return view;
    }
}
