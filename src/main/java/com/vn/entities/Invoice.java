package com.vn.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "Invoice", schema = "movietheater")
public class Invoice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invoiceId;
    
    @Column(name = "add_score")
    private Integer addScore;
    
    @Column(name = "booking_date")
    private Date bookingDate;
    
    @Column(name = "movie_name", columnDefinition = "NVARCHAR(255)")
    private String movieName;
    
    @Column(name = "schedule_show")
    private LocalDate scheduleShow;
    
    @Column(name = "schedule_show_time", columnDefinition = "NVARCHAR(255)")
    private String scheduleShowTime;
    
    @Column(name = "status")
    private Integer status;
    
    @Column(name = "total_money")
    private Long totalMoney;
    
    @Column(name = "use_score")
    private Integer useScore;
    
    @Column(name = "seat", columnDefinition = "NVARCHAR(255)")
    private String seat;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;
    
    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY)
    private List<Ticket> tickets;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id", referencedColumnName = "promotion_id")
    private Promotion promotion;
    
    
    
    
    public Invoice(Integer addScore, Date bookingDate, String movieName, LocalDate scheduleShow,
        String scheduleShowTime, Integer status, Long totalMoney, Integer useScore, String seat, Account account,
        Promotion promotion) {
      super();
      this.addScore = addScore;
      this.bookingDate = bookingDate;
      this.movieName = movieName;
      this.scheduleShow = scheduleShow;
      this.scheduleShowTime = scheduleShowTime;
      this.status = status;
      this.totalMoney = totalMoney;
      this.useScore = useScore;
      this.seat = seat;
      this.account = account;
      this.promotion = promotion;
    }
    
   
    
    
}
