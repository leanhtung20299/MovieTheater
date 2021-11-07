package com.vn.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "Seat", schema = "movietheater")
public class Seat implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "seat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seatId;
    
    @Column(name = "seat_column", columnDefinition = "VARCHAR(255)")
    private String seatColumn;
    
    @Column(name = "seat_row")
    private Integer seatRow;
    
    @Column(name = "status")
    private Integer status;
    
    @Column(name = "seat_type")
    private Integer seatType;
    
    @Column(name = "seat_price")
    private Float seatPrice;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cinema_room_id", referencedColumnName = "cinema_room_id")
    private CinemaRoom cinemaRoom;

  

  

    @Override
    public String toString() {
      return "Seat [seatId=" + seatId + ", seatColumn=" + seatColumn + ", seatRow=" + seatRow + ", status=" + status
          + ", seatType=" + seatType + "]";
    }
    
    
}

