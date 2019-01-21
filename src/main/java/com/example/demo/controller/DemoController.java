package com.example.demo.controller;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping(method=RequestMethod.GET, value="/get")
	public String demoMethod() {
		
		List numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		
		return "Hello";
	}
}
