package com.czy.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler({ArithmeticException.class})
    public String handleArithmeticException(Exception e, Model model) {
        System.out.println("occur ex: " + e);
        model.addAttribute("exception", e);
        return "error";
    }
}
