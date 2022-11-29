package com.bamdoliro.gati.domain.chat.domain.repository;

import com.bamdoliro.gati.domain.chat.domain.Room;
import com.bamdoliro.gati.domain.chat.domain.RoomMember;
import com.bamdoliro.gati.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoomMemberRepository extends CrudRepository<RoomMember, Long> {

    Optional<RoomMember> findByRoomAndUser(Room room, User user);

    List<RoomMember> findAllByUser(User user);

    int countByRoom(Room room);
}
