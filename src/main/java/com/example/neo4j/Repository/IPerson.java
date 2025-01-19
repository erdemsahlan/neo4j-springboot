package com.example.neo4j.Repository;

import com.example.neo4j.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface IPerson extends Neo4jRepository<Person, String> {
}
