package com.vn.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "ScheduleSeat", schema = "movietheater")
public class ScheduleSeat implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "schedule_seat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleSeatId;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private Movie movieId;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "schedule_id")
    private Schedule scheduleId;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seat_id")
    private Seat seatId;
    
    @Column(name = "seat_column", columnDefinition = "VARCHAR(255)")
    private String seatColumn;
    
    @Column(name = "seat_row")
    private Integer seatRow;
    
    @Column(name = "seat_status")
    private Integer seatStatus;
    
    @Column(name = "seat_type")
    private Integer seatType;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "show_date_id")
    private ShowDates showDateId;
    
    
  
    

    public ScheduleSeat(Movie movieId, Schedule scheduleId, Seat seatId, String seatColumn, Integer seatRow,
        Integer seatStatus, Integer seatType, ShowDates showDateId) {
      super();
      this.movieId = movieId;
      this.scheduleId = scheduleId;
      this.seatId = seatId;
      this.seatColumn = seatColumn;
      this.seatRow = seatRow;
      this.seatStatus = seatStatus;
      this.seatType = seatType;
      this.showDateId = showDateId;
    }

    @Override
    public String toString() {
      return "ScheduleSeat [scheduleSeatId=" + scheduleSeatId + ", seatId=" + seatId + ", seatColumn=" + seatColumn
          + ", seatRow=" + seatRow + ", seatStatus=" + seatStatus + ", seatType=" + seatType + "]";
    }
    
}
