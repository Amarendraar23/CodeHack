package com.nokia.uni.sri.domain;

import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity
public class Reports {
	
	private String timestamp;

    private String resourcePath;

    private String value;

    private String serialNumber;

    private String subscriptionId;

}
