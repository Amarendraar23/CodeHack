package com.nokia.uni.sri.domain;

import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity
public class Response {

	private String endPointClientName;
	private String location;
}
