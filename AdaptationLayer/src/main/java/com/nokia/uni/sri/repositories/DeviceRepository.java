package com.nokia.uni.sri.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.nokia.uni.sri.domain.Device;

public interface DeviceRepository extends Neo4jRepository<Device,Long> {
    Device findByName(@Param("name") String name);
}