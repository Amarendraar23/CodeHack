package com.nokia.uni.sri.domain;

import java.util.Map;

import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity
public class Registrations {

	private String tags;

    private String timestamp;

    private String protocol;

    private String groupName;

    private Map<String,String> deviceProps;

    private String deviceType;

    private String serialNumber;

    private String imsi;

    private String subscriptionId;
}
