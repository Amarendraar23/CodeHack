package com.nokia.uni.sri.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nokia.uni.sri.DataStore;
import com.nokia.uni.sri.SendSMS;
import com.nokia.uni.sri.Time;
import com.nokia.uni.sri.domain.Location;
import com.nokia.uni.sri.domain.ObservableEntity;
import com.nokia.uni.sri.domain.Observer;
import com.nokia.uni.sri.domain.Reports;
import com.nokia.uni.sri.repositories.DeviceRepository;
import com.nokia.uni.sri.repositories.LocationRepository;
import com.nokia.uni.sri.repositories.ObservableEntityRepository;

@Service
public class HackService {

	@Autowired
	DeviceRepository deviceRepository;
	@Autowired
	ObservableEntityRepository observableEntityRepository;
	@Autowired
	LocationRepository locationRepository;

	public void updateDevice(Reports reports) {
		ObservableEntity observableEntity  = observableEntityRepository.findDevice(reports.getSerialNumber());
		Location location = locationRepository.findByName(reports.getValue());
		Time time = DataStore.getDataStore().get(location.getName());
		int currentHour = LocalDateTime.now().getHour();
		System.out.println("CurrentHour is...."+currentHour);
		if(currentHour<time.getStartTime() ||currentHour>time.getEndTime())
			raiseAlert(observableEntity.getName(),location.getName());
		observableEntity.setLocation(location);
		observableEntityRepository.save(observableEntity);
	}

	private void raiseAlert(String kidName, String location) {
		Observer observer = observableEntityRepository.findObserver(kidName);
		System.err.println("Raising Alert..........................");
		SendSMS.sendSms("Kid "+kidName+" in wrong location "+location+" now");
	}
}
