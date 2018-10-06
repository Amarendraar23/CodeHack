package com.nokia.uni.sri.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.nokia.uni.sri.domain.ObservableEntity;

public interface ObservableEntityRepository extends Neo4jRepository<ObservableEntity, Long> {
	ObservableEntity findByName(@Param("name") String name);
	@Query("match (n:ObservableEntity),(m:Device),(n)<-[r:TAGGED]-(m) where m.name={name} return n")
	ObservableEntity findDevice(@Param("name") String name);
}