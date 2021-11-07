package com.vn.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Promotion", schema = "movietheater")
public class Promotion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "promotion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer promotionId;
    
    @Column(name = "detail", columnDefinition = "NVARCHAR(255)")
    private String detail;
    
    @Column(name = "discount_level")
    private int discountLevel;
    
    @Column(name = "end_time")
    private Date endTime;
    
    @Column(name = "image", columnDefinition = "VARCHAR(255)")
    private String image;
    
    @Column(name = "start_time")
    private Date startTime;
    
    @Column(name = "title", columnDefinition = "NVARCHAR(255)")
    private String title;
    
    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Invoice> invoice;
    
  

    @Override
    public String toString() {
      return  detail + ", discountLevel=" + discountLevel +"đ";
    }
    
    
}
