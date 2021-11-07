package com.vn.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.DTO.DateDTO;
import com.vn.DTO.WeekMovieDTO;
import com.vn.entities.ShowDates;
import com.vn.repositories.ShowDatesRepository;

@Service
public class ShowDatesServiceImpl implements ShowDatesService {
  @Autowired
  private ShowDatesRepository showDatesRepository;

  @Override
  public List<ShowDates> getAll() {
    return showDatesRepository.findAll();
  }

  @Override
  public List<DateDTO> getListDate(LocalDate localDate) {
    List<DateDTO> list = new ArrayList<DateDTO>();
    for (int i = 0; i <= 6; i++) {

      list.add(new DateDTO(
          localDate.plusDays(i).getDayOfWeek().toString().substring(0, 1).toUpperCase()
              + localDate.plusDays(i).getDayOfWeek().toString().substring(1, 3).toLowerCase(),
          String.valueOf(localDate.plusDays(i).getDayOfMonth())));
    }
    return list;
  }
  
  @Override
  public List<WeekMovieDTO> getListWeek() {
    List<WeekMovieDTO> list = new ArrayList<WeekMovieDTO>();
    LocalDate localDate = LocalDate.now();
    for (int i = 0; i <= 5; i++) {
      list.add(new WeekMovieDTO(String.valueOf(localDate.plusDays(i*7).getDayOfMonth())
          ,String.valueOf(localDate.plusDays(i*7+7-1).getDayOfMonth())
          ,String.valueOf(localDate.plusDays(i*7).getMonthValue())
          ,String.valueOf(localDate.plusDays(i*7+7).getMonthValue())
          ,String.valueOf(localDate.getYear())
          ,String.valueOf(localDate.plusDays(i*7+7).getYear())));
    }
    return list;
  }
  

  @Override
  public ShowDates getShowtimeByDay(LocalDate localDate) {
    return showDatesRepository.findByShowDate(localDate);
  }
}
