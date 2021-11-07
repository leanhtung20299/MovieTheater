package com.vn.service;

import java.util.List;
import java.util.Set;

import com.vn.DTO.SeatDTO;
import com.vn.entities.Movie;
import com.vn.entities.ScheduleSeat;
import com.vn.entities.Seat;

public interface MovieService {

  Movie getMovieById(String movieId);

  Set<SeatDTO> seatAvailable(List<ScheduleSeat> listScheduleSeat, List<Seat> listSeat);

}