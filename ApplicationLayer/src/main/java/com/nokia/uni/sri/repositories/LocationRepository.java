package com.nokia.uni.sri.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.nokia.uni.sri.domain.Location;

public interface LocationRepository extends Neo4jRepository<Location, Long> {
    Location findByName(@Param("name") String name);
}