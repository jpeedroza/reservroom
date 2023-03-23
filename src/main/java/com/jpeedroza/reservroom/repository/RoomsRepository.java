package com.jpeedroza.reservroom.repository;

import com.jpeedroza.reservroom.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Long> {

  @Transactional
  @Query(value = "DELETE FROM Rooms WHERE id = ?1")
  @Modifying
  int deleteRoomById(Long id);

  @Transactional
  @Query(value = "UPDATE Rooms SET activated = false WHERE id = ?1")
  @Modifying
  int deactivateStatusFromRoom(Long id);
}
