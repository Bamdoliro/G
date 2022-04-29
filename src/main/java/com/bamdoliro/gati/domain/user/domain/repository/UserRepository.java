package com.bamdoliro.gati.domain.user.domain.repository;

import com.bamdoliro.gati.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
