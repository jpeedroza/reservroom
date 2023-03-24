package com.jpeedroza.reservroom.utils.message;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ErrorResponse {
  private String title;
  private String detail;
  private int code;
  private HttpStatus status;
  private DetailsError source;
}
