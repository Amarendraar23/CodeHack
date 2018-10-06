package com.nokia.uni.sri.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nokia.uni.sri.domain.Device;
import com.nokia.uni.sri.services.DeviceService;

@RestController
@RequestMapping("/registerDevice")
public class DeviceController {

	private final DeviceService deviceService;

	public DeviceController(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@PostMapping
	public Device registerDevice(@RequestBody Device device) {
		return deviceService.registerDevice(device);
	}
}
