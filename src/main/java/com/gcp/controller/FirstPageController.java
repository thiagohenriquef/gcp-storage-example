package com.gcp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstPageController {

	@RequestMapping("/")
    public String firstPage() {
        System.out.println("******************FIRST PAGE************************");
        return "This is the first page!";
    }

}