package com.nokia.uni.sri.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nokia.uni.sri.domain.Location;
import com.nokia.uni.sri.domain.ObservableEntity;
import com.nokia.uni.sri.domain.Observer;
import com.nokia.uni.sri.repositories.ObservableEntityRepository;
import com.nokia.uni.sri.repositories.ObserverRepository;

@Service
public class ObserverService {
	@Autowired
	ObserverRepository observerRepository;
	@Autowired
	ObservableEntityRepository observableEntityRepository;
	@Transactional
	public Observer registerObserver(Observer observer) {

		Observer lobserver = new Observer(observer.getName(), observer.getPhoneNumbers(), observer.getAddress());
		ArrayList<ObservableEntity> observableEntities = new ArrayList<>();
		for(ObservableEntity lobservableEntity : observer.getObservableEntities()) {
			ObservableEntity oEntity = observableEntityRepository.findByName(lobservableEntity.getName());
			if(oEntity!=null)
				observableEntities.add(oEntity);
			else
				observableEntities.add(lobservableEntity);
		}
		lobserver.setObservableEntities(observableEntities);
		observerRepository.save(lobserver);
		return lobserver;
	}
}