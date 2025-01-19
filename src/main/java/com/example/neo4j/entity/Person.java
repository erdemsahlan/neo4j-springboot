package com.example.neo4j.entity;


import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("Person")
public class Person {
    @Id
    public final String name;
    public final Integer born;

    public Person(String name, Integer born) {
        this.name = name;
        this.born = born;
    }
}
