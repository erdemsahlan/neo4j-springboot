package com.example.neo4j;
import com.example.neo4j.Repository.IMovieRepository;
import com.example.neo4j.entity.Movie;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories(considerNestedRepositories = true)
public class Neo4jApplication  implements CommandLineRunner {

    @Autowired
    Driver driver;

    @Autowired
    IMovieRepository movieRepository;

    public static void main(String[] args) {
        SpringApplication.run(Neo4jApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
//        try(var session = driver.session()) {
//            session.run("MATCH (n:Sahis) return n.adi").list().forEach(System.out::println);
//        }
//        try {
//            Movie actor = actorRepository.findById("Tom Hanks").get();
//            System.out.println(actor);
//        }catch (Exception e){
//            throw e;
//        }
        try{
            Movie movie = movieRepository.titleQuery("The Matrix");
            System.out.println(movie);
        }catch (Exception e){
            throw e;
        }

    }

}
