package com.example.neo4j.entity;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("Movie")
public class Movie {

    @Id
    private final String titles;

    @Relationship(type = "ACTED_IN", direction = Relationship.Direction.INCOMING)
    public List<Person> actors;

    @Relationship(type = "DIRECTED", direction = Relationship.Direction.INCOMING)
    public List<Person> directors;

    @Relationship(type = "PRODUCED", direction = Relationship.Direction.INCOMING)
    public List<Person> producers;

    public Movie(String titles) {
        this.titles = titles;
    }
}
