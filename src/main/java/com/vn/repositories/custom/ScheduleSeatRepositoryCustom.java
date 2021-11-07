package com.vn.repositories.custom;

import java.util.List;

import com.vn.entities.ScheduleSeat;

public interface ScheduleSeatRepositoryCustom {
  List<ScheduleSeat> searchMovieAndScheduleAndShowDate(String movieId , String scheduleId , String showDateId);
}
