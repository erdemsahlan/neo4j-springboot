package com.example.neo4j.Repository;
import com.example.neo4j.entity.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface IMovieRepository extends Neo4jRepository<Movie,String> {


    @Query("MATCH (m:Movie) WHERE m.title=$title OPTIONAL MATCH (m)<-[r:ACTED_IN]-(a) return m, collect(r),collect(a)")
    Movie titleQuery(@Param("title") String title);
}
