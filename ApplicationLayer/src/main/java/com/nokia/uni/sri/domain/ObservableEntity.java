package com.nokia.uni.sri.domain;

import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import lombok.Data;

@NodeEntity
@Data
public class ObservableEntity {

	@Id 
	@GeneratedValue
	Long id;

	private String name;
	private String address;
	private String busRoute;

	public ObservableEntity(String name, String address,String busRoute, Location location)
	{
		this.name = name;
		this.address = address;
		this.busRoute = busRoute;
		if(location!=null)
			this.location = new Location(location.getName(),location.getAddress());
	}
	public ObservableEntity() {}

	@Relationship(type = "BELONGS_TO", direction = Relationship.OUTGOING)
	public List<Observer> observers;

	@Relationship(type = "LOCATED_AT", direction = Relationship.OUTGOING)
	public Location location ;
}
