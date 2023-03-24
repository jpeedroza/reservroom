package com.jpeedroza.reservroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RoomDTO {
  private Long id;
  private String name;
  private String description;
  private String location;
  private BigDecimal valuePerDay;
  private String userName;
}
