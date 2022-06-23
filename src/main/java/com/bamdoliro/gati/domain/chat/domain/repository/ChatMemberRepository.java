package com.bamdoliro.gati.domain.chat.domain.repository;

import com.bamdoliro.gati.domain.chat.domain.ChatMember;
import org.springframework.data.repository.CrudRepository;

public interface ChatMemberRepository extends CrudRepository<ChatMember, Long> {
}
