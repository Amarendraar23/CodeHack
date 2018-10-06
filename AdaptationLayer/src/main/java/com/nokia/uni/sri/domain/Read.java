package com.nokia.uni.sri.domain;

import java.util.List;

import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity
public class Read {
	private String resources;

    private String[] endPointClientNames;
}
