package com.nokia.uni.sri.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nokia.uni.sri.domain.ObservableEntity;
import com.nokia.uni.sri.services.ObservableEntityService;

@RestController

public class ObservableEntityController {

	private final ObservableEntityService observableEntityService;

	public ObservableEntityController(ObservableEntityService observableEntityService) {
		this.observableEntityService = observableEntityService;
	}

	@PostMapping("/registerObservableEntity")
	public ObservableEntity registerObservableEntity(@RequestBody ObservableEntity observableEntity) {
		return observableEntityService.registerObservableEntity(observableEntity);
	}
	/*
	@PostMapping("/updateLocation")
	public ObservableEntity updateObservableEntityLocation(@RequestBody ObservableEntity observableEntity) {
		return observableEntityService.updateObservableEntityLocation(observableEntity);
	}*/
}
