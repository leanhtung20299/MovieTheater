package com.vn.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vn.DTO.BookSeat;
import com.vn.DTO.BookingSeatsDTO;
import com.vn.DTO.DataStatusBookingResponce;
import com.vn.DTO.SeatDTO;
import com.vn.DTO.SeatPriceDTO;
import com.vn.entities.Invoice;
import com.vn.entities.Movie;
import com.vn.entities.Promotion;
import com.vn.entities.Schedule;
import com.vn.entities.ScheduleSeat;
import com.vn.entities.Seat;
import com.vn.entities.ShowDates;
import com.vn.entities.Ticket;
import com.vn.repositories.InvoiceRepository;
import com.vn.repositories.MovieRepository;
import com.vn.repositories.PromotionRepository;
import com.vn.repositories.ScheduleRepository;
import com.vn.repositories.ScheduleSeatRepository;
import com.vn.repositories.SeatRepository;
import com.vn.repositories.ShowDatesRepository;
import com.vn.repositories.TicketRepository;
import com.vn.service.ScheduleSeatService;
import com.vn.service.ScheduleService;

@Controller
public class BookingTicketController {
  
  
  DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
      .parseCaseInsensitive().parseLenient()
      .appendPattern("[yyyy-MM-d]")
      .appendPattern("[yyyy-MM-dd]")
      .appendPattern("[yyyy-M-dd]")
      .appendPattern("[yyyy-M-d]");

  DateTimeFormatter datetimeformatter = builder.toFormatter();

  @Autowired
  private ScheduleSeatService scheduleSeatService;

  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private InvoiceRepository invoiceRepository;

  @Autowired
  private SeatRepository seatRepository;

  @Autowired
  private ScheduleRepository scheduleRepository;

  @Autowired
  private ShowDatesRepository showDatesRepository;

  @Autowired
  private ScheduleSeatRepository scheduleSeatRepository;
  
  @Autowired
  private PromotionRepository promotionRepository;
  
  @Autowired
  private ScheduleService scheduleService;

