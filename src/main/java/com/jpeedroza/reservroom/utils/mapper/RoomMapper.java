package com.jpeedroza.reservroom.utils.mapper;

import com.jpeedroza.reservroom.dto.RoomDTO;
import com.jpeedroza.reservroom.entity.Rooms;

import java.time.LocalDateTime;

public class RoomMapper implements Converter<Rooms, RoomDTO> {
  @Override
  public Rooms convertToEntity(RoomDTO sourceOrigin) {
    if(sourceOrigin == null)
      return null;
    return Rooms.builder().name(sourceOrigin.getName())
            .description(sourceOrigin.getDescription())
            .location(sourceOrigin.getLocation())
            .userName(sourceOrigin.getUserName())
            .valuePerDay(sourceOrigin.getValuePerDay())
            .id(sourceOrigin.getId())
            .activated(true)
            .timePosted(LocalDateTime.now())
            .build();
  }

  @Override
  public RoomDTO convertToDTO(Rooms targetOrigin) {
    if(targetOrigin == null)
      return null;
    return new RoomDTO(
            targetOrigin.getId(),
            targetOrigin.getName(),
            targetOrigin.getDescription(),
            targetOrigin.getLocation(),
            targetOrigin.getValuePerDay(),
            targetOrigin.getUserName());
  }
}
