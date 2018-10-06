package com.nokia.uni.sri.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nokia.uni.sri.domain.Device;
import com.nokia.uni.sri.domain.DeviceReader;
import com.nokia.uni.sri.domain.ObservableEntity;
import com.nokia.uni.sri.services.DeviceReaderService;

@RestController

public class DeviceReaderController {

	private final DeviceReaderService deviceReaderService;

	public DeviceReaderController(DeviceReaderService deviceReaderService) {
		this.deviceReaderService = deviceReaderService;
	}

	@PostMapping("/registerDeviceReader")
	public DeviceReader registerDeviceReader(@RequestBody DeviceReader deviceReader) {
		return deviceReaderService.registerDevice(deviceReader);
	}
	@PostMapping("/foundDevice")
	public ObservableEntity foundDevice(@RequestBody Device device) {
		return deviceReaderService.foundDevice(device);
	}
}
