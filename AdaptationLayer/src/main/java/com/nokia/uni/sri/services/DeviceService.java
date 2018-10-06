package com.nokia.uni.sri.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nokia.uni.sri.domain.Device;
import com.nokia.uni.sri.domain.ObservableEntity;
import com.nokia.uni.sri.repositories.DeviceRepository;
import com.nokia.uni.sri.repositories.ObservableEntityRepository;

@Service
public class DeviceService {
	@Autowired
	DeviceRepository deviceRepository;
	@Autowired
	ObservableEntityRepository observableEntityRepository;
	@Transactional
    public Device registerDevice(Device device) {
		
	    Device ldevice = new Device(device.getName());
	    ObservableEntity lobservableEntity = observableEntityRepository.findByName(device.getObservableEntity().getName());
		if(lobservableEntity==null)
			deviceRepository.save(ldevice);
		else {
			ldevice.setObservableEntity(lobservableEntity);;
			deviceRepository.save(ldevice);
		}
        return ldevice;
    }
	public Device getDeviceDetails(String deviceName) {
		Device device = deviceRepository.findByName(deviceName);
		return device;
	}
}
