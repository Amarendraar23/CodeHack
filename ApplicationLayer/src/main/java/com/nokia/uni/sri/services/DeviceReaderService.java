package com.nokia.uni.sri.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nokia.uni.sri.domain.Device;
import com.nokia.uni.sri.domain.DeviceReader;
import com.nokia.uni.sri.domain.Location;
import com.nokia.uni.sri.domain.ObservableEntity;
import com.nokia.uni.sri.repositories.DeviceReaderRepository;
import com.nokia.uni.sri.repositories.DeviceRepository;
import com.nokia.uni.sri.repositories.LocationRepository;
import com.nokia.uni.sri.repositories.ObservableEntityRepository;

@Service
public class DeviceReaderService {
	@Autowired
	DeviceReaderRepository deviceReaderRepository;
	@Autowired
	ObservableEntityRepository observableEntityRepository;
	@Autowired
	LocationRepository locationRepository;
	@Autowired
	DeviceRepository deviceRepository;
	@Transactional
	
    public DeviceReader registerDevice(DeviceReader deviceReader) {
		
	    DeviceReader ldeviceReader = new DeviceReader(deviceReader.getName());
	    Location llocation = locationRepository.findByName(deviceReader.getLocation().getName());
		if(llocation==null)
			deviceReaderRepository.save(ldeviceReader);
		else {
			ldeviceReader.setLocation(llocation);
			deviceReaderRepository.save(ldeviceReader);
		}
        return ldeviceReader;
    }
	
	@Transactional
	public ObservableEntity foundDevice(Device device) {
		ObservableEntity lobservableEntity = observableEntityRepository.findDevice(device.getName());
		Location llocation = locationRepository.findByName(device.getLocation().getName());
		lobservableEntity.setLocation(llocation);
		observableEntityRepository.save(lobservableEntity);
		return lobservableEntity;
	}
}
