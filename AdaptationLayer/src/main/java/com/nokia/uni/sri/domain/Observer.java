package com.nokia.uni.sri.domain;

import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import lombok.Data;

@NodeEntity
@Data
public class Observer{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private List<String> phoneNumbers;
    private String address;
    

    public Observer(String name, List<String> phoneNumbers,String address)
    {
    	this.name = name;
    	this.phoneNumbers = phoneNumbers;
    	this.address = address;
    }
    public Observer() {}

    @Relationship(type = "OBSERVES", direction = Relationship.OUTGOING)
    public List<ObservableEntity> observableEntities;
}
