package com.vn.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.entities.Schedule;
import com.vn.repositories.ScheduleRepository;

@Service
public class ScheduleServiceImpl implements ScheduleService {
  @Autowired
  private ScheduleRepository scheduleRepository;
  
  @Override
  public boolean CheckTimeBooking(String scheduleId , String date) {

    LocalDate timeNow = LocalDate.now();
    LocalDate timeMovieBooking = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-d"));
    
    Optional<Schedule> schedule = scheduleRepository.findById(Integer.parseInt(scheduleId));
    
    if(timeNow.isEqual(timeMovieBooking)) {
      LocalTime localTime = LocalTime.now();
      LocalTime timeScheduleMovie = LocalTime.parse(schedule.get().getScheduleTime(), DateTimeFormatter.ofPattern("H:mm"));
      System.out.println(localTime + " " + timeScheduleMovie);
      if(localTime.getHour() > timeScheduleMovie.getHour()) {
        return false;
      }
      else if(localTime.getHour() == timeScheduleMovie.getHour() && timeScheduleMovie.getMinute()-localTime.getMinute() <= 30 ){
        return false;
      }
    }
    return true;
    
  }
  
  
}
