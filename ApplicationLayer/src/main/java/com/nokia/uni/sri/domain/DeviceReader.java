package com.nokia.uni.sri.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import lombok.Data;

@NodeEntity
@Data
public class DeviceReader
{
	@Id  @GeneratedValue Long id;
	private String name;

	public DeviceReader(String name)
	{
		this.name = name;
	}

	public DeviceReader() {
	}
	@Relationship(type = "RESIDE_IN", direction = Relationship.OUTGOING)
	public Location location;
}