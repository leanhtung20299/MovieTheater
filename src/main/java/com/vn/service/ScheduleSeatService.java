package com.vn.service;

import java.util.List;

import com.vn.entities.ScheduleSeat;

public interface ScheduleSeatService {

  List<ScheduleSeat> findByMovieIdAndScheduleIdAndShowDateId(String movieId, String scheduleId, String showDateId);

}