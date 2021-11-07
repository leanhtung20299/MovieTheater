package com.vn.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.entities.ShowDates;
@Repository
public interface ShowDatesRepository extends JpaRepository<ShowDates, Long>{
   List<ShowDates> findAll();
   ShowDates findByShowDate(LocalDate ShowDates);
}
