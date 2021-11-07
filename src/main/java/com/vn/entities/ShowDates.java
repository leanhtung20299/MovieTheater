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
@Table(name = "ShowDate", schema = "movietheater")
public class ShowDates implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "show_date_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showDateId;
    
    @Column(name = "show_date")
    private LocalDate showDate;
    
    @Column(name = "date_name", columnDefinition = "NVARCHAR(255)")
    private String dateName;
    
    @OneToMany(mappedBy = "showDates", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<MovieDate> movieDates;
    
   

    public List<MovieDate> getMovieDates() throws NullPointerException{
     
      return movieDates;
    }

   
    @Override
    public String toString() {
      return "ShowDates [showDateId=" + showDateId + ", showDate=" + showDate + ", dateName=" + dateName
          ;
    }
    
    
    
}
