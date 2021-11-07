package com.vn.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.DTO.SeatDTO;
import com.vn.entities.Movie;
import com.vn.entities.ScheduleSeat;
import com.vn.entities.Seat;

@Service
public class MovieServiceImpl implements MovieService {
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public Movie getMovieById(String movieId) {
    Session session = sessionFactory.openSession();
    try {
      session.beginTransaction();
      Criteria criteria = session.createCriteria(Movie.class);
      criteria.add(Restrictions.eq("movieId", Integer.parseInt(movieId)));
      Movie movieResult = (Movie) criteria.uniqueResult();
      session.getTransaction().commit();
      session.close();
      return movieResult;
    } catch (Exception e) {
      e.printStackTrace();
      session.getTransaction().rollback();
      session.close();
    }
    return null;
  }
  
  @Override
  public Set<SeatDTO> seatAvailable(List<ScheduleSeat> listScheduleSeat , List<Seat> listSeat ){
    Set<SeatDTO> setSeatDTO = new HashSet<SeatDTO>();
    for (Seat seat : listSeat) {
      boolean flagStatus = true;
      for (ScheduleSeat scheduleSeat : listScheduleSeat) {
        if (scheduleSeat.getSeatId().getSeatId() == seat.getSeatId()) {
          setSeatDTO.add(new SeatDTO(seat.getSeatId(), seat.getSeatColumn(), seat.getSeatRow(), 0, seat.getSeatType()));
          flagStatus = false;
        }
      }
      if (flagStatus == true)
        setSeatDTO.add(new SeatDTO(seat.getSeatId(), seat.getSeatColumn(), seat.getSeatRow(), 1, seat.getSeatType()));
    }
    
    return setSeatDTO;
    
  }
}
