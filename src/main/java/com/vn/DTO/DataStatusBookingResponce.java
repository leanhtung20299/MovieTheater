package com.vn.DTO;

import java.util.List;

public class DataStatusBookingResponce {
  private String statusTransaction;
  private List<String> listSeatNotAvailable;

  public DataStatusBookingResponce() {
    
  }
  

  public DataStatusBookingResponce(String statusTransaction) {
    super();
    this.statusTransaction = statusTransaction;
  }


  public String getStatusTransaction() {
    return statusTransaction;
  }

  public void setStatusTransaction(String statusTransaction) {
    this.statusTransaction = statusTransaction;
  }

  public List<String> getListSeatNotAvailable() {
    return listSeatNotAvailable;
  }

  public void setListSeatNotAvailable(List<String> listSeatNotAvailable) {
    this.listSeatNotAvailable = listSeatNotAvailable;
  }
  
}
