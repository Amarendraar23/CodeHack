package com.nokia.uni.sri.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Hack {
    @Id  @GeneratedValue Long id;

    public Hack() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}