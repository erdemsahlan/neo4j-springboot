package com.example.neo4j;

import com.example.neo4j.Repository.IMovieRepository;
import com.example.neo4j.entity.Ticket;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Thread.sleep;

@SpringBootApplication
@EnableNeo4jRepositories(considerNestedRepositories = true)
public class Neo4jApplication implements CommandLineRunner {

    @Autowired
    Driver driver;

    @Autowired
    IMovieRepository movieRepository;

    public static void main(String[] args) {
        SpringApplication.run(Neo4jApplication.class, args);
    }

    @Transactional
    public void func1() throws InterruptedException {
        System.err.println("FUNC1-->Start");
        sleep(1000);
        for (var i = 1000000; i < 1100000; i++) {
            String query = "merge (m:Movie{custom:" + i + "})" +
                    "merge(n:Movie{custom:" + (i + 1) + "})" +
                    "merge(m)-[r:bond]->(n)";
            try (Session session = driver.session()) {
                session.run(query);
                System.err.println("FUNC1-->" + i);
            } catch (Exception e) {
                throw e;
            }
        }
        System.err.println("FUNC1-->END");
    }

    @Transactional
    public void func2() throws InterruptedException {
        System.err.println("FUNC2-->Start");
        sleep(1000);
        for (var i = 1100000; i < 1200000; i++) {
            String query = "merge (m:Movie{custom:" + i + "})" +
                    "merge(n:Movie{custom:" + (i + 1) + "})" +
                    "merge(m)-[r:bond]->(n)";
            try (Session session = driver.session()) {
                session.run(query);
                System.err.println("FUNC2-->" + i);
            } catch (Exception e) {
                throw e;
            }
        }
        System.err.println("FUNC2-->END");
    }

    @Transactional
    public void func3() throws InterruptedException {
        System.err.println("FUNC3-->Start");
        sleep(1000);
        for (var i = 1200000; i < 1300000; i++) {
            String query = "merge (m:Person{custom:" + i + "})" +
                    "merge(n:Person{custom:" + (i + 1) + "})" +
                    "merge(m)-[r:bond]->(n)";
            try (Session session = driver.session()) {
                session.run(query);
                System.err.println("FUNC3-->" + i);
            } catch (Exception e) {
                throw e;
            }
        }
        System.err.println("FUNC3-->END");
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

//        Thread t1 = new Thread(()->{
//            func1();
//        });
//        Thread t2 = new Thread(()->{
//            func2();
//        });
//        Thread t3 = new Thread(()->{
//            func3();
//        });
//        t1.start();
//        t2.start();
//        t3.start();
//        t1.join();
//        t2.join();
//        t3.join();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);  // Başlangıçta çalışan thread sayısı
        executor.setMaxPoolSize(10);  // Maksimum thread sayısı
        executor.setQueueCapacity(25);  // Kuyruk kapasitesi
        executor.setThreadNamePrefix("Executor-");  // Thread isimlendirme
        executor.initialize();

//        executor.execute(()->{
//           try{
//               func1();
//           }catch (Exception e){
//               System.err.println("Thread-1 HATA");
//           }
//        });
//        executor.execute(()->{
//            try{
//                func2();
//            }catch (Exception e){
//                System.err.println("Thread-2 HATA");
//            }
//        });
//        executor.execute(()->{
//            try{
//                func3();
//            }catch (Exception e){
//                System.err.println("Thread-3 HATA");
//            }
//        });

        List<Ticket> ticketList = new ArrayList<>();
        for (var i = 0; i < 100; i++) {
            Ticket ticket = new Ticket();
            ticket.name = "test" + i;
            ticket.description = "test";
            ticketList.add(ticket);
        }

        Iterator<Ticket> iterator = ticketList.iterator();

        executor.execute(() -> {
            try {
                while (iterator.hasNext()) {
                    Ticket ticket1 = iterator.next();
                    System.err.println(ticket1.name + " " + ticket1.description);
                    iterator.remove();
                }
                System.err.println(ticketList.size());
            } catch (Exception e) {
                System.err.println("Thread-4 HATA");
            }
        });
    }
}