  // Checking booking
  @RequestMapping(value = "/booking-ticket/bookingSeat", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<DataStatusBookingResponce> bookingSeat(Model model, HttpServletRequest request,
      @RequestBody BookingSeatsDTO bookingSeatsDTO) {

//    System.out.println(bookingSeatsDTO.getDate());

    
    ShowDates showDates = showDatesRepository
        .findByShowDate(LocalDate.parse(bookingSeatsDTO.getDate(),datetimeformatter));
    
    // get List listScheduleSeat (Seat available)
    List<ScheduleSeat> listScheduleSeat = scheduleSeatService.findByMovieIdAndScheduleIdAndShowDateId(
        bookingSeatsDTO.getMovieId(), bookingSeatsDTO.getScheduleId(), showDates.getShowDateId().toString());
    
    
    
    DataStatusBookingResponce dataStatusBookingResponce = new DataStatusBookingResponce();
    // Check seat booking duplicate
    
    
    
    for (BookSeat seat : bookingSeatsDTO.getListBookSeat()) {
      for (ScheduleSeat scheduleSeat : listScheduleSeat) {
        if (seat.getSeatId() == scheduleSeat.getSeatId().getSeatId()) {
          dataStatusBookingResponce.setStatusTransaction("Failed");
          List<String> seatsAvailable = new ArrayList<String>();
          for (ScheduleSeat scheduleSeat2 : listScheduleSeat) {
            seatsAvailable.add(String.valueOf(scheduleSeat2.getSeatId().getSeatId()));
          }
          dataStatusBookingResponce.setListSeatNotAvailable(seatsAvailable);
          return new ResponseEntity<DataStatusBookingResponce>(dataStatusBookingResponce, HttpStatus.OK);
        }
      }
    }
    return new ResponseEntity<DataStatusBookingResponce>(new DataStatusBookingResponce("Succcessful"), HttpStatus.OK);

  }

  // Push data to UI confirm
  @RequestMapping(value = "/booking-ticket/ConfirmBooking", method = RequestMethod.POST)
  public String ConfirmBooking(Model model, HttpServletRequest request,
      @RequestParam(value = "listBookSeat[]") List<Integer> listBookSeat, @RequestParam("scheduleId") String scheduleId,
      @RequestParam("movieId") String movieId, @RequestParam("date") String date) {

    // get Movie by movieId
    Movie movieData = movieRepository.findByMovieId(Integer.parseInt(movieId));

    List<SeatPriceDTO> listSeatPriceDTOs = new ArrayList<>();

    List<Seat> listSeats = seatRepository.findAllById(listBookSeat);
    
    // Checktime booking
    Optional<Schedule> schedule = scheduleRepository.findById(Integer.parseInt(scheduleId));
    
    
    Optional<List<Promotion>> listPromotion = Optional.ofNullable(promotionRepository.listPromotionValid(LocalDate.now()));
    listPromotion.stream().forEach(u->System.out.println(u));
    
    int totalPrice = 0;

    for (int i = 0; i < listSeats.size(); i++) {
      listSeatPriceDTOs.add(new SeatPriceDTO(listSeats.get(i).getSeatRow() + listSeats.get(i).getSeatColumn(),
          listSeats.get(i).getSeatPrice()));
      totalPrice += listSeats.get(i).getSeatPrice();
    }
    
    model.addAttribute("promotionInitial", listPromotion.get().get(0));
    model.addAttribute("listPromotion", listPromotion.get());
    model.addAttribute("movieName", movieData.getMovieNameVn());
    model.addAttribute("listSeatPriceDTOs", listSeatPriceDTOs);
    model.addAttribute("totalPrice", totalPrice);
    model.addAttribute("timeSchudule", schedule.get().getScheduleTime());
    model.addAttribute("nameRoom", movieData.getCinemaRoom().getCinemaRoomName());
    model.addAttribute("date", LocalDate.parse(date, datetimeformatter));

    return "BookingTicket/BookingTicketInfomation";
  }

  // Save confirm
  @RequestMapping(value = "/booking-ticket/SaveBooking", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<DataStatusBookingResponce> bookingSeatConfirm(Model model, HttpServletRequest request,
      @RequestBody BookingSeatsDTO bookingSeatsDTO) {
    
//    System.out.println(bookingSeatsDTO);
    
    ShowDates showDates = showDatesRepository
        .findByShowDate(LocalDate.parse(bookingSeatsDTO.getDate(), datetimeformatter));
    
    Optional<Promotion> promotion = promotionRepository.findById(Integer.parseInt(bookingSeatsDTO.getPromotionId()));
    
    // find Schedule movie by Id
    Optional<Schedule> schedule = scheduleRepository.findById(Integer.parseInt(bookingSeatsDTO.getScheduleId()));

    // find ScheduleSeat
    List<ScheduleSeat> listScheduleSeat = scheduleSeatService.findByMovieIdAndScheduleIdAndShowDateId(
        bookingSeatsDTO.getMovieId(), bookingSeatsDTO.getScheduleId(), showDates.getShowDateId().toString());

    // check duplicate seat
    Boolean checktimeBooking = scheduleService.CheckTimeBooking(bookingSeatsDTO.getScheduleId() , bookingSeatsDTO.getDate());
    
    DataStatusBookingResponce dataStatusBookingResponce = new DataStatusBookingResponce();
    // Check seat booking duplicate
    for (BookSeat seat : bookingSeatsDTO.getListBookSeat()) {
      for (ScheduleSeat scheduleSeat : listScheduleSeat) {
        if (seat.getSeatId() == scheduleSeat.getSeatId().getSeatId()) {
          dataStatusBookingResponce.setStatusTransaction("Failed");
          List<String> seatsAvailable = new ArrayList<String>();
          for (ScheduleSeat scheduleSeat2 : listScheduleSeat) {
            seatsAvailable.add(String.valueOf(scheduleSeat2.getSeatId().getSeatId()));
          }
          dataStatusBookingResponce.setListSeatNotAvailable(seatsAvailable);
          return new ResponseEntity<DataStatusBookingResponce>(dataStatusBookingResponce, HttpStatus.OK);
        }
      }
    }
    if(checktimeBooking == false) {
      dataStatusBookingResponce.setStatusTransaction("Timeout");
      return new ResponseEntity<DataStatusBookingResponce>(dataStatusBookingResponce, HttpStatus.OK);
    }

    // if checking ok seat available
    // find movie
    Movie movieData = movieRepository.findByMovieId(Integer.parseInt(bookingSeatsDTO.getMovieId()));

    List<Integer> listBookSeat = new ArrayList<Integer>();
    for (BookSeat bookSeat : bookingSeatsDTO.getListBookSeat()) {
      listBookSeat.add(bookSeat.getSeatId());
    }

    List<Seat> listSeats = seatRepository.findAllById(listBookSeat);

    Schedule scheduleData = scheduleRepository.getById(Integer.parseInt(bookingSeatsDTO.getScheduleId()));

    List<ScheduleSeat> listScheduleSeatSave = new ArrayList<ScheduleSeat>();
    for (int i = 0; i < listSeats.size(); i++) {
      ScheduleSeat scheduleSeat = new ScheduleSeat(movieData, scheduleData, listSeats.get(i),
          listSeats.get(i).getSeatColumn(), listSeats.get(i).getSeatRow(), listSeats.get(i).getStatus(),
          listSeats.get(i).getSeatType(), showDates);
      listScheduleSeatSave.add(scheduleSeat);
    }

    List<SeatPriceDTO> listSeatPriceDTOs = new ArrayList<>();
    String seatShow = "";
    for (int i = 0; i < bookingSeatsDTO.getListBookSeat().size(); i++) {
      seatShow = seatShow + listSeats.get(i).getSeatRow() + listSeats.get(i).getSeatColumn() + " ";
//        listSeatPriceDTOs.add(new SeatPriceDTO(listSeats.get(i).getSeatRow() + listSeats.get(i).getSeatColumn(), 45000));
//        totalPrice += 45000;
    }
    
    int totalPrice = 0;
    for (int i = 0; i < listSeats.size(); i++) {
      totalPrice += listSeats.get(i).getSeatPrice();
    }


    Invoice invoice = new Invoice(0, new Date(), movieData.getMovieNameVn(), LocalDate.now(),
        schedule.get().getScheduleTime(), 0, (totalPrice-promotion.get().getDiscountLevel())<=0?0:(long)(totalPrice-promotion.get().getDiscountLevel()), 0, seatShow, null, promotion.get());
    Invoice invoiceData = invoiceRepository.save(invoice);

    List<Ticket> tickets = new ArrayList<Ticket>();
    for (ScheduleSeat scheduleSeat : listScheduleSeatSave) {
      Ticket ticket = new Ticket(scheduleSeat.getSeatId().getSeatType(), invoiceData, scheduleSeat);
      tickets.add(ticket);
    }

    ticketRepository.saveAll(tickets);

    return new ResponseEntity<DataStatusBookingResponce>(new DataStatusBookingResponce("Succcessful"), HttpStatus.OK);
  }

  @RequestMapping(value = "/booking-ticket/ResultBooking", method = RequestMethod.POST)
  public String ResultBooking(Model model, HttpServletRequest request,
      @RequestParam(value = "listBookSeat[]") List<Integer> listBookSeat, @RequestParam("scheduleId") String scheduleId,
      @RequestParam("movieId") String movieId, @RequestParam("date") String date , @RequestParam("promotionId") String idPromotion) {

    // get Movie by movieId
    Movie movieData = movieRepository.findByMovieId(Integer.parseInt(movieId));
    Optional<Promotion> promotion = promotionRepository.findById(Integer.parseInt(idPromotion));
    List<SeatPriceDTO> listSeatPriceDTOs = new ArrayList<>();

    List<Seat> listSeats = seatRepository.findAllById(listBookSeat);
    Optional<Schedule> schedule = scheduleRepository.findById(Integer.parseInt(scheduleId));

    int totalPrice = 0;

    for (int i = 0; i < listSeats.size(); i++) {
      listSeatPriceDTOs.add(new SeatPriceDTO(listSeats.get(i).getSeatRow() + listSeats.get(i).getSeatColumn(),
          listSeats.get(i).getSeatPrice()));
      totalPrice += listSeats.get(i).getSeatPrice();
    }

    model.addAttribute("movieName", movieData.getMovieNameVn());
    model.addAttribute("listSeatPriceDTOs", listSeatPriceDTOs);
    model.addAttribute("totalPrice", totalPrice);
    model.addAttribute("timeSchudule", schedule.get().getScheduleTime());
    model.addAttribute("nameRoom", movieData.getCinemaRoom().getCinemaRoomName());
    model.addAttribute("date", LocalDate.parse(date, datetimeformatter));

    return "BookingTicket/BookingTicketComplete";
  }

}
