package com.vn.DTO;

public class DateDTO {
  private String day;
  private String dateOfMonth;
  
  public DateDTO() {
    // TODO Auto-generated constructor stub
  }

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }

  public String getDateOfMonth() {
    return dateOfMonth;
  }

  public void setDateOfMonth(String dateOfMonth) {
    this.dateOfMonth = dateOfMonth;
  }

  public DateDTO(String day, String dateOfMonth) {
    super();
    this.day = day;
    this.dateOfMonth = dateOfMonth;
  }

  @Override
  public String toString() {
    return "DateDTO [day=" + day + ", dateOfMonth=" + dateOfMonth + "]";
  }
  
} 
