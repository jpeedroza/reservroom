package com.jpeedroza.reservroom.controller;

import com.jpeedroza.reservroom.entity.Rooms;
import com.jpeedroza.reservroom.service.RoomsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class RoomsController {

  private final RoomsService roomsService;

  public RoomsController(RoomsService roomsService) {
    this.roomsService = roomsService;
  }

  @GetMapping
  public ResponseEntity<List<Rooms>> listAllRooms() {
    return ResponseEntity.ok().body(roomsService.findAllRooms());
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Rooms> listSpecificRoomById(@PathVariable Long id) {
    return ResponseEntity.ok().body(roomsService.findSpecificRoomById(id));
  }

  @PostMapping
  public ResponseEntity<Rooms> createNewRoom(@RequestBody Rooms room) {
    return ResponseEntity.status(HttpStatus.CREATED).body(roomsService.saveNewRoom(room));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Rooms> modifyValuesFromRoom(@PathVariable Long id, @RequestBody Rooms room) {
    return ResponseEntity.ok(roomsService.saveValuesFromExistingRoom(id, room));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteRoom(@PathVariable Long id,
                                 @RequestParam(required = false, value = "destroy") Boolean forceDestroy) {
    return ResponseEntity.ok(roomsService.deleteExistingRoom(id, forceDestroy));
  }
}
