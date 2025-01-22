package com.example.neo4j;
import com.example.neo4j.Repository.IMovieRepository;
import com.example.neo4j.entity.Movie;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

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
//        try{
//            Movie movie = movieRepository.titleQuery("The Matrix");
//            System.out.println(movie);
//        }catch (Exception e){
//            throw e;
//        }

        Thread t1 = new Thread(()->{
            func1();
        });
        Thread t2 = new Thread(()->{
            func2();
        });
        Thread t3 = new Thread(()->{
            func3();
        });
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

    }

    @Transactional
    public void func1(){
        for(var i = 50000; i < 55000; i++){
            String query = "merge (m:Movie{custom:"+i+"})" +
                    "merge(n:Movie{custom:"+(i+1)+"})" +
                    "merge(m)-[r:bond]->(n)";
            try (Session session = driver.session()) {
                session.run(query);
                System.err.println("FUNC1-->" + i);
            }catch (Exception e){
                throw e;
            }
        }
        System.err.println("FUNC1-->END");
    }

    @Transactional
    public void func2(){
        for(var i = 55000; i < 60000; i++){
            String query = "merge (m:Movie{custom:"+i+"})" +
                    "merge(n:Movie{custom:"+(i+1)+"})" +
                    "merge(m)-[r:bond]->(n)";
            try (Session session = driver.session()) {
                session.run(query);
                System.err.println("FUNC2-->" + i);
            }catch (Exception e){
                throw e;
            }
        }
        System.err.println("FUNC2-->END");
    }
    @Transactional
    public void func3(){
        for(var i = 65000; i < 65000; i++){
            String query = "merge (m:Person{custom:"+i+"})" +
                    "merge(n:Sahis{custom:"+(i+1)+"})" +
                    "merge(m)-[r:bond]->(n)";
            try (Session session = driver.session()) {
                session.run(query);
                System.err.println("FUNC3-->" + i);
            }catch (Exception e){
                throw e;
            }
        }
        System.err.println("FUNC3-->END");
    }

}
