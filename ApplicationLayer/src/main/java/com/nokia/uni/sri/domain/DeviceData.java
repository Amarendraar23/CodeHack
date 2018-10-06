package com.nokia.uni.sri.domain;

import java.util.List;

import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@NodeEntity
@Data
public class DeviceData {
	private String correlatorId;
	private List<Read> read;
}
