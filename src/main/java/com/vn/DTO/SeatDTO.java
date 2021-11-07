package com.vn.DTO;

public class SeatDTO { 
  private Integer seatId; 
  private String  seatColumn; 
  private Integer seatRow; 
  private Integer statusBooking;
  private Integer seatType;
  
  public SeatDTO() {
    // TODO Auto-generated constructor stub
  }
  
  public Integer getSeatId() {
    return seatId;
  }

  public void setSeatId(Integer seatId) {
    this.seatId = seatId;
  }

  public String getSeatColumn() {
    return seatColumn;
  }

  public void setSeatColumn(String seatColumn) {
    this.seatColumn = seatColumn;
  }

  public Integer getSeatRow() {
    return seatRow;
  }

  public void setSeatRow(Integer seatRow) {
    this.seatRow = seatRow;
  }

  public Integer getStatusBooking() {
    return statusBooking;
  }

  public void setStatusBooking(Integer statusBooking) {
    this.statusBooking = statusBooking;
  }

  public Integer getSeatType() {
    return seatType;
  }

  public void setSeatType(Integer seatType) {
    this.seatType = seatType;
  }

  public SeatDTO(Integer seatId, String seatColumn, Integer seatRow, Integer statusBooking, Integer seatType) {
    super();
    this.seatId = seatId;
    this.seatColumn = seatColumn;
    this.seatRow = seatRow;
    this.statusBooking = statusBooking;
    this.seatType = seatType;
  }

  @Override
  public String toString() {
    return "SeatDTO [seatId=" + seatId + ", seatColumn=" + seatColumn + ", seatRow=" + seatRow + ", statusBooking="
        + statusBooking + ", seatType=" + seatType + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((seatColumn == null) ? 0 : seatColumn.hashCode());
    result = prime * result + ((seatRow == null) ? 0 : seatRow.hashCode());
    result = prime * result + ((seatType == null) ? 0 : seatType.hashCode());
    result = prime * result + ((statusBooking == null) ? 0 : statusBooking.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SeatDTO other = (SeatDTO) obj;
    if (seatColumn == null) {
      if (other.seatColumn != null)
        return false;
    } else if (!seatColumn.equals(other.seatColumn))
      return false;
    if (seatRow == null) {
      if (other.seatRow != null)
        return false;
    } else if (!seatRow.equals(other.seatRow))
      return false;
    if (seatType == null) {
      if (other.seatType != null)
        return false;
    } else if (!seatType.equals(other.seatType))
      return false;
    if (statusBooking == null) {
      if (other.statusBooking != null)
        return false;
    } else if (!statusBooking.equals(other.statusBooking))
      return false;
    return true;
  }

 
  
  
}
