package com.vn.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vn.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {  
   Movie findByMovieId(Integer MovieId);
}
