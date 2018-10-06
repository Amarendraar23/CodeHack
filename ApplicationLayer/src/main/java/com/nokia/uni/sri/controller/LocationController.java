package com.nokia.uni.sri.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nokia.uni.sri.domain.Location;
import com.nokia.uni.sri.services.LocationService;

@RestController
@RequestMapping("/registerLocation")
public class LocationController {

	private final LocationService locationService;

	public LocationController(LocationService locationService) {
		this.locationService = locationService;
	}

	@PostMapping
	public Location registerLocation(@RequestBody Location location) {
		return locationService.registerLocation(location);
	}
}
