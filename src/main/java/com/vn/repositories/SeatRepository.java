package com.vn.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.entities.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer>{
   List<Seat> findAllById(Iterable<Integer> ids) ;
}
