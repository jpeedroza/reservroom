package com.jpeedroza.reservroom.controller;

import com.jpeedroza.reservroom.exceptions.RoomNotFoundException;
import com.jpeedroza.reservroom.utils.DetailsError;
import com.jpeedroza.reservroom.utils.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {RoomNotFoundException.class})
  protected ResponseEntity<Object> handleNoContentID(RoomNotFoundException ex, WebRequest webRequest, HttpServletRequest servletRequest){
    var errorResponse = ErrorResponse.builder()
            .title("Not found the ID")
            .detail(ex.getMessage())
            .status(HttpStatus.NO_CONTENT)
            .code(HttpStatus.NO_CONTENT.value())
            .source(new DetailsError(servletRequest.getServletPath(), servletRequest.getParameter("destroy")))
            .build();
    var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    log.info("Exception " + ex.getClass().toString() + " handled by controller advicer");
    return handleExceptionInternal(ex, errorResponse, headers, HttpStatus.NO_CONTENT, webRequest);
  }
}
