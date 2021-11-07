package com.vn.repositories;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vn.entities.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer>{
  @Query(value = "SELECT * FROM movietheater.promotion where :localdate  between start_time and end_time" , nativeQuery = true)
  List<Promotion> listPromotionValid(@Param("localdate") LocalDate localdate);
}
