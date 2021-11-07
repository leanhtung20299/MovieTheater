package com.vn.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.entities.ScheduleSeat;
import com.vn.repositories.ScheduleSeatRepository;

@Service
public class ScheduleSeatServiceImpl implements ScheduleSeatService {
  @Autowired
  private ScheduleSeatRepository scheduleSeatRepository;
  
  @Override
  public List<ScheduleSeat> findByMovieIdAndScheduleIdAndShowDateId(String movieId, String scheduleId  ,String showDateId){
    return scheduleSeatRepository.searchMovieAndScheduleAndShowDate(movieId, scheduleId,showDateId);
  }
}
