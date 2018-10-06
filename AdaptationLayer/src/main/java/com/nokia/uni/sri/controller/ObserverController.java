package com.nokia.uni.sri.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nokia.uni.sri.domain.Observer;
import com.nokia.uni.sri.services.ObserverService;

@RestController
@RequestMapping("/registerObserver")
public class ObserverController {

	private final ObserverService observerService;

	public ObserverController(ObserverService observerService) {
		this.observerService = observerService;
	}

	@PostMapping
	public Observer registerObserver(@RequestBody Observer observer) {
		return observerService.registerObserver(observer);
	}
}
