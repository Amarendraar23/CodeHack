package com.nokia.uni.sri.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.nokia.uni.sri.domain.Observer;

public interface ObserverRepository extends Neo4jRepository<Observer, Long> {
//    Observer findBySureName(@Param("name") String name);
}