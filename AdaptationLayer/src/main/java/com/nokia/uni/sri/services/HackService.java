package com.nokia.uni.sri.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nokia.uni.sri.domain.Hack;
import com.nokia.uni.sri.repositories.HackRepository;

@Service
public class HackService {

    private final static Logger LOG = LoggerFactory.getLogger(HackService.class);

	private final HackRepository hackRepository;

	public HackService(HackRepository hackRepository) {
		this.hackRepository = hackRepository;
	}


    @Transactional
    public Hack createHack() {
    	
    	
	    return new Hack();
    }


}
