package com.vn.service;

import java.time.LocalDate;
import java.util.List;

import com.vn.DTO.DateDTO;
import com.vn.DTO.WeekMovieDTO;
import com.vn.entities.ShowDates;

public interface ShowDatesService {

  List<ShowDates> getAll();

  List<DateDTO> getListDate(LocalDate localDate);

  List<WeekMovieDTO> getListWeek();

  ShowDates getShowtimeByDay(LocalDate localDate);

}