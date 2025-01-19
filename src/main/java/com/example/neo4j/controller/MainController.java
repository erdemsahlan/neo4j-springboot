package com.example.neo4j.controller;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("neo")
public class MainController {

    @Autowired
    Driver driver;

    @GetMapping("/addActorToMovie/{movie}/{actor}")
    public ResponseEntity<?> addActorToMovie(@PathVariable String movie, @PathVariable String actor) {
        String query = "MATCH (n:Movie) WHERE n.title = '"+ movie +"' create (n)<-[:ACTED_IN]-(a:Person {name:'"+actor+"'})";
        try (Session session = driver.session()) {
            session.run(query);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            throw e;
        }
    }
}
