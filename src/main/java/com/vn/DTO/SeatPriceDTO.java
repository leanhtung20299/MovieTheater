package com.vn.DTO;

public class SeatPriceDTO {
  private String seatIndex;
  private Float money;
  
  public SeatPriceDTO() {
    // TODO Auto-generated constructor stub
  }

  public String getSeatIndex() {
    return seatIndex;
  }

  public void setSeatIndex(String seatIndex) {
    this.seatIndex = seatIndex;
  }

  public Float getMoney() {
    return money;
  }

  public void setMoney(Float money) {
    this.money = money;
  }

  public SeatPriceDTO(String seatIndex, Float money) {
    super();
    this.seatIndex = seatIndex;
    this.money = money;
  }

  
  
  

  
  
  
}
