package com.peisia.spring.mi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peisia.dto.Cat;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/api/*")
@AllArgsConstructor
@RestController
public class ApiController {
	@GetMapping("/cat")
	public Cat cat() {
		Cat cat = new Cat("야옹이", 7);
		cat.hobby.add("식빵굽기");
		cat.hobby.add("잠자기");
		return cat;
	}
}