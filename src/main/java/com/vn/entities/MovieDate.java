package com.vn.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "MovieDate", schema = "movietheater")
public class MovieDate implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
  private Movie movie;

  @Id
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "show_date_id", referencedColumnName = "show_date_id")
  private ShowDates showDates;


 
  @Override
  public String toString() {
    return "MovieDate [movie=" + movie + ", showDates=" + showDates + "]";
  }

  
}
