package com.nokia.uni.sri.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import lombok.Data;

@NodeEntity
@Data
public class Device
{
	@Id  @GeneratedValue Long id;
	private String name;
	private Location location;
	
	public Device(String name)
	{
		this.name = name;
	}

	public Device() {
	}
	@Relationship(type = "TAGGED", direction = Relationship.OUTGOING)
	public ObservableEntity observableEntity;
}