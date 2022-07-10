package com.bamdoliro.gati.domain.chat.domain.repository;

import com.bamdoliro.gati.domain.chat.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
