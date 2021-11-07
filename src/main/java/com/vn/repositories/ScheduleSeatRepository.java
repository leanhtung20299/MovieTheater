package com.vn.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vn.entities.ScheduleSeat;
import com.vn.repositories.custom.ScheduleSeatRepositoryCustom;


@Repository
public interface ScheduleSeatRepository  extends JpaRepository<ScheduleSeat, Integer>,ScheduleSeatRepositoryCustom{
  List<ScheduleSeat> findByMovieIdAndScheduleIdAndShowDateId(String movieId, String scheduleId , String showDateId);
}
