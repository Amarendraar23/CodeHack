package com.nokia.uni.sri.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class Entity {

    protected String uuid;

    protected String type;

    protected String discoveredName;

    protected String operation;//added by partial MDL upload feature


    protected String sureName;

    protected String contextId;

    protected String subType;

    protected String displayName;


    protected String creationDate;

    protected String lastUpdationDate;

    protected String primaryLabel;

//    protected Map<String, Object> properties = new HashMap<>();

//    protected Map<String, Object> allProperties = new HashMap<>();

    protected List<String> labels = new ArrayList<>();

    protected String entityKey;

    protected String tenantSureName;

    protected List<String> borrowedByTenant;

    protected List<String> candidateResourceOfTenant;

    protected String createdBy;

    protected String contextKey;

    protected String rootNetworkElement;

//    protected Map<String, String> associationCreatedBy = new HashMap<String, String>();

}
