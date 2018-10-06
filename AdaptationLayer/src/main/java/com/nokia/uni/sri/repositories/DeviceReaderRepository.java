package com.nokia.uni.sri.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.nokia.uni.sri.domain.DeviceReader;

public interface DeviceReaderRepository extends Neo4jRepository<DeviceReader,Long> {
    DeviceReader findByName(@Param("name") String name);
}