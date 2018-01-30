package com.woowahan.junweb.repository;

import com.woowahan.junweb.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUserId(String userId);
}
