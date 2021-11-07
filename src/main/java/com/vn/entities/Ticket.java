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
@Table(name = "Ticket", schema = "movietheater")
public class Ticket implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ticket_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer ticketId;

  @Column(name = "ticket_type")
  private Integer ticketType;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "invoice_id", referencedColumnName = "invoice_id")
  private Invoice invoice;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "schedule_seat_id")
  private ScheduleSeat scheduleSeat;
  
  
  
  public Ticket( Integer ticketType, Invoice invoice, ScheduleSeat scheduleSeat) {
    super();
 
    this.ticketType = ticketType;
    this.invoice = invoice;
    this.scheduleSeat = scheduleSeat;
  }


 

}
