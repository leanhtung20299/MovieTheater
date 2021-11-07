package com.vn.DTO;

public class WeekMovieDTO {
  private String dayOfMonthNow;
  private String dayOfMonthTo;
  private String monthNow;
  private String monthTo;
  private String yearNow;
  private String yearTo;
  
  
  public WeekMovieDTO() {
    
  }
  

  public WeekMovieDTO(String dayOfMonthNow, String dayOfMonthTo, String monthNow, String monthTo, String yearNow,
      String yearTo) {
    super();
    this.dayOfMonthNow = dayOfMonthNow;
    this.dayOfMonthTo = dayOfMonthTo;
    this.monthNow = monthNow;
    this.monthTo = monthTo;
    this.yearNow = yearNow;
    this.yearTo = yearTo;
  }




  @Override
  public String toString() {
    return "WeekMovieDTO [dayOfMonthNow=" + dayOfMonthNow + ", dayOfMonthTo=" + dayOfMonthTo + ", monthNow=" + monthNow
        + ", monthTo=" + monthTo + ", yearNow=" + yearNow + ", yearTo=" + yearTo + "]";
  }


  public String getDayOfMonthNow() {
    return dayOfMonthNow;
  }

  public void setDayOfMonthNow(String dayOfMonthNow) {
    this.dayOfMonthNow = dayOfMonthNow;
  }

  public String getDayOfMonthTo() {
    return dayOfMonthTo;
  }

  public void setDayOfMonthTo(String dayOfMonthTo) {
    this.dayOfMonthTo = dayOfMonthTo;
  }

  public String getMonthNow() {
    return monthNow;
  }

  public void setMonthNow(String monthNow) {
    this.monthNow = monthNow;
  }

  public String getMonthTo() {
    return monthTo;
  }

  public void setMonthTo(String monthTo) {
    this.monthTo = monthTo;
  }

  public String getYearNow() {
    return yearNow;
  }

  public void setYearNow(String yearNow) {
    this.yearNow = yearNow;
  }

  public String getYearTo() {
    return yearTo;
  }

  public void setYearTo(String yearTo) {
    this.yearTo = yearTo;
  }

  
  
  
}
