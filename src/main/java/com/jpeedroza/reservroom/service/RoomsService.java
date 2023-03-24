package com.jpeedroza.reservroom.service;

import com.jpeedroza.reservroom.dto.RoomDTO;
import com.jpeedroza.reservroom.exceptions.RoomNotFoundException;
import com.jpeedroza.reservroom.repository.RoomsRepository;
import com.jpeedroza.reservroom.utils.mapper.RoomMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomsService {

  private final RoomsRepository roomsRepository;
  private final RoomMapper roomMapper;

  public List<RoomDTO> findAllRooms() {
    return roomsRepository.findAll()
            .stream()
            .map(roomMapper::convertToDTO)
            .collect(Collectors.toList());
  }

  public RoomDTO findSpecificRoomById(@PathVariable Long id) {
    return roomsRepository
            .findById(id)
            .map(roomMapper::convertToDTO)
            .orElseThrow(() -> new RoomNotFoundException(id));
  }

  public RoomDTO saveNewRoom(@RequestBody RoomDTO room) {
    return roomMapper.convertToDTO(
            roomsRepository.save(roomMapper.convertToEntity(room)));
  }

  public RoomDTO saveValuesFromExistingRoom(@PathVariable Long id, @RequestBody RoomDTO room) {
    return roomsRepository.findById(id)
            .map(room1 -> {
              room1.setName(room.getName());
              room1.setDescription(room.getDescription());
              room1.setLocation(room.getLocation());
              room1.setUserName(room.getUserName());
              room1.setTimePosted(LocalDateTime.now());
              room1.setValuePerDay(room.getValuePerDay());
              return roomMapper.convertToDTO(room1);
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
