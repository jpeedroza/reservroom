package com.jpeedroza.reservroom.utils;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonRootName(value = "source")
@Data
@AllArgsConstructor
public class DetailsError {
  private String pointer;
  private String parameter;
}
