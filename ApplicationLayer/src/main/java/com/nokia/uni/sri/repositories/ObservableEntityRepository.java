package com.nokia.uni.sri.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.nokia.uni.sri.domain.ObservableEntity;
import com.nokia.uni.sri.domain.Observer;

public interface ObservableEntityRepository extends Neo4jRepository<ObservableEntity, Long> {
	ObservableEntity findByName(@Param("name") String name);
	@Query("match (n:ObservableEntity),(m:Device),(n)<-[r:TAGGED]-(m),(a:Location),(n)-[s:LOCATED_AT]->(a) where m.name={name} return n,s,a")
	ObservableEntity findDevice(@Param("name") String name);
	@Query("match(n:ObservableEntity),(m:Observer),(m)-[:OBSERVES]->(n) where n.name = {name} return m")
	Observer findObserver(@Param("name") String name);
}