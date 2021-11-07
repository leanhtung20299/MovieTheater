package com.vn.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
@Table(name = "CinemaRoom", schema = "movietheater")
public class CinemaRoom implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "cinema_room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cinemaRoomId;
    
    @Column(name = "cinema_room_name", columnDefinition = "NVARCHAR(255)")
    private String cinemaRoomName;
    
    @Column(name = "seat_quantity")
    private int seatQuantity;
    
    @Column(name = "cinema_image")
    private String image;
    
    @OneToMany(mappedBy = "cinemaRoom", cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Seat> seats;
    
    @OneToMany(mappedBy = "cinemaRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Movie> movies;

    
}
