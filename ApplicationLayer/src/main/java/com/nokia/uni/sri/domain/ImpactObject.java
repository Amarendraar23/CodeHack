package com.nokia.uni.sri.domain;

import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity
public class ImpactObject
{
    private Reports[] reports;

    private String[] updates;

    private String[] deregistrations;

    private Registrations[] registrations;

    private String[] expirations;

    private String[] responses;
}
			
