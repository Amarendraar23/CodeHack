package com.nokia.uni.sri.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.nokia.uni.sri.domain.Hack;

public interface HackRepository extends Neo4jRepository<Hack, Long> {

}