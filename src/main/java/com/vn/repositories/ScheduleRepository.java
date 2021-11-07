package com.vn.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.entities.Schedule;
@Repository
public interface ScheduleRepository  extends JpaRepository<Schedule, Integer>{
   Optional<Schedule> findByScheduleId(Integer id);
}
