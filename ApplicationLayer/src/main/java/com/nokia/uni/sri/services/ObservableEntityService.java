package com.nokia.uni.sri.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nokia.uni.sri.domain.Location;
import com.nokia.uni.sri.domain.ObservableEntity;
import com.nokia.uni.sri.repositories.LocationRepository;
import com.nokia.uni.sri.repositories.ObservableEntityRepository;

@Service
public class ObservableEntityService {
	@Autowired
	ObservableEntityRepository observableEntityRepository;
	@Autowired
	LocationRepository locationRepository;
	@Transactional
	public ObservableEntity registerObservableEntity(ObservableEntity observableEntity) {

		ObservableEntity lobservableEntity = new ObservableEntity(observableEntity.getName(), observableEntity.getAddress(), observableEntity.getBusRoute(),observableEntity.getLocation());
		Location llocation = locationRepository.findByName(observableEntity.getLocation().getName());
		if(llocation==null)
			observableEntityRepository.save(lobservableEntity);
		else {
			lobservableEntity.setLocation(llocation);
			observableEntityRepository.save(lobservableEntity);
		}
		return lobservableEntity;
	}
	
	/*@Transactional
	public ObservableEntity updateObservableEntityLocation(ObservableEntity observableEntity) {
		ObservableEntity lobservableEntity = observableEntityRepository.findByName(observableEntity.getName());
		Location llocation = locationRepository.findByName(observableEntity.getLocation().getName());
		lobservableEntity.setLocation(llocation);
		observableEntityRepository.save(lobservableEntity);
		return lobservableEntity;
	}*/
}
