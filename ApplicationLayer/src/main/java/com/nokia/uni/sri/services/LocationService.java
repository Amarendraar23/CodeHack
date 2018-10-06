package com.nokia.uni.sri.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nokia.uni.sri.domain.Location;
import com.nokia.uni.sri.repositories.LocationRepository;

@Service
public class LocationService {
	@Autowired
	LocationRepository locationRepository;
	@Transactional
    public Location registerLocation(Location location) {
		
	    Location llocation = new Location(location.getName(), location.getAddress());
	    locationRepository.save(llocation);
	    
        return llocation;
    }
}
