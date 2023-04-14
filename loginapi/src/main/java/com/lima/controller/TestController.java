package com.lima.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
@CrossOrigin("http://localhost:3000")
public class TestController {

	@GetMapping("/test1")
	public String test() {
		System.out.println("test controller");
		return "test";
	}
}
