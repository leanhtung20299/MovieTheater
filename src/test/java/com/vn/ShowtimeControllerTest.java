package com.vn;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.vn.DTO.DateDTO;
import com.vn.repositories.ShowDatesRepository;
import com.vn.service.ShowDatesService;

@RunWith(MockitoJUnitRunner.class)
public class ShowtimeControllerTest {

  @Autowired
  @InjectMocks
  private ShowDatesService showDateService;
  
  @Autowired
  @Mock
  private ShowDatesRepository showDatesRepository;
  
  @Test
  public void testShowTimeSuccess() { 
//    List<DateDTO> list = showDateService.getListDate(LocalDate.now());
//    when(showDatesRepository.)
//    assertTrue(null, false);
  }

}
