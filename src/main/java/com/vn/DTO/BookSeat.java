package com.vn.DTO;

import java.io.Serializable;

public class BookSeat implements Serializable{
  private Integer seatId; 
  private String rowSeat;
  private String columeSeat;
  private String seatType;
  private String statusBooking;
  
  public BookSeat() {
    // TODO Auto-generated constructor stub
  }
  
  public String getRowSeat() {
    return rowSeat;
  }
  public void setRowSeat(String rowSeat) {
    this.rowSeat = rowSeat;
  }
  public String getColumeSeat() {
    return columeSeat;
  }
  public void setColumeSeat(String columeSeat) {
    this.columeSeat = columeSeat;
  }
  public String getSeatType() {
    return seatType;
  }
  public void setSeatType(String seatType) {
    this.seatType = seatType;
  }
  public String getStatusBooking() {
    return statusBooking;
  }
  public void setStatusBooking(String statusBooking) {
    this.statusBooking = statusBooking;
  }
  
  public Integer getSeatId() {
    return seatId;
  }
  public void setSeatId(Integer seatId) {
    this.seatId = seatId;
  }
  @Override
  public String toString() {
    return "BookSeat [seatId=" + seatId + ", rowSeat=" + rowSeat + ", columeSeat=" + columeSeat + ", seatType="
        + seatType + ", statusBooking=" + statusBooking + "]";
  }
  
  
  
  
}
