package com.ibm.practica.cinema.repository;

import com.ibm.practica.cinema.entity.Movie;
import com.ibm.practica.cinema.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    // interacting with the user DB or table
    Optional<Movie> findByName(String name);
}
