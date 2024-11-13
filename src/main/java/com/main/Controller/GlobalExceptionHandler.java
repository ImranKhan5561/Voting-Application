package com.main.Controller;



import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        // Log the exception
        ex.printStackTrace();

        // Add exception details to the model
        model.addAttribute("errorMessage", ex.getMessage());

        // Return the name of the error view
        return "access-denied";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFound(Exception ex, Model model) {
        // Log the exception
        ex.printStackTrace();

        // Add exception details to the model
        model.addAttribute("errorMessage", "Page not found");

        // Return a custom 404 error page
        return "404";
    }
}
