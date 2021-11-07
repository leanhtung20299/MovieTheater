package com.vn.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vn.DTO.DateDTO;
import com.vn.DTO.WeekMovieDTO;
import com.vn.entities.Movie;
import com.vn.entities.MovieDate;
import com.vn.entities.ShowDates;
import com.vn.service.ShowDatesService;

@Controller
public class ShowtimeController {
  
  DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
      .parseCaseInsensitive().parseLenient()
      .appendPattern("[dd-MM-yyyy]")
      .appendPattern("[d-MM-yyyy]")
      .appendPattern("[dd-M-yyyy]")
      .appendPattern("[d-M-yyyy]");

  DateTimeFormatter datetimeformatter = builder.toFormatter();
  
  @Autowired
  private ShowDatesService showDateService;

  @RequestMapping(value = { "/booking-ticket", "/booking-ticket/showtimeviews/reload" }, method = RequestMethod.GET)
  public String InitialShowtime(Model model, HttpServletRequest request ) {
    // List Date of week
    List<DateDTO> listDate = showDateService.getListDate(LocalDate.now());

    // List Week
    List<WeekMovieDTO> listWeek = showDateService.getListWeek();

    WeekMovieDTO fristWeek = listWeek.get(0);
    listWeek.remove(0);

    // Show date now
    ShowDates showDateMovie = showDateService.getShowtimeByDay(LocalDate.now());
    List<MovieDate> listMovieDate = null ;
    try {
      listMovieDate = showDateMovie.getMovieDates();
    } catch (Exception e) {
    }

    // Retrive list movie
    List<Movie> listMovie = new ArrayList<Movie>();
    if(listMovieDate!=null) {
      for (MovieDate movieDate : listMovieDate) {
        listMovie.add(movieDate.getMovie());
      }
    }
   

    listMovie.stream().forEach(u -> System.out.println(u));

    model.addAttribute("listDate", listDate);
    model.addAttribute("fristWeek", fristWeek);
    model.addAttribute("listWeek", listWeek);
    model.addAttribute("listMovie", listMovie);
    model.addAttribute("date", LocalDate.now());
  
    return "BookingTicket/ShowtimeViews";
  }


  @RequestMapping(value = { "/booking-ticket/ShowtimeViews/getShowdatebyDay" }, method = RequestMethod.GET)
  public String getShowdatebyDay(Model model, HttpServletRequest request) {
    String day = request.getParameter("day");
    String dateofmonth = request.getParameter("dateofmonth");
    String timeweek = request.getParameter("timeweek");
    System.out.println(day + " " + dateofmonth + " " + timeweek);

    List<WeekMovieDTO> listWeek = showDateService.getListWeek();

    String[] timeweekSplit = timeweek.split("-");

    String[] timeWeekFrom = timeweekSplit[0].split("/");
    String[] timeWeekTo = timeweekSplit[1].split("/");
    
    
    WeekMovieDTO nowWeek = null;
    String localDateValue = "";
    for (WeekMovieDTO weekMovieDTO : listWeek) {
      if (weekMovieDTO.getDayOfMonthNow().equals(timeWeekFrom[0])
          && weekMovieDTO.getDayOfMonthTo().equals(timeWeekTo[0])) {
        localDateValue = localDateValue + weekMovieDTO.getDayOfMonthNow() + "-" + weekMovieDTO.getMonthNow() + "-"
            + weekMovieDTO.getYearNow();
        nowWeek = weekMovieDTO;
        break;
      }
    }
    LocalDate localDateFormat = LocalDate.parse(localDateValue, datetimeformatter);
    LocalDate valueSearchPanigation = null;
    for (int i = 0; i <= 7; i++) {
      if (localDateFormat.plusDays(i).getDayOfMonth() == Integer.parseInt(dateofmonth)) {
        valueSearchPanigation = localDateFormat.plusDays(i);
        break;
      }
    }
    List<DateDTO> listDate = showDateService.getListDate(localDateFormat);
    
    System.out.println(valueSearchPanigation.toString());

/// 

    listWeek.remove(nowWeek);

    // Show date now

    ShowDates showDateMovie = showDateService.getShowtimeByDay(valueSearchPanigation);
    List<MovieDate> listMovieDate = null;
    try {
      listMovieDate = showDateMovie.getMovieDates();
    } catch (Exception e) {
    }

    List<Movie> listMovie = new ArrayList<Movie>();
    if (listMovieDate != null) {
      for (MovieDate movieDate : listMovieDate) {
        listMovie.add(movieDate.getMovie());
      }
    }
    listMovie.stream().forEach(u -> System.out.println(u));
    model.addAttribute("listMovie", listMovie);

    // Retrive list movie

    model.addAttribute("listDate", listDate);
    model.addAttribute("fristWeek", nowWeek);
    model.addAttribute("listWeek", listWeek);
    model.addAttribute("date", valueSearchPanigation);
    return "BookingTicket/ShowtimeViews";
  }
  
  @RequestMapping(value = { "/booking-ticket/ShowtimeViews/getShowbyWeek" }, method = RequestMethod.GET)
  public String getShowbyWeek(Model model, HttpServletRequest request) {  
    String timeweek = request.getParameter("timeWeek");
    System.out.println(timeweek);

    List<WeekMovieDTO> listWeek = showDateService.getListWeek();

    String[] timeweekSplit = timeweek.split("-");

    String[] timeWeekFrom = timeweekSplit[0].split("/");
    String[] timeWeekTo = timeweekSplit[1].split("/");

    WeekMovieDTO nowWeek = null;
    String localDateValue1 = "";
    String localDateValue2 = "";
    for (WeekMovieDTO weekMovieDTO : listWeek) {
      if (weekMovieDTO.getDayOfMonthNow().equals(timeWeekFrom[0])
          && weekMovieDTO.getDayOfMonthTo().equals(timeWeekTo[0])) {
        localDateValue1 = localDateValue1 + weekMovieDTO.getDayOfMonthNow() + "-" + weekMovieDTO.getMonthNow() + "-"
            + weekMovieDTO.getYearNow();
        localDateValue2 = localDateValue2 + weekMovieDTO.getDayOfMonthTo() + "-" + weekMovieDTO.getMonthTo() + "-"
            + weekMovieDTO.getYearTo();
        nowWeek = weekMovieDTO;
        break;
      }
    }
    listWeek.remove(nowWeek);
    
    LocalDate localDateFormat1 = LocalDate.parse(localDateValue1, datetimeformatter);
    LocalDate localDateFormat2 = LocalDate.parse(localDateValue2, datetimeformatter);
    
    
    List<DateDTO> listDate = showDateService.getListDate(localDateFormat1);
    
    ShowDates showDateMovie = showDateService.getShowtimeByDay(localDateFormat1);
    List<MovieDate> listMovieDate = null;
    try {
      listMovieDate = showDateMovie.getMovieDates();
    } catch (Exception e) {
    }
    
    List<Movie> listMovie = new ArrayList<Movie>();
    if (listMovieDate != null) {
      for (MovieDate movieDate : listMovieDate) {
        listMovie.add(movieDate.getMovie());
      }
    }
    

    model.addAttribute("listDate", listDate);
    model.addAttribute("fristWeek", nowWeek);
    model.addAttribute("listWeek", listWeek);
    model.addAttribute("listMovie", listMovie);
    model.addAttribute("date", localDateFormat1);
    return "BookingTicket/ShowtimeViews";
  }

//  

}
