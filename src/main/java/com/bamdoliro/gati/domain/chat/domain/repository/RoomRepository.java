package com.bamdoliro.gati.domain.chat.domain.repository;

import com.bamdoliro.gati.domain.chat.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
