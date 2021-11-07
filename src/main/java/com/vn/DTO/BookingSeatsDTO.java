package com.vn.DTO;

import java.util.List;

public class BookingSeatsDTO {
  private List<BookSeat> listBookSeat;
  private String movieId;
  private String scheduleId;
  private String date;
  private String promotionId;
  public List<BookSeat> getListBookSeat() {
    return listBookSeat;
    
  }

  public void setListBookSeat(List<BookSeat> listBookSeat) {
    this.listBookSeat = listBookSeat;
  }
  
  public BookingSeatsDTO() {
  }
  
  
  

  @Override
  public String toString() {
    return "BookingSeatsDTO [listBookSeat=" + listBookSeat + ", movieId=" + movieId + ", scheduleId=" + scheduleId
        + ", date=" + date + ", promotionId=" + promotionId + "]";
  }

  public String getPromotionId() {
    return promotionId;
  }

  public void setPromotionId(String promotionId) {
    this.promotionId = promotionId;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getMovieId() {
    return movieId;
  }

  public void setMovieId(String movieId) {
    this.movieId = movieId;
  }

  public String getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(String scheduleId) {
    this.scheduleId = scheduleId;
  }
  
  
}
