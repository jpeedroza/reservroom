package com.jpeedroza.reservroom.exceptions;

public class RoomNotFoundException extends RuntimeException {
  public RoomNotFoundException(Long id){
    super("The id " + id + " was not found");
  }
}
