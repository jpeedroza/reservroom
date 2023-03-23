package com.jpeedroza.reservroom.service;

import com.jpeedroza.reservroom.entity.Rooms;
import com.jpeedroza.reservroom.exceptions.RoomNotFoundException;
import com.jpeedroza.reservroom.repository.RoomsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class RoomsService {

  private final RoomsRepository roomsRepository;

  public List<Rooms> findAllRooms() {
    return roomsRepository.findAll();
  }

  public Rooms findSpecificRoomById(@PathVariable Long id) {
    return roomsRepository
            .findById(id)
            .orElseThrow(() -> new RoomNotFoundException(id));
  }

  public Rooms saveNewRoom(@RequestBody Rooms room) {
    return roomsRepository.save(room);
  }

  public Rooms saveValuesFromExistingRoom(@PathVariable Long id, @RequestBody Rooms room) {
    return roomsRepository.findById(id)
            .map(room1 -> {
              return roomsRepository.save(Rooms.builder()
                      .name(room.getName())
                      .description(room.getDescription())
                      .location(room.getLocation())
                      .userName(room.getUserName())
                      .timePosted(LocalDateTime.now())
                      .valuePerDay(room.getValuePerDay())
                      .build());
            })
            .orElseThrow(() -> new RoomNotFoundException(id));
  }
  public Map<String, Object> deleteExistingRoom(@PathVariable Long id, @RequestParam(required = false, value = "destroy") Boolean forceDestroy) {
    Map<String, Object> mapper = new HashMap<>();
    int rows = 0;

    if (!String.valueOf(forceDestroy).isEmpty() && Boolean.TRUE.equals(forceDestroy))
      rows = roomsRepository.deleteRoomById(id);
    else
      rows = roomsRepository.deactivateStatusFromRoom(id);

    mapper.put("rows_affected", rows);
    mapper.put("soft_deleted", forceDestroy);
    return mapper;
  }
}
