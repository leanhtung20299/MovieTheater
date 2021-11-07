package com.vn.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vn.DTO.SeatDTO;
import com.vn.entities.Movie;
import com.vn.entities.Schedule;
import com.vn.entities.ScheduleSeat;
import com.vn.entities.Seat;
import com.vn.entities.ShowDates;
import com.vn.repositories.ScheduleRepository;
import com.vn.repositories.ShowDatesRepository;
import com.vn.service.MovieService;
import com.vn.service.ScheduleSeatService;

@Controller
public class ShowDateMovie {
  
  
  DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
      .parseCaseInsensitive().parseLenient()
      .appendPattern("[yyyy-MM-d]")
      .appendPattern("[yyyy-MM-dd]")
      .appendPattern("[yyyy-M-dd]")
      .appendPattern("[yyyy-M-d]");

  DateTimeFormatter datetimeformatter = builder.toFormatter();
  
  
  @Autowired
  private MovieService movieService;

  @Autowired
  private ScheduleSeatService scheduleSeatService;
  
  @Autowired
  private ShowDatesRepository showDatesRepository;
  
  @Autowired
  private ScheduleRepository  scheduleRepository;
  
  
  @RequestMapping("/booking-ticket/bookingSeatMovies/{movieId}/{scheduleId}/{date}")
  public String showDateMovie(@PathVariable String movieId, @PathVariable String scheduleId,@PathVariable String date, Model model , RedirectAttributes redirectAttributes) {
    
    Movie movie = movieService.getMovieById(movieId);
    
    Optional<Schedule> schedule = scheduleRepository.findById(Integer.parseInt(scheduleId));
    
    ShowDates showDates = showDatesRepository.findByShowDate(LocalDate.parse(date, datetimeformatter));
    System.out.println(showDates.toString());
//    findByScheduleId
    LocalDate timeNow = LocalDate.now();
    LocalDate timeMovieBooking = LocalDate.parse(date, datetimeformatter);
    
    if(timeNow.isEqual(timeMovieBooking)) {
      LocalTime localTime = LocalTime.now();
      LocalTime timeScheduleMovie = LocalTime.parse(schedule.get().getScheduleTime(), DateTimeFormatter.ofPattern("H:mm"));
      System.out.println(localTime + " " + timeScheduleMovie);
      if(localTime.getHour() > timeScheduleMovie.getHour()) {
        redirectAttributes.addFlashAttribute("status", "Schedule movie has been shown");
        return "redirect:/booking-ticket";
      }
      else if(localTime.getHour() == timeScheduleMovie.getHour() && timeScheduleMovie.getMinute()-localTime.getMinute() <= 30 ){
        redirectAttributes.addFlashAttribute("status", "The movie is over the registration time because it's under 30 minutes");
        return "redirect:/booking-ticket";
      }
    }

    
    
    
    System.out.println(movieId + scheduleId + date );
    List<Seat> seats = (List<Seat>) movie.getCinemaRoom().getSeats();
    seats.stream().forEach(u -> System.out.println(u.toString()));
    
    
 
    
    System.out.println("==============================================");
    
    List<ScheduleSeat> listScheduleSeat = scheduleSeatService.findByMovieIdAndScheduleIdAndShowDateId(movieId, scheduleId , showDates.getShowDateId().toString());
    listScheduleSeat.stream().forEach(u->System.out.println(u.toString()));
    
    Set<SeatDTO> listSeatDTO = movieService.seatAvailable(listScheduleSeat, seats);
    listSeatDTO.stream().forEach(u->System.out.println(u.toString()));
    
    List<SeatDTO> listSeat = new ArrayList<SeatDTO>();
    listSeat.addAll(listSeatDTO);
    Collections.sort(listSeat, Comparator.comparing(SeatDTO::getSeatRow).thenComparing(SeatDTO::getSeatColumn));

    listSeat.stream().forEach(u -> System.out.println(u.toString()));

    model.addAttribute("movieId", movieId);
    model.addAttribute("scheduleId", scheduleId);
    model.addAttribute("closetag", "</ol></li>");
    model.addAttribute("listSeats", listSeat);
    return "BookingTicket/BookingTicket";
  }

}
