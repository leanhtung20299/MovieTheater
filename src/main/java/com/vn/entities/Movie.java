package com.vn.entities;



import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity
@Table(name = "Movie", schema = "movietheater")
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "movie_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer movieId;
  @Column(name = "actor", columnDefinition = "NVARCHAR(255)")
	private String actor;

//	@Column(name = "cinema_room_id")
//	private Integer cinemaRoomId;
	
	@Column(name = "content", columnDefinition = "NVARCHAR(1000)")
	private String content;

	@Column(name = "director", columnDefinition = "NVARCHAR(255)")
	private String director;

	@Column(name = "duration")
	private int duration;

	@Column(name = "from_date")
	private LocalDate fromDate;

	@Column(name = "movie_product_company", columnDefinition = "NVARCHAR(255)")
	private String movieProductCompany;

	@Column(name = "to_date")
	private LocalDate toDate;

	@Column(name = "version", columnDefinition = "NVARCHAR(255)")
	private String version;

	@Column(name = "movie_name_english", columnDefinition = "VARCHAR(255)")
	private String movieNameEnglish;

	@Column(name = "movie_name_vn", columnDefinition = "NVARCHAR(255)")
	private String movieNameVn;

	@Column(name = "large_image", columnDefinition = "VARCHAR(255)")
	private String largeImage;

	@Column(name = "small_image", columnDefinition = "VARCHAR(255)")
	private String smallImage;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<MovieDate> movieDates;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<MovieSchedule> movieSchedules;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<MovieType> movieTypes;

	@ManyToOne
	@JoinColumn(name = "cinema_room_id", referencedColumnName = "cinema_room_id")
	private CinemaRoom cinemaRoom;

	public Movie(LocalDate fromDate, LocalDate toDate, String movieNameEnglish, String movieNameVn, String largeImage,
			String smallImage) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.movieNameEnglish = movieNameEnglish;
		this.movieNameVn = movieNameVn;
		this.largeImage = largeImage;
		this.smallImage = smallImage;
	}

}
