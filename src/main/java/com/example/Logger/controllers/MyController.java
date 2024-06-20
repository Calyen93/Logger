package com.example.Logger.controllers;

import com.example.Logger.exceptions.CustomErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MyController {

    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    @Value("${custom.env.var1}")
    private int var1;

    @Value("${custom.env.var2}")
    private int var2;

    @GetMapping("/")
    public String welcome() {
        logger.info("Welcome endpoint accessed");
        return "Welcome to my Spring Boot application!";
    }

    @GetMapping("/exp")
    public String calculateExponent() {
        logger.debug("Calculating exponent for var1 = {} and var2 = {}", var1, var2);
        logger.info("Starting calculation");
        double result = Math.pow(var1, var2);
        logger.info("Calculation finished");
        return String.format("%d^%d = %.2f", var1, var2, result);
    }

    @GetMapping("/get-errors")
    public String getErrors() {
        logger.error("Custom error occurred");
        throw new CustomErrorException("Custom error occurred");
    }
}