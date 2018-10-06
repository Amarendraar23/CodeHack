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
		
	    ObservableEntity lobservableEntity = observableEntityRepository.findByName(device.getObservableEntity().getName());
		if(lobservableEntity==null)
			deviceRepository.save(device);
		else {
			device.setObservableEntity(lobservableEntity);;
			deviceRepository.save(device);
		}
        return device;
    }
}
