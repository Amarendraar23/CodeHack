package com.nokia.uni.sri.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@NodeEntity
@Data
public class Location
{
	@Id  @GeneratedValue Long id;
	private String name;
	private String address;

	public Location(String name,String address)
	{
		this.name = name;
		this.address = address;
	}

	public Location() {
	}
}