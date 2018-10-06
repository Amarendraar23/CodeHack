package com.nokia.uni.sri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories("com.nokia.uni.sri.repositories")
public class SriApplication {

    public static void main(String[] args) {
        SpringApplication.run(SriApplication.class, args);
    }
}