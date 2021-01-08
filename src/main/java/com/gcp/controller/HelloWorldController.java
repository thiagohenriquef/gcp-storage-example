package com.gcp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcp.model.Message;

@RestController
public class HelloWorldController {

	@GetMapping("/hello-world")
	public Message helloWorld(@RequestParam(value = "user", defaultValue = "New User!") String user) {
		return new Message(String.format("Hello World! %s", user));
	}
}
